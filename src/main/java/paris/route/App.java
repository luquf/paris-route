package paris.route;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main( String[] args ) throws IOException {
    	DirectedGraph g = new DirectedGraph();
    	List<Vertex> path = g.bfs("A_1894", "1909");
    	for (Vertex v : path) {
    		System.out.println(v.getName());
    	}
    }
}

