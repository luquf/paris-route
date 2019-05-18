package paris.route;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Graph {

	private int n;
	private int m;
	private HashMap<String, List<DirectedEdge>> adj;

	public Graph() throws IOException {

		System.out.println("Building graph...");
		String data = new String(Files.readAllBytes(Paths.get("data/reseau.json")));
		List<Vertex> vertices = new ArrayList<Vertex>();

		// Parsing Stations
		JSONObject obj2 = new JSONObject(data);
		JSONObject obj3 = (JSONObject) obj2.get("stations");
		Iterator<String> keys = obj3.keys();
		while (keys.hasNext()) {
			String k = keys.next();
			String num = (String) obj3.getJSONObject(k).get("num");
			String nom = (String) obj3.getJSONObject(k).get("nom");
			String type = (String) obj3.getJSONObject(k).get("type");
			double lat = Double.parseDouble((String) obj3.getJSONObject(k).get("lat"));
			double lng = Double.parseDouble((String) obj3.getJSONObject(k).get("lng"));
			Vertex v = new Vertex(lng, lat, nom, num, type);
			vertices.add(v);
		}

		// Parsing crossings
		JSONObject obj = new JSONObject(data);
		JSONArray corresp = (JSONArray) obj.get("corresp");
		System.out.println(corresp.length());
		for (int i = 0; i < corresp.length(); i++) {
			// System.out.println(corresp.get(i));
		}

		System.out.println("Done building graph...");
	}
}
