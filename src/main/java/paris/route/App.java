package paris.route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class App {
    public static void main( String[] args ) throws IOException {
    	DirectedGraph g = new DirectedGraph();
    	String start = "1638";
    	String stop = "1726";
    	Map<String, String> path = g.dijkstra(start, stop);
    	List<String> finalPath = new ArrayList<String>();
    	finalPath.add(stop);
    	String parent = path.get(stop);
    	finalPath.add(parent);
    	while (true) {
    		String next = path.get(parent);
    		finalPath.add(next);
    		parent = next;
    		if (parent.matches(start)) break;
    	}
    	Collections.reverse(finalPath);
    	for (int i = 0; i < finalPath.size(); i++) {
    		//System.out.println(g.getVertices().get(finalPath.get(i)).getName() + " (" + g.getVertices().get(finalPath.get(i)).getType() + ")");
    	}
    }
}

