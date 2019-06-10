package paris.route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class App {
    public static void main( String[] args ) throws IOException {
    	DirectedGraph g = new DirectedGraph();
    	String start = "A_1983"; // Conflans-Fin d'Oise
    	String stop = "1867"; // Kléber
    	
    	/*
    	 * Finds shortest path
    	 * */
    	
    	Map<String, String> path = g.bfs(start, stop);
//    	Map<String, String> path = g.dijkstra(start, stop);
    	List<String> finalPath = new ArrayList<String>();
    	finalPath.add(stop);
    	String parent = path.get(stop);
    	finalPath.add(parent);
    	if (!parent.matches(start)) {
    		while (true) {
        		String next = path.get(parent);
        		finalPath.add(next);
        		parent = next;
        		if (parent.matches(start)) break;
        	}
    	}
    	Collections.reverse(finalPath);
    	for (int i = 0; i < finalPath.size(); i++) {
    		System.out.println(g.getVertices().get(finalPath.get(i)).getName() + " (" + g.getVertices().get(finalPath.get(i)).getType() + ")");
    	}
    	
    	/* 
    	 * Calculates the diameters
    	 * */
    	
    	ShortestPath sp = new ShortestPath(g);
    	sp.printUnWeightedDiameter(); // OUTPUT: Diameter: 47(3798491 to B_1673)
//    	sp.printWeightedDiameter(); // OUTPUT: Diameter: 101(B_1673 to 22443)
    }
}

