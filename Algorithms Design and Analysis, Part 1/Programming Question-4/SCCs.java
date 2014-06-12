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

public class SCCs {
	private static Graph graph;					// hold input graph
	
	public static void input(String path) {
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
				if(graph.size() % 1000 == 0)
					System.out.println((double)graph.size() / 875714 * 100 + "%");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please input file path.");
			System.exit(-1);
		}
		input(args[0]);
		graph.print();
	}
}