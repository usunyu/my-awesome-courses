/*
An adjacency list representation for a graph associates each vertex in the graph with the collection of its 
neighboring vertices or edges. There are many variations of this basic idea, differing in the details of how 
they implement the association between vertices and collections, in how they implement the collections, in 
whether they include both vertices and edges or only vertices as first class objects, and in what kinds of 
objects are used to represent the vertices and edges.
*/

import java.util.ArrayList;

public class Graph {
	private Vertex[] vertexs;
	
	public Graph(int size) {
		vertexs = new Vertex[size];
	}
	
	public void addVertex(int data) {
		vertexs[data - 1] = new Vertex(data);
	}
	
	public void addEdges(int index, ArrayList<Integer> edges) {
		vertexs[index].addEdges(edges);
	}
	
	public void print() {
		for(Vertex vertex : vertexs) {
			System.out.print(vertex.data + "\t");
			Edge edge = vertex.firstArc;
			while(edge != null) {
				System.out.print((edge.index + 1) + "\t");
				edge = edge.nextArc;
			}
			System.out.println();
		}
	}
}
