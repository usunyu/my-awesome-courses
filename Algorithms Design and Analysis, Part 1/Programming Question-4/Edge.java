public class Edge {
	private int from;
	private int to;
	private Edge nextArc;
	
	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public int getFromVertex() {
		return from;
	}
	
	public int getToVertex() {
		return to;
	}
	
	public Edge getNextArc() {
		return nextArc;
	}
	
	public void setNextArc(Edge arc) {
		nextArc = arc;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + "]";
	}
}
