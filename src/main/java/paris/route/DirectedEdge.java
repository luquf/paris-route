package paris.route;

public class DirectedEdge implements Comparable<DirectedEdge> {
	
	private int from;
	private int to;
	private double weight;
	
	public DirectedEdge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
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
