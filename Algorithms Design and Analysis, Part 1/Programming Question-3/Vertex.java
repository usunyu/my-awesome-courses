import java.util.ArrayList;

public class Vertex {
	boolean active;	// check whether contraction
	int data;
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
			Edge edge = new Edge(data, e);
			if(firstArc != null)
				edge.setNextArc(firstArc);
			firstArc = edge;
		}
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void deactivate() {
		active = false;
	}
}
