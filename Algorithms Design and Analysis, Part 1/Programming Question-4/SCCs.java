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

JVM params to run with: -Xms512m -Xmx1024m -Xss256m
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class SCCs {
	private static Graph graph;		// hold input graph
	private static Graph graphR;	// hold reverse graph
	private static int t;			// # of nodes processed so far
	private static Vertex s;		// for leaders in 2nd process
	private static Stack<Vertex> stack;	// for finish time
	
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
	
	private static void firstDFSLoop() {
		System.out.println("First DFS-Loop...");
		t = 0;
		stack = new Stack<Vertex>();
		for(int id = graphR.size(); id >= 1; id--) {
			Vertex vertex = graphR.getVertex(id);
			if(!vertex.isExplored()) {
				firstDFSProcess(vertex);
			}
		}
	}
	
	private static void firstDFSProcess(Vertex vertex) {
		vertex.setExplored();
		Edge edge = vertex.getFirstArc();
		while(edge != null) {
			Vertex toVertex = graphR.getVertex(edge.getToVertex());
			if(!toVertex.isExplored()) {
				firstDFSProcess(toVertex);
			}
			edge = edge.getNextArc();
		}
		t++;
		Vertex v = graph.getVertex(vertex.getId());
		stack.push(v);
	}
	
	private static void secondDFSLoop() {
		System.out.println("Second DFS-Loop...");
		s = null;
		while(!stack.isEmpty()) {
			Vertex vertex = stack.pop();
			s = vertex;
			if(!vertex.isExplored()) {
				secondDFSProcess(vertex);
			}
		}
	}
	
	private static void secondDFSProcess(Vertex vertex) {
		vertex.setExplored();
		vertex.setLeader(s);
		Edge edge = vertex.getFirstArc();
		while(edge != null) {
			Vertex toVertex = graph.getVertex(edge.getToVertex());
			if(!toVertex.isExplored()) {
				secondDFSProcess(toVertex);
			}
			edge = edge.getNextArc();
		}
	}
	
	private static int[] SCC() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(Vertex vertex : graph.getVertices()) {
			int leader = vertex.getLeaderId();
			if(map.containsKey(leader)) {
				map.put(leader, map.get(leader) + 1);
			}
			else {
				map.put(leader, 1);
			}
		}
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 > o2) return -1;
				else if(o1 < o2) return 1;
				else return 0;
			}
		});
		
		for(int v : map.values()) {
			heap.add(v);
		}
		int[] result = new int[5];
		int i = 0;
		while(!heap.isEmpty() && i < result.length) {
			int v = heap.poll();
			result[i++] = v;
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
		//graph.print();
		generateReverseGraph();
		//graphR.print();
		firstDFSLoop();
		secondDFSLoop();
		print(SCC());
	}
}
