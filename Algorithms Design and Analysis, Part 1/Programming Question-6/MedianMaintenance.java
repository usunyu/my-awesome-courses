/*
The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 5 lecture on heap 
applications). The text file contains a list of the integers from 1 to 10000 in unsorted order; you should treat 
this as a stream of numbers, arriving one by one. Letting xi denote the ith number of the file, the kth median mk 
is defined as the median of the numbers x1,…,xk. (So, if k is odd, then mk is ((k+1)/2)th smallest number among 
x1,…,xk; if k is even, then mk is the (k/2)th smallest number among x1,…,xk.)

In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits). That 
is, you should compute (m1+m2+m3+⋯+m10000)mod10000.

OPTIONAL EXERCISE: Compare the performance achieved by heap-based and search-tree-based implementations of the 
algorithm.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MedianMaintenance {
	private static ArrayList<Integer> numbers;
	private final static int DefaultSize = 10000;

	private static void input(String path) {
		System.out.println("Processing input file...");
		try {
			numbers = new ArrayList<Integer>(DefaultSize);
			File file = new File(path);
			Scanner sc = new Scanner(file);
			while (sc.hasNextInt())
				numbers.add(sc.nextInt());
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// size of maxHeap >= size of minHeap
	private static int medianMaintenance() {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(DefaultSize / 2,
				new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						if (a < b) return 1;
						else if (a == b) return 0;
						else return -1;
					}
				});
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		int[] medians = new int[DefaultSize];
		for(int i = 0; i < numbers.size(); i++) {
			int v = numbers.get(i);
			if(maxHeap.isEmpty())
				maxHeap.add(v);
			else {
				if(v < maxHeap.peek()) {
					if(maxHeap.size() > minHeap.size())
						minHeap.add(maxHeap.poll());
					maxHeap.add(v);
				}
				else if(v > minHeap.peek()) {
					if(minHeap.size() == maxHeap.size())
						maxHeap.add(minHeap.poll());
					minHeap.add(v);
				}
				else {
					if(minHeap.size() == maxHeap.size())
						maxHeap.add(v);
					else
						minHeap.add(v);
				}
			}
			medians[i] = maxHeap.peek();
		}
		int sum = 0;
		for(int i = 0; i < medians.length; i++) {
			sum += medians[i];
		}
		return sum % 10000;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please input file path.");
			System.exit(-1);
		}
		input(args[0]);
		System.out.println(medianMaintenance());
	}
}