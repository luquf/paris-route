package paris.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortestPath {
	
	DirectedGraph dg;
	
	int wDiameterValue = 0;
	String wDiameterStart = "";
	String wDiameterStop = "";
	
	int uwDiameterValue = 0;
	String uwDiameterStart = "";
	String uwDiameterStop = "";
	
	public ShortestPath(DirectedGraph dg) {
		this.dg = dg;
	}
	
	public void printUnWeightedDiameter() {
		System.out.println("Calculating all shortest paths to determine diameter...");
		this.uwDiameterStart = "";
		this.uwDiameterStop = "";
		this.uwDiameterValue = 0;
		Set<String> vertices = dg.getVertices().keySet();
		List<String> arrayVertices = new ArrayList<String>(vertices);
		for (int i = 0; i < arrayVertices.size()-1; i++) {
			for (int j = i; j < arrayVertices.size()-1; j++) {
				String start = arrayVertices.get(i);
				String stop = arrayVertices.get(j + 1);
				Map<String, String> path = dg.bfs(start, stop);
				String parent = stop;
				int d = 0;
				while(path.get(parent) != start) {
					if (parent == null) {
						break;
					}
					d += 1;
					parent = path.get(parent);
				}
				if (d > this.uwDiameterValue) {
					this.uwDiameterValue = d;
					this.uwDiameterStart = start;
					this.uwDiameterStop = stop;
				}
			}
		}
		System.out.println("Diameter: " + this.uwDiameterValue + "(" + this.uwDiameterStart + " to " + this.uwDiameterStop + ")");
	}
	
	public void printWeightedDiameter() {
		this.wDiameterStart = "";
		this.wDiameterStop = "";
		this.wDiameterValue = 0;
		System.out.println("Calculating all shortest paths to determine diameter...");
		Set<String> vertices = dg.getVertices().keySet();
		List<String> arrayVertices = new ArrayList<String>(vertices);
		for (int i = 0; i < arrayVertices.size()-1; i++) {
			for (int j = i; j < arrayVertices.size()-1; j++) {
				String start = arrayVertices.get(i);
				String stop = arrayVertices.get(j + 1);
				Map<String, String> path = dg.dijkstra(start, stop);
				String parent = stop;
				int d = 0;
				while(path.get(parent) != start) {
					if (parent == null) {
						break;
					}
					d += 1;
					parent = path.get(parent);
				}
				if (d > this.wDiameterValue) {
					this.wDiameterValue = d;
					this.wDiameterStart = start;
					this.wDiameterStop = stop;
				}
			}
		}
		System.out.println("Diameter: " + this.wDiameterValue + "(" + this.wDiameterStart + " to " + this.wDiameterStop + ")");
	}
}
