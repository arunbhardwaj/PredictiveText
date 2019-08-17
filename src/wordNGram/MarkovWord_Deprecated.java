package wordNGram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import assignment2.b.IMarkovModel;

/**
 * Initial from-scratch implementation of N-order Markov Word text generation.
 * Issues: 
 * - uses mutable ArrayList's as keys in a hashmap (immutable objects preferred)
 * - unable to generate random text due to keys that have no values 
 * 
 * @author Arun
 *
 */
public class MarkovWord_Deprecated implements IMarkovModel{
	private String[] myText;
	private Random myRandom;
	public HashMap<ArrayList<String>, ArrayList<String>> hash = new HashMap<>(); //Have to initialize the hashmap, or it throws a nullpointerError in buildMap()
	private String name = "MarkovWordOne";
	private int nWords;

	public MarkovWord_Deprecated() {
		Scanner in = new Scanner(System.in);
		myRandom = new Random();
		System.out.println("How many words should the text be based off of?");
		nWords = Integer.parseInt(in.next());
		in.close();
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
		buildHash(nWords);
	}

	public void setTraining(String[] text) {
		myText = text;
		buildHash(nWords);
	}

	@Override
	public String getRandomText(int numWords) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		
		//Initialize the string with a random selection from the text.
		String space = new String(" ");
		String word = new String();
		ArrayList<String> key = new ArrayList<>();
		
		int index;
		for (int i = 0; i < nWords; i++) {
			index = myRandom.nextInt(myText.length);
			word = myText[index];
			sb.append(word).append(space);
			key.add(word);
		}		
		
		
		//Build the string with likely-proceeding characters.
		for (int k=0; k < numWords - nWords; k++) {
			
			//follows = hashmap value
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			sb.append(follows.get(index)).append(space);
			key.add(follows.get(index));
			key.remove(0);
		
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(ArrayList<String> key) {
		return hash.get(key);
	}
	
	/**
	 * Builds the hashmap for the WordGram object. Iterates through 
	 * myText and generates keys up to the final amount for the number 
	 * of words you want to the predictive text algorithm to run on, 
	 * with the last entry containing a null. 
	 *
	 * @param nWords to base the prediction algorithm on
	 */
	public void buildHash(int nWords) {
		ArrayList<String> key = new ArrayList<String>();
		
		// i iterates through myText
		for (int i = 0; i <= myText.length - nWords; i++) {
			
			// j is the first word of the key to be generated
			int j = i;
			while (j < i + nWords ) {
				key.add(myText[j]);
				j++;
			}
			if ( !hash.containsKey(key) ) {
				buildHash(new ArrayList<String>(key));
//				System.out.println(key);
			}
			key.clear();
		}
	}
	
	/**
	 * Finds the instances within the text for the given key, and 
	 * adds the proceeding word to the follows ArrayList, which 
	 * then gets added to the hashmap once all instances are found.
	 * 
	 * @param key
	 */
	private void buildHash(ArrayList<String> key) {
		ArrayList<String> follows = new ArrayList<String>();
		// iterate through the text to find 
		for (int index = indexOf(myText, key, 0);	index <= myText.length - nWords; 	index = indexOf(myText, key, index + 1)) {
			if (index == -1 || index == myText.length - nWords) {
				break;
			}
			follows.add( myText[index + nWords] );
		}
		hash.put(key, follows);
	}
	
	/**
	 * Finds the index of a target key within a text, 
	 * starting at a specified start position.
	 * 
	 * @param words
	 * @param key
	 * @param start
	 * @return
	 */
	private int indexOf(String[] words, ArrayList<String> key, int start) {
		int wordsIndex = start;
		int i = start;
		int keyIndex = 0;
		while (wordsIndex <= words.length - key.size() ) {
			//if we have successfully compared key[j] and words[i] specifically key.size() times, then we've found a match.
			if (keyIndex == key.size() ) {
				
				return wordsIndex;
			} else if (key.get(keyIndex).equalsIgnoreCase(words[i]) ) {
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
		System.out.printf("The string is: \n| %s |\n\n", test);
		System.out.println("The length of the string in an array is: " + words.length);
		System.out.println(indexOf(
				words, 
				new ArrayList<String>(Arrays.asList("this", "is")),
				0));
		System.out.println(indexOf(words, new ArrayList<String>(Arrays.asList("this", "is")), 3));
		System.out.println(indexOf(words, 
				new ArrayList<String>(Arrays.asList("frog", "and")),
				5));
		System.out.println(indexOf(words, 
				new ArrayList<String>(Arrays.asList("simple", "test")), 
				2));
		System.out.println(indexOf(words, 
				new ArrayList<String>(Arrays.asList("test", "")), 
				5));
		this.setTraining(words);
		System.out.println("The size of the hash is " + hash.size());
		System.out.println(hash.toString());
		
	}

}
