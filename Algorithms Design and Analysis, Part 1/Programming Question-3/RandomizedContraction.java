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
import java.util.Random;

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
		int count = graph.getVertexs().length;
		int size = edgePool.size();
		Random seed = new Random();
		int index, from, to, v, d;
		Edge edge, e1, e2, temp;
		Vertex fromVertex, toVertex, v1, v2;
		//int i = 13;
		while(count > 2) {	// contraction until left two vertices
			do {
				index = seed.nextInt(size);
				//index = i--;
				edge = edgePool.get(index);
				from = edge.getFromVertex();
				to = edge.getToVertex();
				fromVertex = graph.getVertexs()[from - 1];
				toVertex = graph.getVertexs()[to - 1];
			} while(!edge.isActive() || !fromVertex.isActive() || !toVertex.isActive());	// chose a valid edge
			edge.deactivate();	// do not chose next time
			
			// contract (from, to) -> from
			toVertex.deactivate();
			
			// process to vertex's edge
			e1 = toVertex.getFirstArc();
			while(e1 != null) {
				d = e1.getToVertex();
				v2 = graph.getVertexs()[d - 1];
				if(d == from || !e1.isActive() || !v2.isActive()) {
					e1 = e1.getNextArc();
					continue;
				}
				// set outgoing edge
				temp = e1.getNextArc();
				e1.setFromVertex(from);
				e1.setNextArc(null);
				fromVertex.addEdge(e1);
				// set incoming edge
				v = e1.getToVertex();
				v1 = graph.getVertexs()[v - 1];
				e2 = v1.getFirstArc();
				while(e2 != null) {
					if(e2.getToVertex() == to) {
						e2.setToVertex(from);
						break;
					}
					e2 = e2.getNextArc();
					//System.out.println(e2);
				}
				e1 = temp;
			}
			count--;
			//System.out.println(count);
		}
		int cut = 0, data1 = -1, data2 = -1;
		for(Vertex vertex : graph.getVertexs()) {
			if(vertex.isActive()) {
				if(data1 == -1) {
					data1 = vertex.getData();
				}
				else if(data2 == -1) {
					data2 = vertex.getData();
					break;
				}
			}
		}
		Vertex vertex1 = graph.getVertexs()[data1 - 1]; //, vertex2 = graph.getVertexs()[data2 - 1];
		edge = vertex1.getFirstArc();
		while(edge != null) {
			if(edge.getToVertex() == data2) {
				cut++;
			}
			edge = edge.getNextArc();
		}
		/*
		edge = vertex2.getFirstArc();
		while(edge != null) {
			if(edge.getToVertex() == data1) {
				minCut--;
			}
			edge = edge.getNextArc();
		}
		*/
		return cut;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please input file path.");
			System.exit(-1);
		}
		int minCut = Integer.MAX_VALUE;
		for(int i = 0; i < DefaultSize; i++) {
			input(args[0]);
			// graph.print();
			readEdges();
			// System.out.println(edgePool.size());
			int cut = randomizedContraction();
			//System.out.println(cut);
			if(minCut > cut)
				minCut = cut;
		}
		System.out.println(minCut);
	}
}