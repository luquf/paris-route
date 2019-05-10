package paris.route;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

	private int n;
	private int m;
	private HashMap<String, List<DirectedEdge>> adj;
	
	public Graph() throws IOException {
		
		System.out.println("##### Building graph from data... #####");
				
		// Building graph from CSV resource
		this.adj = new HashMap<String, List<DirectedEdge>>();
		HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
		BufferedReader buf = new BufferedReader(new FileReader("data/positions-geographiques-des-stations-du-reseau-ratp.csv"));
        String line;
        int i = 1;
        while ((line = buf.readLine()) != null) {
        	String[] data = line.split(",");
        	System.out.println(data[0]);
        	if (data.length == 5) {
        		Vertex v = new Vertex(Double.parseDouble(data[3]), Double.parseDouble(data[4]), data[2], data[1], data[0]);
        		vertices.put(v.getCode(), v);
        	} else if (data.length == 6) {
        		Vertex v = new Vertex(Double.parseDouble(data[4]), Double.parseDouble(data[5]), data[2], data[1], data[0]);
        		vertices.put(v.getCode(), v);
        	}
        	this.adj.put(data[0], new ArrayList<DirectedEdge>());
        }
        buf.close();
        
        System.out.println("##### Done parsing vertices #####");
        
        // Attaching edges from TXT resource
		buf = new BufferedReader(new FileReader("data/edges.txt"));
        while ((line = buf.readLine()) != null) {
        	String[] d = line.split(" ");
        	System.out.println(vertices.containsKey("8399"));
        	Double euclidian = Math.sqrt((Math.pow(vertices.get(d[0]).getLongitude(), 2) - Math.pow(vertices.get(d[1]).getLongitude(), 2)) + (Math.pow(vertices.get(d[0]).getLattitude(), 2) - Math.pow(vertices.get(d[1]).getLattitude(), 2)));
        	DirectedEdge de = new DirectedEdge(vertices.get(d[0]), vertices.get(d[1]), euclidian);
        	List<DirectedEdge> currentList = this.adj.get(d[0]);
        	if (currentList == null) {
        		currentList = new ArrayList<DirectedEdge>();
        	}
        	currentList.add(de);
        	this.adj.put(d[0], currentList);
        }
        buf.close();
        
        System.out.println("##### Graph is ready ######");
	}
}
