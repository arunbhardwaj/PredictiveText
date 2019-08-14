package assignment1;

/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import assignment2.MarkovFour;
import assignment2.MarkovModel;

//import edu.duke.*;

public class MarkovRunner {
    public static void runMarkovZero() throws IOException {
    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";
//		FileReader fr = new FileReader(source);
//		String st = fr.toString();
		String st = new String(Files.readAllBytes(Paths.get(source)));
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setRandom(88);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
    
    public static void runMarkovOne() throws IOException {
//    	Alice
//    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\alice.txt";
    	
//    	Confucius
    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";
    	
    	
    	String st = new String(Files.readAllBytes(Paths.get(source)));
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setRandom(273);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
    
    public static void runMarkovTwo() throws IOException {
//    	Alice
//    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\alice.txt";
    	
//    	Confucius
//    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";

//    	Romeo
    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\romeo.txt";

    	
    	String st = new String(Files.readAllBytes(Paths.get(source)));
		st = st.replace('\n', ' ');
		MarkovTwo markov = new MarkovTwo();
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	
    public static void runMarkovThree() throws IOException {
//    	Alice
//    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\alice.txt";
    	
//    	Confucius
//    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";

//    	Romeo
    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\romeo.txt";

    	
    	String st = new String(Files.readAllBytes(Paths.get(source)));
		st = st.replace('\n', ' ');
		MarkovThree markov = new MarkovThree();
		markov.setRandom(42);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
    
    public static void runMarkovFour() throws IOException {
//    	Alice
//    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\alice.txt";
    	
//    	Confucius
    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";

//    	Romeo
//    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\romeo.txt";

    	
    	String st = new String(Files.readAllBytes(Paths.get(source)));
		st = st.replace('\n', ' ');
		MarkovFour markov = new MarkovFour();
		markov.setRandom(371);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
    
    public static void runMarkovModel() throws IOException {
//    	Alice
//    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\alice.txt";
    	
//    	Confucius
    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";

//    	Romeo
//    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\romeo.txt";

    	
    	String st = new String(Files.readAllBytes(Paths.get(source)));
		st = st.replace('\n', ' ');
		MarkovModel markov = new MarkovModel(8);
		markov.setRandom(365);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
    
	private static void printOut(String s) {
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.print("_");
		System.out.println("\n----------------------------------");
	}
	
	public static void main(String[] args) throws IOException {
//		runMarkovZero();
//		runMarkovOne();
//		runMarkovTwo();
//		runMarkovThree();
//		runMarkovFour();
		runMarkovModel();
	}
}
