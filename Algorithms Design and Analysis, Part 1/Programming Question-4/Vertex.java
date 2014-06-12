public class Vertex {
	private int data;
	private Edge firstArc;
	
	public Vertex(int data) {
		this.data = data;
	}
	
	public Edge getFirstArc() {
		return firstArc;
	}
	
	public int getData() {
		return data;
	}
	
	public int getIndex() {
		return data - 1;
	}
	
	public void addEdge(int e) {
		Edge edge = new Edge(data, e);
		if(firstArc != null)
			edge.setNextArc(firstArc);
		firstArc = edge;
	}
	
	@Override
	public String toString() {
		return "Vertex [data=" + data + "]";
	}
}
