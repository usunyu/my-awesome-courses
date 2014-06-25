public class Vertex {
	private int id;
	private Edge firstArc;
	
	public Vertex(int id) {
		this.id = id;
	}
	
	public Edge getFirstArc() {
		return firstArc;
	}
	
	public int getId() {
		return id;
	}
	
	public int getIndex() {
		return id - 1;
	}
	
	public void addEdge(int e, int l) {
		Edge edge = new Edge(id, e, l);
		if(firstArc != null)
			edge.setNextArc(firstArc);
		firstArc = edge;
	}
	
	@Override
	public String toString() {
		return "Vertex [id=" + id + "]";
	}
}
