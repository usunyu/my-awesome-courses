import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices;
	
	public Graph() {
		vertices = new ArrayList<Vertex>();
	}
	
	public void addVertex(int data) {
		Vertex vertex = new Vertex(data);
		vertices.add(vertex);
	}
	
	public void addEdge(int from, int to) {
		Vertex vertex = vertices.get(from - 1);
		vertex.addEdge(to);
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	
	public int size() {
		return vertices.size();
	}
	
	public void print() {
		for(Vertex vertex : vertices) {
			System.out.print(vertex.getData() + "\t");
			Edge edge = vertex.getFirstArc();
			while(edge != null) {
				System.out.print(edge.getToVertex() + "\t");
				edge = edge.getNextArc();
			}
			System.out.println();
		}
	}

	@Override
	public String toString() {
		return "Graph [vertices=" + vertices + "]";
	}
}
