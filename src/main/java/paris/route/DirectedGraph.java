package paris.route;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

public class DirectedGraph {

	private int n;
	private int m;
	private Map<String, Vertex> vertices;
	private HashMap<String, List<DirectedEdge>> adj;

	public DirectedGraph() throws IOException {

		System.out.println("Building graph...");
		this.adj = new HashMap<String, List<DirectedEdge>>();
		String data = new String(Files.readAllBytes(Paths.get("data/reseau.json")));
		this.vertices = new HashMap<String, Vertex>();

		// Parsing stations
		JSONObject stationsRawData = new JSONObject(data);
		JSONObject stationsJsonData = (JSONObject) stationsRawData.get("stations");
		Iterator<String> keys = stationsJsonData.keys();
		while (keys.hasNext()) {
			String k = keys.next();
			String num = (String) stationsJsonData.getJSONObject(k).get("num");
			String nom = (String) stationsJsonData.getJSONObject(k).get("nom");
			String type = (String) stationsJsonData.getJSONObject(k).get("type");
			double lat = Double.parseDouble((String) stationsJsonData.getJSONObject(k).get("lat"));
			double lng = Double.parseDouble((String) stationsJsonData.getJSONObject(k).get("lng"));
			Vertex v = new Vertex(lng, lat, nom, num, type);
			this.vertices.put(num, v);
		}
		
		// Parsing all edges with the 'routes' element
		JSONObject routesRawData = new JSONObject(data);
		JSONArray routesJsonArray = (JSONArray) routesRawData.get("routes");
		List<DirectedEdge> lde = new ArrayList<DirectedEdge>();  
		for (int i = 0; i < routesJsonArray.length(); i++) {
			JSONObject routeObject = (JSONObject)routesJsonArray.get(i);
			String t = (String)routeObject.get("type");
			if (t.matches("corresp")) {
				//System.out.println(routeObject.get("type"));
			} else {
				JSONArray stops = (JSONArray)routeObject.get("arrets");
				for (int j = 0; j < stops.length()-1; j++) {
					double dist = Math.sqrt(
							Math.pow(vertices.get(stops.get(j)).getLongitude() - vertices.get(stops.get(j+1)).getLongitude(), 2) + 
							Math.pow(vertices.get(stops.get(j)).getLattitude() - vertices.get(stops.get(j+1)).getLattitude(), 2)
							);
					DirectedEdge de = new DirectedEdge(vertices.get(stops.get(j)), vertices.get(stops.get(j + 1)), dist);
					lde.add(de);
				}
			}
		}

		// Parsing stations with multiple name: last thing to do from corresp element
		JSONObject correspRawData = new JSONObject(data);
		JSONArray corresp = (JSONArray) correspRawData.get("corresp");
		for (int i = 0; i < corresp.length(); i++) {
			JSONArray cor = (JSONArray) corresp.get(i);
			if (cor.length() == 2) {
				double dist = Math.sqrt(
					Math.pow(vertices.get(cor.get(0)).getLongitude() - vertices.get(cor.get(1)).getLongitude(), 2) + 
					Math.pow(vertices.get(cor.get(0)).getLattitude() - vertices.get(cor.get(1)).getLattitude(), 2)
					);
				DirectedEdge de1 = new DirectedEdge(vertices.get(cor.get(0)), vertices.get(cor.get(1)), dist);
				DirectedEdge de2 = new DirectedEdge(vertices.get(cor.get(1)), vertices.get(cor.get(0)), dist);
				lde.add(de1);
				lde.add(de2);
			} else {
				double dist = Math.sqrt(
						Math.pow(vertices.get(cor.get(0)).getLongitude() - vertices.get(cor.get(1)).getLongitude(), 2) + 
						Math.pow(vertices.get(cor.get(0)).getLattitude() - vertices.get(cor.get(1)).getLattitude(), 2)
						);
				DirectedEdge de1 = new DirectedEdge(vertices.get(cor.get(0)), vertices.get(cor.get(1)), dist);
				DirectedEdge de2 = new DirectedEdge(vertices.get(cor.get(1)), vertices.get(cor.get(0)), dist);
				dist = Math.sqrt(
						Math.pow(vertices.get(cor.get(1)).getLongitude() - vertices.get(cor.get(2)).getLongitude(), 2) + 
						Math.pow(vertices.get(cor.get(1)).getLattitude() - vertices.get(cor.get(2)).getLattitude(), 2)
						);
				DirectedEdge de3 = new DirectedEdge(vertices.get(cor.get(1)), vertices.get(cor.get(2)), dist);
				DirectedEdge de4 = new DirectedEdge(vertices.get(cor.get(2)), vertices.get(cor.get(1)), dist);
				dist = Math.sqrt(
						Math.pow(vertices.get(cor.get(0)).getLongitude() - vertices.get(cor.get(2)).getLongitude(), 2) + 
						Math.pow(vertices.get(cor.get(0)).getLattitude() - vertices.get(cor.get(2)).getLattitude(), 2)
						);
				DirectedEdge de5 = new DirectedEdge(vertices.get(cor.get(0)), vertices.get(cor.get(2)), dist);
				DirectedEdge de6 = new DirectedEdge(vertices.get(cor.get(2)), vertices.get(cor.get(0)), dist);
				lde.add(de1);
				lde.add(de2);
				lde.add(de3);
				lde.add(de4);
				lde.add(de5);
				lde.add(de6);
			}
		}
		
		// Building the graph
		
		for (int k = 0; k < lde.size(); k++) {
			String from = lde.get(k).getFrom().getCode();
			if (this.adj.containsKey(from)) {
				List<DirectedEdge> vElement = this.adj.get(from);
				if (!vElement.contains(lde.get(k))) {
					vElement.add(lde.get(k));
					this.adj.put(from, vElement);
				}
			} else {
				List<DirectedEdge> vElement = new ArrayList<DirectedEdge>();
				vElement.add(lde.get(k));
				this.adj.put(from, vElement);
			}
		}
		
		System.out.println("Done building graph...");
	}
	
