/*
Your task is to code up and run the randomized contraction algorithm for the min cut problem and use it on the above
graph to compute the min cut. (HINT: Note that you'll have to figure out an implementation of edge contractions. 
Initially, you might want to do this naively, creating a new graph from the old every time there's an edge 
contraction. But you should also think about more efficient implementations.) (WARNING: As per the video lectures, 
please make sure to run the algorithm many times with different random seeds, and remember the smallest cut that you 
ever find.) Write your numeric answer in the space provided. So e.g., if your answer is 5, just type 5 in the space 
provided.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RandomizedContraction {
	private static int DefaultSize = 200;
	private static Graph graph;					// hold input graph
	private static ArrayList<Edge> edgePool;	// edges for random select
	
	public static void input(String path) {
		BufferedReader br = null;
		String line = null;
		graph = new Graph(DefaultSize);
		try {
			br = new BufferedReader(new FileReader(path));
			while ((line = br.readLine()) != null) {
				String[] nodes = line.split("\t");
				int data = Integer.parseInt(nodes[0]);
				graph.addVertex(data);
				ArrayList<Integer> edges = new ArrayList<Integer>();
				for(int i = 1; i < nodes.length; i++) {
					edges.add(Integer.parseInt(nodes[i]));
				}
				graph.addEdges(data - 1, edges);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readEdges() {
		edgePool = new ArrayList<Edge>();
		Vertex[] vertexs = graph.getVertexs();
		for(Vertex vertex : vertexs) {
			Edge edge = vertex.getFirstArc();
			while(edge != null) {
				// we just need one of two directions edge
				if(edge.getFromVertex() < edge.getToVertex()) {
					edgePool.add(edge);
				}
				edge = edge.getNextArc();
			}
		}
	}
	
	public static int randomizedContraction() {

		
		return 0;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please input file path.");
			System.exit(-1);
		}
		input(args[0]);
		// graph.print();
		readEdges();
		// System.out.println(edgePool.size());
		System.out.println(randomizedContraction());
	}
}