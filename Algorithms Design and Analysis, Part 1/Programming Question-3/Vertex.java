import java.util.ArrayList;

public class Vertex {
	boolean active;	// check whether contraction
	int data;
	Edge firstArc;
	
	public Vertex(int data) {
		this.data = data;
		active = true;
	}
	
	public void addEdges(ArrayList<Integer> edges) {
		for(int e : edges) {
			Edge edge = new Edge(data, e);
			if(firstArc != null)
				edge.nextArc = firstArc;
			firstArc = edge;
		}
	}
}