	public List<DirectedEdge> getSuccessors(String v) {
		return this.adj.get(v);
	}
	
	public Map<String, String> bfs(String start, String stop) {
		List<String> visited = new ArrayList<String>();
		List<Vertex> queue = new LinkedList<Vertex>();
		Map<String, String> shortestPath = new HashMap<String, String>();
		queue.add(this.vertices.get(start));
		while (!queue.isEmpty()) {
			Vertex currentNode = queue.remove(0);
			if (!visited.contains(currentNode.getCode())) {
				visited.add(currentNode.getCode());
				if (currentNode.getCode().matches(stop)) {
					return shortestPath;
				}
				List<DirectedEdge> edges = this.getSuccessors(currentNode.getCode());
				for (DirectedEdge de : edges) {
					if (!visited.contains(de.getTo().getCode())) {
						shortestPath.put(de.getTo().getCode(), de.getFrom().getCode());
					}
					queue.add(de.getTo());
				}	
			}	
		}
		return shortestPath;
	}
	
	public Map<String, String> dfs(String start, String stop) {
		List<String> visited = new ArrayList<String>();
		Stack<Vertex> stack = new Stack<Vertex>();
		Map<String, String> shortestPath = new HashMap<String, String>();
		stack.add(this.vertices.get(start));
		while (!stack.isEmpty()) {
			Vertex currentNode = stack.pop();
			if (!visited.contains(currentNode.getCode())) {
				visited.add(currentNode.getCode());
				if (currentNode.getCode().matches(stop)) {
					return shortestPath;
				}
				List<DirectedEdge> edges = this.getSuccessors(currentNode.getCode());
				for (DirectedEdge de : edges) {
					if (!visited.contains(de.getTo().getCode())) {
						shortestPath.put(de.getTo().getCode(), de.getFrom().getCode());
					}
					stack.add(de.getTo());
				}	
			}	
		}
		return shortestPath;
	}
	
	public Map<String, String> dijkstra(String start, String stop) {
		List<String> visited = new ArrayList<String>();
		Queue<DestinationVertexWithCost> queue = new PriorityQueue<DestinationVertexWithCost>();
		Map<String, String> shortestPath = new HashMap<String, String>();
		queue.add(new DestinationVertexWithCost(this.vertices.get(start), 0));
		while (!queue.isEmpty()) {
			DestinationVertexWithCost currentNode = queue.remove();
			if (!visited.contains(currentNode.getV().getCode())) {
				visited.add(currentNode.getV().getCode());
				if (currentNode.getV().getCode().matches(stop)) {
					return shortestPath;
				}
				List<DirectedEdge> edges = this.getSuccessors(currentNode.getV().getCode());
				for (DirectedEdge de : edges) {
					if (!visited.contains(de.getTo().getCode())) {
						shortestPath.put(de.getTo().getCode(), de.getFrom().getCode());
					}
					queue.add(new DestinationVertexWithCost(de.getTo(), de.getWeight()));
					
				}	
			}	
		}
		return shortestPath;
	}
	
	public Map<String, Vertex> getVertices() {
		return this.vertices;
	}
	
	public HashMap<String, List<DirectedEdge>> getAdj() {
		return adj;
	}

	public void setAdj(HashMap<String, List<DirectedEdge>> adj) {
		this.adj = adj;
	}
}
