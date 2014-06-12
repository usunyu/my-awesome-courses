import java.util.ArrayList;

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
	
	public void addEdges(ArrayList<Integer> edges) {
		for(int e : edges) {
			addEdge(e);
		}
	}
	
	public void addEdge(int e) {
		Edge edge = new Edge(data, e);
		if(firstArc != null)
			edge.setNextArc(firstArc);
		firstArc = edge;
	}
	
	public void addEdge(Edge edge) {
		if(firstArc != null)
			edge.setNextArc(firstArc);
		firstArc = edge;
	}

	@Override
	public String toString() {
		return "Vertex [data=" + data + "]";
	}
}
