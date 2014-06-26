public class Vertex {
	private int id;
	private Edge firstArc;
	private int dist;
	private boolean covered;	// if the vertex has been covered by dijkstra algorithm
	
	public Vertex(int id) {
		this.id = id;
		dist = 1000000;
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
	
	public void setDist(int dist) {
		this.dist = dist;
	}
	
	public int getDist() {
		return dist;
	}
	
	public void setCovered() {
		covered = true;
	}
	
	public boolean isCovered() {
		return covered;
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
