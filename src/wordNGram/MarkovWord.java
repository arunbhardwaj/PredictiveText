package wordNGram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import assignment2.b.IMarkovModel;

public class MarkovWord implements IMarkovModel {
	private String[] myText;
	private Random myRandom;
	private int myOrder;
	
	private String name = "MarkovWord";
	public HashMap<String, ArrayList<String>> hash = new HashMap<>(); //Have to initialize the hashmap, or it throws a nullpointerError in buildMap()

	
	public MarkovWord() {
		myRandom = new Random();
	}

	public MarkovWord(int myOrder) {
		myRandom = new Random();
		this.myOrder = myOrder;
	}

	@Override
	public String getName() {
		return name;
	}


	@Override
	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}


	@Override
	public void setTraining(String text) {
		myText = text.split("\\s+"); 
	}

	@Override
	public String getRandomText(int numWords) {
		if (myText == null) {
			return "";
		}
		
		// Declare and Initialize the variables
		StringBuilder sb = new StringBuilder();
		String space = new String(" ");
		String word = new String();
		String[] words = new String[myOrder];
		int index;
		
		
		//Add a random selection of words from the text to 
		//the StringBuilder and to the key array
		for (int i = 0; i < myOrder; i++) {
			index = myRandom.nextInt(myText.length);
			word = myText[index];
			sb.append(word).append(space);
			words[i] = word;
		}
		
		
		// Key WordGram
		WordGram key = new WordGram(words, 0, words.length);
		
		
		//Build the string with likely-proceeding characters.
		for (int k=0; k < numWords - 1; k++) {
			
			//follows = hashmap value
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			sb.append(follows.get(index)).append(space);
			key.shiftAdd(follows.get(index));
			
			
		
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(WordGram kGram) {
		ArrayList<String> follows = new ArrayList<String>();
		// iterate through the text to find 
		for (int index = indexOf(myText, kGram, 0);	index <= myText.length - myOrder; 	index = indexOf(myText, kGram, index + 1)) {
			if (index == -1 || index == myText.length - myOrder) {
				break;
			}
			follows.add( myText[index + myOrder] );
		}
		return follows;
	}
	
	private int indexOf(String[] words, WordGram target, int start) {
		int wordsIndex = start;
		int i = start;
		int keyIndex = 0;
		while (wordsIndex <= words.length - target.length() ) {
			//if we have successfully compared key[j] and words[i] specifically key.size() times, then we've found a match.
			if (keyIndex == target.length() ) {
				
				return wordsIndex;
			} else if (target.wordAt(keyIndex).equalsIgnoreCase(words[i]) ) {
				i++;
				keyIndex++;
			} else {
				wordsIndex++;
				i = wordsIndex;
				keyIndex = 0;
			}
		}
		
		return -1;
	}
	
	public void testIndexOf() {
		String test = "this is just a test yes this is a simple test";
		String[] words = test.split("\\s+");
		System.out.printf("The string is: \n| %s |\n", test);
		System.out.println("The length of the string in an array is: " + words.length);
		String[] sample = new String[]{"This", "is"};;
		WordGram target = new WordGram(sample, 0, sample.length);
		System.out.println(indexOf(words, target , 0));
//		System.out.println(indexOf(words, "this", 3));
//		System.out.println(indexOf(words, "frog", 5));
//		System.out.println(indexOf(words, "simple", 2));
//		System.out.println(indexOf(words, "test", 5));
		this.myText = words;
	}

}