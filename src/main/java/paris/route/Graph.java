package paris.route;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Graph {

	private int n;
	private int m;
	private TreeMap<Vertex, List<DirectedEdge>> adj;
	
	public Graph() throws IOException {
		System.out.println("Building graph from data...");
		this.adj = new TreeMap<Vertex, List<DirectedEdge>>();
		BufferedReader buf = new BufferedReader(new FileReader("data/positions-geographiques-des-stations-du-reseau-ratp.csv"));
        String line;
        while ((line = buf.readLine()) != null) {
        	String[] data = line.split(",");
        	Vertex v = new Vertex(Double.parseDouble(data[3]), Double.parseDouble(data[4]), data[2], data[1], data[0]);
        	this.adj.put(v, new ArrayList<DirectedEdge>());
        }
	}
}
