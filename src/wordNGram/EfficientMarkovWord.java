package wordNGram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import assignment2.NumberToWordsConverter;
import assignment2.b.IMarkovModel;

public class EfficientMarkovWord implements IMarkovModel {
	private String[] myText;
	private Random myRandom;
	private int myOrder;
	private NumberToWordsConverter c = new NumberToWordsConverter();
	private String name = "EfficientMarkovWord";
	public HashMap<String, ArrayList<String>> hash = new HashMap<>(); //Have to initialize the hashmap, or it throws a nullpointerError in buildMap()

	
	public EfficientMarkovWord() {
		myRandom = new Random();
	}

	public EfficientMarkovWord(int myOrder) {
		this.myOrder = myOrder;
		myRandom = new Random();
		name = name + c.numToWords(myOrder);
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
		buildMap();
	}

	
	public String getRandomText(int numWords) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - myOrder);
		
		//Initialize the string with a singular random selection from the text.
		WordGram key = new WordGram(myText, index, myOrder);
		sb.append(key.toString()).append(" ");

		
		//Build the string with likely-proceeding characters.
		for (int k=0; k < numWords - myOrder; k++) {
			
			//follows
			ArrayList<String> follows = getFollows(key);
			if (follows == null || follows.size() == 0 ) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			sb.append(follows.get(index)).append(" ");
			key = key.shiftAdd(follows.get(index));	
		}
		
		return sb.toString().trim();
	}
	
	
	public ArrayList<String> getFollows(WordGram kGram) {

		return hash.get(kGram.toString() );
	}
	
	/**
	 * Checks the index of a target WordGram within a text and returns the starting index of the 
	 * matching sequence within the text. 
	 * 
	 * @param words source array of text
	 * @param target a WordGram object containing the target sequence
	 * @param start indicates the starting position to start looking through the source array
	 * @return index of matching sequence or -1 if it wasn't found
	 */
	private int indexOf(String[] words, WordGram target, int start) {
		//WordsIndex is to iterate through the words
		// i is to initialize starting variables
		// keyIndex is to iterate through the target WordGram
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
	
	public void buildMap() {
		WordGram key;
		
		// Iterate through myText to get every possible key
		for (int i = 0; i <= myText.length - myOrder; i++) {
			
			// change the key
			key = new WordGram(myText, i, myOrder);

			// Add the key if it hasn't already
			if ( !hash.containsKey(key.toString().toLowerCase()) ) {
				buildHash(key);
			}
		}
	}
	
	/**
	 * Finds the instances within the text for the given key, and 
	 * adds the proceeding word to the follows ArrayList, which 
	 * then gets added to the hashmap once all instances are found.
	 * 
	 * @param key
	 */
	private void buildHash(WordGram key) {
		ArrayList<String> follows = new ArrayList<String>();
		
		// Iterate through the text to find the key
		for (int index = indexOf(myText, key, 0);	index <= myText.length - myOrder; 	index = indexOf(myText, key, index + 1)) {
			
			// Break if the key isn't contained or if the last possible index is reached (which will produce a null follows)
			if (index == -1 || index == myText.length - myOrder) {
				break;
			}
			
			// Add the proceeding word
			follows.add( myText[index + myOrder] );
		}
		
		// Add the array and key to the hash
		hash.put(key.toString(), follows);
	}
	
	public void testIndexOf() {
		String test = "this is just a test yes this is a simple test";
		String[] words = test.split("\\s+");
		String[] sample = new String[]{"This", "is"};;
		WordGram target = new WordGram(sample, 0, sample.length);
		
		System.out.printf("The string is: \n| %s |\n", test);
		System.out.println("The length of the string in an array is: " + words.length);
		System.out.println(indexOf(words, target , 0));
//			sample = new String[] {"this"};
//			target = new WordGram(sample, 0, sample.length);
//			System.out.println(indexOf(words, target, 3));
//			sample = new String[] {"frog"};
//			target = new WordGram(sample, 0, sample.length);
	}
	
	public void printHashMapInfo() {
		
		//Prints the HashMap if the size is small.
		if (hash.size() < 100) {
			System.out.println(hash.toString());
		} 
		
		//Prints total number of keys in the hash.
		System.out.println("The number of keys in the hash: " + hash.size());
		
		int maxSize = 0; //
		ArrayList<String> keysWithMax = new ArrayList<String>();
		
		// Get the max size amongst all entries in the hash
		for (Map.Entry<String, ArrayList<String>> m : hash.entrySet()) {
			if (m.getValue().size() > maxSize) {
				maxSize = m.getValue().size();
			} 
		}
		
		// Get all keys that have the max size
		for (Map.Entry<String, ArrayList<String>> m : hash.entrySet()) {
			if (m.getValue().size() == maxSize) {
				keysWithMax.add(m.getKey());
			}
		}
		
		//Prints the largest value and the keys associated with them.
		System.out.println("The size of the largest value: " + maxSize);
//		System.out.println("The key(s) with the largest value: ");
//		for (String key : keysWithMax) {
//			System.out.println("[" + key + "]");
//			System.out.println(hash.get(key));
//		}
//		
//		System.out.println();
	}

}

