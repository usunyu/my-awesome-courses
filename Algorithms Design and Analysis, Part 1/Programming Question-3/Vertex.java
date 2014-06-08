import java.util.ArrayList;

public class Vertex {
	private boolean active;	// check whether contraction
	private int data;
	private Edge firstArc;
	
	public Vertex(int data) {
		this.data = data;
		active = true;
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
	
	public boolean isActive() {
		return active;
	}
	
	public void deactivate() {
		active = false;
	}

	@Override
	public String toString() {
		return "Vertex [active=" + active + ", data=" + data + "]";
	}
}
