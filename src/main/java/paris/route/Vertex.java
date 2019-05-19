package paris.route;

public class Vertex {
	
	private double longitude;
	private double lattitude;
	private String name;
	private String code;
	private String type;
	
	public Vertex(double longitude, double lattitude, String name, String code, String type) {
		this.longitude = longitude;
		this.lattitude = lattitude;
		this.name = name;
		this.code = code;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
