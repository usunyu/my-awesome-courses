public class Vertex {
	private int id;
	private int otherId;	// new id assigned by first DFS
	private Edge firstArc;
	private boolean explored;
	private Vertex leader;
	
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
	
	public void setOtherId(int otherId) {
		this.otherId = otherId;
	}
	
	public int getOtherId() {
		return otherId;
	}
	
	public boolean isExplored() {
		return explored;
	}
	
	public void setExplored() {
		explored = true;
	}
	
	public Vertex getLeader() {
		return leader;
	}
	
	public void setLeader(Vertex leader) {
		this.leader = leader;
	}
	
	public void addEdge(int e) {
		Edge edge = new Edge(id, e);
		if(firstArc != null)
			edge.setNextArc(firstArc);
		firstArc = edge;
	}
	
	@Override
	public String toString() {
		return "Vertex [id=" + id + "]";
	}
}
