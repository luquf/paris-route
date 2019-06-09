package paris.route;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
	
	String data;
	
	public JsonParser() throws IOException {
		this.data = new String(Files.readAllBytes(Paths.get("data/reseau.json")));
	}
	
	public JSONObject getStations()  {
		JSONObject stationsRawData = new JSONObject(this.data);
		JSONObject stationsJsonData = (JSONObject) stationsRawData.get("stations");
		return stationsJsonData;
	}
	
	public JSONArray getRoutes() {
		JSONObject routesRawData = new JSONObject(data);
		JSONArray routesJsonArray = (JSONArray) routesRawData.get("routes");
		return routesJsonArray;
	}

	public JSONArray getCorresp() {
		JSONObject correspRawData = new JSONObject(data);
		JSONArray corresp = (JSONArray) correspRawData.get("corresp");
		return corresp;
	}
}
