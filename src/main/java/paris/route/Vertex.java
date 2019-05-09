package paris.route;

public class Vertex {
	
	private double longitude;
	private double lattitude;
	private String Address;
	private String name;
	private String code;
	
	public Vertex(double longitude, double lattitude, String address, String name, String code) {
		this.longitude = longitude;
		this.lattitude = lattitude;
		Address = address;
		this.name = name;
		this.code = code;
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
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
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
