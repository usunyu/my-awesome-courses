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
	
	public void addEdge(int from, int to, int length) {
		Vertex vertex = vertices.get(from - 1);
		vertex.addEdge(to, length);
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	
	public Vertex getVertex(int id) {
		return vertices.get(id - 1);
	}
	
	public int size() {
		return vertices.size();
	}
	
	public void print() {
		for(Vertex vertex : vertices) {
			System.out.print(vertex.getId() + "\t");
			Edge edge = vertex.getFirstArc();
			while(edge != null) {
				System.out.print(edge.getToVertex() + "(" + edge.getLength() + ")" + "\t");
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
