package paris.route;

public class DirectedEdge implements Comparable<DirectedEdge> {
	
	private Vertex from;
	private Vertex to;
	private double weight;
	
	public DirectedEdge(Vertex from, Vertex to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public Vertex getFrom() {
		return from;
	}

	public void setFrom(Vertex from) {
		this.from = from;
	}

	public Vertex getTo() {
		return to;
	}

	public void setTo(Vertex to) {
		this.to = to;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int compareTo(DirectedEdge arg0) {
		if (this.weight > arg0.weight) {
			return 1;
		} else if (this.weight == arg0.weight) {
			return 0;
		} else {
			return -1;
		}
	}
}
