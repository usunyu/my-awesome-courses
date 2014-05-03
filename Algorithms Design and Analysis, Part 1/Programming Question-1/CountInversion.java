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
	private static ArrayList<Integer> input;

	private static void input(String path) {
		try {
			File file = new File(path);
			Scanner sc = new Scanner(file);
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if(args.length == 0) {
			System.err.println("Please input file path.");
			System.exit(-1);
		}
		input(args[0]);
	}
}