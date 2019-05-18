package paris.route;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class DirectedGraph {

	private int n;
	private int m;
	private HashMap<String, List<DirectedEdge>> adj;

	public DirectedGraph() throws IOException {

		System.out.println("Building graph...");
		String data = new String(Files.readAllBytes(Paths.get("data/reseau.json")));
		Map<String, Vertex> vertices = new HashMap<String, Vertex>();

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
			vertices.put(num, v);
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
					DirectedEdge de = new DirectedEdge(vertices.get(stops.get(j)), vertices.get(stops.get(j + 1)), 0.0);
					lde.add(de);
				}
				for (int j = stops.length()-1; j > 0; j--) {
					DirectedEdge de = new DirectedEdge(vertices.get(stops.get(j)), vertices.get(stops.get(j - 1)), 0.0);
					lde.add(de);
				}
			}
		}
		System.out.println("Number of edges: " + lde.size());

		// Parsing stations with multiple name: last thing to do
		JSONObject correspRawData = new JSONObject(data);
		JSONArray corresp = (JSONArray) correspRawData.get("corresp");
		for (int i = 0; i < corresp.length(); i++) {
			JSONArray cor = (JSONArray) corresp.get(i);
			if (cor.length() == 2) {
				DirectedEdge de1 = new DirectedEdge(vertices.get(cor.get(0)), vertices.get(cor.get(1)), 0.0);
				DirectedEdge de2 = new DirectedEdge(vertices.get(cor.get(0)), vertices.get(cor.get(1)), 0.0);
				lde.add(de1);
				lde.add(de2);
			} else {
				DirectedEdge de1 = new DirectedEdge(vertices.get(cor.get(0)), vertices.get(cor.get(1)), 0.0);
				DirectedEdge de2 = new DirectedEdge(vertices.get(cor.get(1)), vertices.get(cor.get(0)), 0.0);
				DirectedEdge de3 = new DirectedEdge(vertices.get(cor.get(1)), vertices.get(cor.get(2)), 0.0);
				DirectedEdge de4 = new DirectedEdge(vertices.get(cor.get(2)), vertices.get(cor.get(1)), 0.0);
				DirectedEdge de5 = new DirectedEdge(vertices.get(cor.get(0)), vertices.get(cor.get(2)), 0.0);
				DirectedEdge de6 = new DirectedEdge(vertices.get(cor.get(2)), vertices.get(cor.get(0)), 0.0);
				lde.add(de1);
				lde.add(de2);
				lde.add(de3);
				lde.add(de4);
				lde.add(de5);
				lde.add(de6);
			}
		}
		
		System.out.println("Number of edges: " + lde.size());

		System.out.println("Done building graph...");
	}
}
