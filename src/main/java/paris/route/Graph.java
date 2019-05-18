package paris.route;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Graph {

	private int n;
	private int m;
	private HashMap<String, List<DirectedEdge>> adj;
	
	public Graph() throws IOException {
		
		String data = new String(Files.readAllBytes(Paths.get("data/reseau.json")));
		JSONObject obj = new JSONObject(data);
		JSONArray corresp = (JSONArray) obj.get("corresp");
		for (int i = 0; i < corresp.length(); i++) {
			// get the containing both stations crossing
		}
		
        
       
	}
}
