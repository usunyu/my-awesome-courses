/*
The file contains an adjacency list representation of an undirected weighted graph with 200 vertices labeled 1 to 200.
Each row consists of the node tuples that are adjacent to that particular vertex along with the length of that edge.
For example, the 6th row has 6 as the first entry indicating that this row corresponds to the vertex labeled 6.
The next entry of this row "141,8200" indicates that there is an edge between vertex 6 and vertex 141 that has length
8200. The rest of the pairs of this row indicate the other vertices adjacent to vertex 6 and the lengths of the
corresponding edges.

Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first vertex) as the source vertex,
and to compute the shortest-path distances between 1 and every other vertex of the graph. If there is no path between
a vertex v and vertex 1, we'll define the shortest-path distance between 1 and v to be 1000000. 

You should report the shortest-path distances to the following ten vertices, in order: 7,37,59,82,99,115,133,165,188,
197. You should encode the distances as a comma-separated string of integers. So if you find that all ten of these 
vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000 distance away, then your answer should 
be 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of reporting DOES MATTER, and the string 
should be in the same order in which the above ten vertices are given. Please type your answer in the space provided.

IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn) time implementation of Dijkstra's 
algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing the 
heap-based version. Note this requires a heap that supports deletions, and you'll probably need to maintain some 
kind of mapping between vertices and their positions in the heap.
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DijkstraAlgorithm {
	final private static int MAX_DISTANCE = 1000000;
	private static Graph graph;		// hold input graph
	private static int[] targets = {7,37,59,82,99,115,133,165,188,197}; 
	
	private static void input(String path) {
		System.out.println("Processing input file...");
		BufferedReader br = null;
		String line = null;
		graph = new Graph();
		try {
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				String[] nodes = line.split("\t");
				int from = Integer.parseInt(nodes[0]);
				
				while(from > graph.size()) {	// need create new vertex
					int data = graph.size() + 1;
					graph.addVertex(data);
				}
				
				for(int i = 1; i < nodes.length; i++) {
					String[] tuples = nodes[i].split(",");
					int to = Integer.parseInt(tuples[0]);
					int length = Integer.parseInt(tuples[1]);
					graph.addEdge(from, to, length);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void updateNeighbours(Vertex vert, int[] dist) {
		Edge edge = vert.getFirstArc();
		while(edge != null) {
			Vertex toVert = graph.getVertex(edge.getToVertex());
			int newDist = dist[vert.getIndex()] + edge.getLength();
			if(newDist < dist[toVert.getIndex()]) {
				dist[toVert.getIndex()] = newDist;	// update
			}
			edge = edge.getNextArc();
		}
	}
	
	private static Vertex minVertex(int[] dist) {
		int min = MAX_DISTANCE + 1, minId = -1;
		for(int id = 1; id <= dist.length; id++) {
			Vertex v = graph.getVertex(id);
			if(v.isCovered())
				continue;
			if(dist[id - 1] < min) {
				min = dist[id - 1];
				minId = id;
			}
		}
		return graph.getVertex(minId);
	}
	
	private static int[] dijkstra() {
		System.out.println("Running Dijkstra algorithm...");
		int[] result = new int[targets.length];
		// initial
		Vertex source = graph.getVertex(1);
		source.setCovered();
		int[] dist = new int[graph.size()];
		for(int i = 1; i < dist.length; i++)
			dist[i] = MAX_DISTANCE;
		int covered = 1;
		Vertex last = source;	// record last added vertex
		while(covered < graph.size()) {
			updateNeighbours(last, dist);
			Vertex min = minVertex(dist);
			min.setCovered();
			last = min;
			covered++;
		}
		for(int i = 0; i < targets.length; i++) {
			int id = targets[i];
			result[i] = dist[id - 1];
		}
		return result;
	}
	
	private static void print(int[] result) {
		for(int i = 0; i < result.length; i++) {
			System.out.print(result[i]);
			if(i < result.length - 1)
				System.out.print(",");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please input file path.");
			System.exit(-1);
		}
		input(args[0]);
		// graph.print();
		print(dijkstra());
	}
}