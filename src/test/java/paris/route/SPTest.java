package paris.route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class SPTest extends TestCase {
	
	public void testBFSPath() throws IOException {
		String start = "A_1894";
		String stop = "A_2053";
		DirectedGraph g = new DirectedGraph();
		Map<String, String> path = g.bfs(start, stop);
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
		assertEquals(finalPath.size(), 5);
		assertEquals(finalPath.get(0), start);
    	assertEquals(finalPath.get(finalPath.size()-1), stop);
	}
	
	public void testDijkstraPath() throws IOException {
		String start = "A_1894";
		String stop = "A_2053";
		DirectedGraph g = new DirectedGraph();
		Map<String, String> path = g.dijkstra(start, stop);
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
    	assertEquals(finalPath.get(0), start);
    	assertEquals(finalPath.get(finalPath.size()-1), stop);
	}
 }
