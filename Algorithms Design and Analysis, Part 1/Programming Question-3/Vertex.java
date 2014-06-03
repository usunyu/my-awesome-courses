import java.util.ArrayList;

public class Vertex {
	int data;
	
	Edge firstArc;
	
	public Vertex(int data) {
		this.data = data;
	}
	
	public void addEdges(ArrayList<Integer> edges) {
		for(int e : edges) {
			Edge edge = new Edge(e - 1);
			if(firstArc != null)
				edge.nextArc = firstArc;
			firstArc = edge;
		}
	}
}
