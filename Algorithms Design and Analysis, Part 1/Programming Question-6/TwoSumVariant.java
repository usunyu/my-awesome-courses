/*
Question 1

The goal of this problem is to implement a variant of the 2-SUM algorithm (covered in the Week 6 lecture on hash 
table applications).
The file contains 1 million integers, both positive and negative (there might be some repetitions!).This is your 
array of integers, with the ith row of the file specifying the ith entry of the array.

Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such that there 
are distinct numbers x,y in the input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a one-line 
addition to the algorithm from lecture.)

Write your numeric answer (an integer between 0 and 20001) in the space provided.


OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. For example, 
you could compare performance under the chaining and open addressing approaches to resolving collisions.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class TwoSumVariant {
	private static ArrayList<Long> numbers;
	private final static int DefaultSize = 1000000;

	private static void input(String path) {
		System.out.println("Processing input file...");
		try {
			numbers = new ArrayList<Long>(DefaultSize);
			File file = new File(path);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLong())
				numbers.add(sc.nextLong());
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// using HashSet
	private static int twoSumVariant() {
		System.out.println("Counting two sum...");
		int count = 0;
		for (long target = -10000; target <= 10000; target++) {
			HashSet<Long> set = new HashSet<Long>();
			for (long i : numbers) {
				long need = target - i;
				if (set.contains(need)) {
					if (need != i) {
						count++;
						break;
					}
				}
				set.add(i);
			}
			if (target % 100 == 0)
				System.out.println((double) (target + 10000) / 20000 + "%");
		}
		return count;
	}

	// useing sorting
	private static int twoSumVariant2() {
		System.out.println("Counting two sum...");
		Collections.sort(numbers);
		int count = 0;
		for (long target = -10000; target <= 10000; target++) {
			int start = 0, end = numbers.size() - 1;
			while (start < end) {
				long sum = numbers.get(start) + numbers.get(end);
				if (sum == target) {
					if (numbers.get(start) != numbers.get(end))
						count++;
					break;
				} else if (sum < target) {
					start++;
				} else {
					end--;
				}
			}
			if (target % 100 == 0)
				System.out.println((double) (target + 10000) / 20000 * 100 + "%");
		}
		return count;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please input file path.");
			System.exit(-1);
		}
		input(args[0]);
		System.out.println(twoSumVariant2());
	}
}