public class Edge {
	private int from;
	private int to;
	private int length;
	private Edge nextArc;
	
	public Edge(int from, int to, int length) {
		this.from = from;
		this.to = to;
		this.length = length;
	}
	
	public int getFromVertex() {
		return from;
	}
	
	public int getToVertex() {
		return to;
	}
	
	public int getLength() {
		return length;
	}
	
	public Edge getNextArc() {
		return nextArc;
	}
	
	public void setNextArc(Edge arc) {
		nextArc = arc;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", length=" + length + "]";
	}
}
