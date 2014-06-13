/*
The file contains the edges of a directed graph. Vertices are labeled as positive integers from 1 to 875714. 
Every row indicates an edge, the vertex label in first column is the tail and the vertex label in second column 
is the head (recall the graph is directed, and the edges are directed from the first column vertex to the second 
column vertex). So for example, the 11th row looks liks : "2 47646". This just means that the vertex with label 2 
has an outgoing edge to the vertex with label 47646

Your task is to code up the algorithm from the video lectures for computing strongly connected components (SCCs), 
and to run this algorithm on the given graph. 

Output Format: You should output the sizes of the 5 largest SCCs in the given graph, in decreasing order of sizes, 
separated by commas (avoid any spaces). So if your algorithm computes the sizes of the five largest SCCs to be 500, 
400, 300, 200 and 100, then your answer should be "500,400,300,200,100". If your algorithm finds less than 5 SCCs, 
then write 0 for the remaining terms. Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300, and 
100, then your answer should be "400,300,100,0,0".

WARNING: This is the most challenging programming assignment of the course. Because of the size of the graph you 
may have to manage memory carefully. The best way to do this depends on your programming language and environment, 
and we strongly suggest that you exchange tips for doing this on the discussion forums.
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

class SCCs {
	private static Graph graph;		// hold input graph
	private static Graph graphR;	// hold reverse graph
	private static int t;			// # of nodes processed so far
	private static Vertex s;		// for leaders in 2nd process
	private static PriorityQueue<Integer> heap;	// for scc
	
	private static void input(String path) {
		System.out.println("Processing input file...");
		BufferedReader br = null;
		String line = null;
		graph = new Graph();
		try {
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				String[] nodes = line.split(" ");
				int from = Integer.parseInt(nodes[0]);
				int to = Integer.parseInt(nodes[1]);
				while(from > graph.size()) {	// need create new vertex
					int data = graph.size() + 1;
					graph.addVertex(data);
				}
				graph.addEdge(from, to);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void generateReverseGraph() {
		System.out.println("Generate reverse graph...");
		graphR = new Graph();
		for(int i = 1; i <= graph.size(); i++) {
			graphR.addVertex(i);
		}
		for(Vertex v : graph.getVertices()) {
			Edge e = v.getFirstArc();
			while(e != null) {
				graphR.addEdge(e.getToVertex(), e.getFromVertex());	// add reverse edge
				e = e.getNextArc();
			}
		}
	}
	
	private static void DFSLoop(Graph G, boolean flag) {
		if(flag) {
			System.out.println("First DFS-Loop...");
			t = 0;
		}
		else {
			System.out.println("Second DFS-Loop...");
			s = null;
			heap = new PriorityQueue<Integer>();
		}
		int n = G.size();
		for(int id = n; id >= 1; id--) {
			Vertex vertex = G.getVertex(id);
			if(!vertex.isExplored()) {
				if(!flag)
					s = vertex;
				DFS(G, vertex.getId(), flag);
			}
		}
	}
	
	private static void DFS(Graph G, int id, boolean flag) { 
		Vertex vertex = G.getVertex(id);
		vertex.setExplored();
		if(!flag)
			vertex.setLeader(s);
		Edge edge = vertex.getFirstArc();
		while(edge != null) {
			Vertex toVertex = G.getVertex(edge.getToVertex());
			if(!toVertex.isExplored()) {
				DFS(G, toVertex.getId(), flag);
			}
			edge = edge.getNextArc();
		}
		if(flag) {	// in the first DFS process
			t++;
			vertex = graph.getVertex(id);
			vertex.setOtherId(t);
		}
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please input file path.");
			System.exit(-1);
		}
		input(args[0]);
		//graph.print();
		generateReverseGraph();
		//graphR.print();
		DFSLoop(graphR, true);
		DFSLoop(graph, false);
	}
}
