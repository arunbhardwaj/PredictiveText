package wordNGram;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import assignment2.b.IMarkovModel;
import edu.duke.*;

/**
 * A simple runner class to test our random text generation (word-based) algorithm.
 * 
 * @author Arun Bhardwaj 
 * @version (a version number or a date)
 */

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
    public void runMarkov(int size, int seed) {
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
    
    public void runMarkovWord(int size, int seed) throws IOException {
//		FileReader fr = new FileReader(source);
//		String st = fr.toString();
    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";
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

    public void testHashMap() {
    	String source = "this is a test yes this is really a test yes a test this is wow";
    	EfficientMarkovWord markov = new EfficientMarkovWord(2);
    	markov.setRandom(42);
    	markov.setTraining(source);
    	markov.printHashMapInfo();
    	String output = markov.getRandomText(50);
    	printOut(output);
    }
    
    public void compareMethods() {
    	FileResource fr = new FileResource(); 
    	String text = fr.asString();

    	MarkovWord markov = new MarkovWord(2);
    	markov.setTraining(text);
    	long start = System.nanoTime();
    	runModel(markov, text, 100, 42);
    	
    	long middle = System.nanoTime();
    	
    	EfficientMarkovWord eMarkov = new EfficientMarkovWord(2);
    	eMarkov.setTraining(text);
    	
    	// Middle 2 for excluding buildHash time
    	long middle2 = System.nanoTime();
    	runModel(eMarkov, text, 100, 42);
    	
    	long end = System.nanoTime();
		
		long time1 = middle - start;
		long time2 = end - middle;
		
		
		System.out.printf("%s took %d nanoseconds.\n", markov.getName(), time1 );
		System.out.printf("%s took %d nanoseconds.\n", eMarkov.getName(), time2 );
		
		System.out.printf("%s was %d times faster than regular model.\n", eMarkov.getName(), time1/time2 );
		
		
    }
    
    public static void main(String[] args) throws IOException {
    	String source = "C:\\Users\\Arun\\eclipse-workspace\\PredictiveText\\data\\confucius.txt";
		String st = new String(Files.readAllBytes(Paths.get(source)));
		st = st.replace('\n', ' ');
		st = st.trim();
    	
    	MarkovRunner runner = new MarkovRunner();
//    	runner.runMarkovWord(100, 371);
//    	runner.testHashMap();
    	runner.compareMethods();
    	
//    	EfficientMarkovWord test = new EfficientMarkovWord(2);
//    	test.setRandom(65);
//    	test.setTraining(st);
//    	test.printHashMapInfo();
    	
    }
    
}
