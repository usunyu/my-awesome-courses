/*
Your task is to compute the number of inversions in the file given, where the ith row of the file indicates the 
ith entry of an array.

Because of the large size of this array, you should implement the fast divide-and-conquer algorithm covered in 
the video lectures. The numeric answer for the given input file should be typed in the space below.

So if your answer is 1198233847, then just type 1198233847 in the space provided without any space / commas / 
any other punctuation marks. You can make up to 5 attempts, and we'll use the best one for grading.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CountInversion {
	private static ArrayList<Integer> numbers;
	private static int DefaultSize = 100000;

	private static void input(String path) {
		try {
			numbers = new ArrayList<Integer>(DefaultSize);
			File file = new File(path);
			Scanner sc = new Scanner(file);
			while(sc.hasNextInt())
				numbers.add(sc.nextInt());
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static long bruteForce() {
		long count = 0;
		for(int i = 0; i < numbers.size(); i++) {
			for(int j = i + 1; j < numbers.size(); j++) {
				if(numbers.get(j) < numbers.get(i))
					count++;
			}
		}
		return count;
	}
	
	private static long merge(int start, int mid, int end) {
		long count = 0;
		int p1 = start, p2 = mid;
		ArrayList<Integer> temp = new ArrayList<Integer>(end - start + 1);
		while(p1 < mid && p2 <= end) {
			if(numbers.get(p1) < numbers.get(p2)) {
				temp.add(numbers.get(p1++));
			}
			else {
				temp.add(numbers.get(p2++));
				count += (mid - p1);	// count inversions
			}
		}
		while(p1 < mid)	// if left part not finish
			temp.add(numbers.get(p1++));
		int p = start;
		for(int i : temp)	// copy sorted list to original list
			numbers.set(p++, i);
		return count;
	}

	private static long divideAndConquer(int left, int right) {
		if(left >= right) {
			return 0;
		}
		else {
			int mid = left + (right - left) / 2;
			long leftInvs = divideAndConquer(left, mid);
			long rightInvs = divideAndConquer(mid + 1, right);
			long currentInvs = merge(left, mid + 1, right);
			return leftInvs + rightInvs + currentInvs;
		}
	}
	
	private static long divideAndConquer() {
		return divideAndConquer(0, numbers.size() - 1);
	}

	public static void main(String[] args) {
		if(args.length == 0) {
			System.err.println("Please input file path.");
			System.exit(-1);
		}
		input(args[0]);
		
		long count, begin, end;
		
		begin = System.currentTimeMillis();
		count = bruteForce();
		end = System.currentTimeMillis();
		System.out.println("Brute Force: " + count + ", runtime " + (end - begin) + " ms");
		
		begin = System.currentTimeMillis();
		count = divideAndConquer();
		end = System.currentTimeMillis();
		System.out.println("Divide and Conquer: " + count + ", runtime " + (end - begin) + " ms");
	}
}