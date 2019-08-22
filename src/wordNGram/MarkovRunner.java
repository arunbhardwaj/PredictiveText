package wordNGram;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A simple runner class to test our random text generation (word-based) algorithm.
 * 
 * @author Arun Bhardwaj 
 * @version (a version number or a date)
 */

import assignment2.b.IMarkovModel;
import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
		runModel(markov,text,size,-1);
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        if (seed != -1) {
        	markov.setRandom(seed);
        }
        System.out.println("running with " + markov.getName()); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
		FileResource fr = new FileResource(); 
		String st = fr.asString(); 
		st = st.replace('\n', ' '); 
		int size = 200;
		
		MarkovWordOne markovWord = new MarkovWordOne(); 
		runModel(markovWord, st, size); 
    } 
    
    /**
     * 
     * @param seed for text generation. Enter -1 for random seed.
     * @param size of text generated.
     */
    public void runMarkov(int seed, int size) {
		FileResource fr = new FileResource(); 
		String st = fr.asString(); 
		st = st.replace('\n', ' ');
		
		MarkovWordOne markovWord = new MarkovWordOne(); 
		if (seed != -1) {markovWord.setRandom(seed);}
		runModel(markovWord, st, size, seed); 
    }

    public void runMarkovTwo() {
    	FileResource fr = new FileResource(); 
		String st = fr.asString(); 
		st = st.replace('\n', ' '); 
		int size = 200;
		
		MarkovWordTwo markovWord = new MarkovWordTwo(); 
		runModel(markovWord, st, size);
    }
    
    /**
     * 
     * @param seed for text generation. Enter -1 for random seed.
     * @param size of text generated.
     */
    public void runMarkovTwo(int seed, int size) {
		FileResource fr = new FileResource(); 
		String st = fr.asString(); 
		st = st.replace('\n', ' ');
		
		MarkovWordOne markovWord = new MarkovWordOne(); 
		if (seed != -1) {markovWord.setRandom(seed);}
		runModel(markovWord, st, size, seed); 
    }
    
    
    
    public void runMarkovWord(int seed, int size) throws IOException {
    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";
//		FileReader fr = new FileReader(source);
//		String st = fr.toString();
		String st = new String(Files.readAllBytes(Paths.get(source)));
		st = st.replace('\n', ' ');
		
		MarkovWord markovWord = new MarkovWord(3); 
		if (seed != -1) {markovWord.setRandom(seed);}
		runModel(markovWord, st, size, seed); 
    }
    
    private void printOut(String s){
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
    	MarkovRunner runner = new MarkovRunner();
    	
    	runner.runMarkovWord(643, 20);
    	
//    	MarkovWord test = new MarkovWord();
//    	test.testIndexOf();
    	
		/*
		 * MarkovWordOne test = new MarkovWordOne();
		 * String source =
		 * "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";
		 * FileResource fr = new FileResource(source); String text = fr.asString();
		 * test.setTraining(text); System.out.println(test.hash.size());
		 */
    
    }
    
}
