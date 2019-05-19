package paris.route;

public class DestinationVertexWithCost implements Comparable<DestinationVertexWithCost> {
	
	private Vertex v;
	private double cost;
	
	public DestinationVertexWithCost(Vertex v, double cost) {
		super();
		this.v = v;
		this.cost = cost;
	}

	public Vertex getV() {
		return v;
	}

	public void setV(Vertex v) {
		this.v = v;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int compareTo(DestinationVertexWithCost arg0) {
		if (this.cost > arg0.getCost()) {
			return 1;
		} else if (this.cost == arg0.getCost()) {
			return 0;
		} else {
			return -1;
		}
	}

}
