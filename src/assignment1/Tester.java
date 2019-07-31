package assignment1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Tester {

	public Tester() {
		// TODO Auto-generated constructor stub
	}
	
	public static void testGetFollows() {
		MarkovTwo markov = new MarkovTwo();
		markov.setTraining("this is a test yes this is a test.");
		ArrayList<String> result = markov.getFollows("t.");
		
		print(result);
		
	}

	public static void testGetFollowsWithFile() throws IOException {
		MarkovOne markov = new MarkovOne();
		
		String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\melville.txt";
		String st = new String(Files.readAllBytes(Paths.get(source)));
		st = st.replace('\n', ' ');
		markov.setTraining(st);
		
		ArrayList<String> result = markov.getFollows("o");
		print(result);
	}
	
	public static void print(ArrayList<String> result) {
		int numOfChars = 0;
		for (String s : result) {
			if (result.get(result.size() - 1) == s) {
				System.out.println(s + "_");
			} else {
				System.out.print(s + ",");
			}
			numOfChars++;
		}
		System.out.println("The number of chars is: " + numOfChars);
	}
	
	public static void main(String[] args) throws IOException {
		testGetFollowsWithFile();
	}

}
