package wordNGram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import assignment2.b.IMarkovModel;

public class WordGram implements IMarkovModel{
	private String[] myText;
	private Random myRandom;
	public HashMap<ArrayList<String>, ArrayList<String>> hash = new HashMap<>(); //Have to initialize the hashmap, or it throws a nullpointerError in buildMap()
	private String name = "MarkovWordOne";
	private int nWords;

	public WordGram() {
		myRandom = new Random();
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
			key.remove(0);
			key.add(follows.get(index));
		
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(ArrayList<String> key) {
		return hash.get(key);
	}
	
	public void buildHash(int nWords) {
		ArrayList<String> key = new ArrayList<String>();
		for (int i = 0; i <= myText.length - nWords; i++) {
			int j = i;
			while (j < i+3 ) {
				key.add(myText[j]);
				j++;
			}
			if ( !hash.containsKey(key) ) {
				buildHash(key);
			}
		}
	}
	
	private void buildHash(ArrayList<String> key) {
		ArrayList<String> follows = new ArrayList<String>();
		for (int index = indexOf(myText, key, 0); index < myText.length - 2; index = indexOf(myText, key, index + 1)) {
			if (index == -1) {
				break;
			}
			follows.add( myText[index + 2] );
		}
		hash.put(key, follows);
	}
	
	private int indexOf(String[] words, ArrayList<String> targets, int start) {
		int wordsIndex = start;
		int i = start;
		int targetsIndex = 0;
		while (wordsIndex < words.length - targets.size() ) {
			//if we have successfully compared targets[j] and words[i] specifically targets.size() times, then we've found a match.
			if (targetsIndex == wordsIndex + targets.size() ) {
				
				return wordsIndex;
			} else if (targets.get(targetsIndex).equalsIgnoreCase(words[i]) ) {
				i++;
				targetsIndex++;
			} else {
				wordsIndex++;
				i = wordsIndex;
				targetsIndex = 0;
			}
		}
		
		return -1;
	}
	
	public void testIndexOf() {
		String test = "this is just a test yes this is a simple test";
		String[] words = test.split("\\s+");
		System.out.printf("The string is: \n| %s |\n", test);
		System.out.println("The length of the string in an array is: " + words.length);
		System.out.println(indexOf(
				words, 
				new ArrayList<String>(Arrays.asList("this", "is")),
				0));
		System.out.println(indexOf(words, 
				new ArrayList<String>(Arrays.asList("this", "is")), 
				3));
		System.out.println(indexOf(words, 
				new ArrayList<String>(Arrays.asList("frog", "and")),
				5));
		System.out.println(indexOf(words, 
				new ArrayList<String>(Arrays.asList("simple", "test")), 
				2));
		System.out.println(indexOf(words, 
				new ArrayList<String>(Arrays.asList("test", "")), 
				5));
		this.myText = words;
		buildHash(2);
		System.out.println(hash.toString());
		
	}

}
