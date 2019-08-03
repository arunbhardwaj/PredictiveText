package assignment2.b;

import java.util.ArrayList;

/**
 * Simple running class.
 * 
 * @author Duke & Arun Bhardwaj
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
	 public void runMarkov() {
	    	runMarkov(-1);
	    }
	    
	public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        if (seed != -1) {
        	markov.setRandom(seed);
        }
        System.out.println("running with " + markov.getName());
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov(int seed) {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        System.out.println(mFour.getName() + " the name is blahbalbhablahb");
        runModel(mFour, st, size, seed);

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
		EfficientMarkovModel markov = new EfficientMarkovModel(2);
		markov.setRandom(42);
		markov.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
		System.out.println(markov.toString());
		markov.buildMap(2);
		
        for(int k=0; k < 3; k++){
			String st = markov.getRandomText(50);
			printOut(st);
		}
	}
	
	public void compareMethods(int order, int seed) {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		
		long start = System.nanoTime();
		MarkovModel markov = new MarkovModel(order);
		runModel(markov, st, size, seed);
		long middle = System.nanoTime();
		
		EfficientMarkovModel eMarkov = new EfficientMarkovModel(order);
		runModel(eMarkov, st, size, seed);
		long end = System.nanoTime();
		
		long time1 = middle - start;
		long time2 = end - middle;
		
		
		System.out.printf("%s took %d nanoseconds.\n", markov.getName(), time1 );
		System.out.printf("%s took %d nanoseconds.\n", eMarkov.getName(), time2 );
		
		System.out.printf("%s was %d times faster than regular model.\n", eMarkov.getName(), time1/time2 );
		
		
	}
	
	public static void main(String[] args) {
		MarkovRunnerWithInterface test = new MarkovRunnerWithInterface();
		//test.runMarkov(2);
//		test.testHashMap();
		test.compareMethods(2, 42);
	}
	
}
