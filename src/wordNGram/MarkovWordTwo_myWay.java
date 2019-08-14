package wordNGram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import assignment2.b.IMarkovModel;

public class MarkovWordTwo_myWay implements IMarkovModel {
	private String[] myText;
	private Random myRandom;
	public HashMap<String, ArrayList<String>> hash = new HashMap<>(); //Have to initialize the hashmap, or it throws a nullpointerError in buildMap()
	private String name = "MarkovWordOne";

	public MarkovWordTwo_myWay() {
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
		buildHash();
	}


	@Override
	public String getRandomText(int numWords) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length);
		
		//Initialize the string with a random selection from the text.
		String key = myText[index];
		String space = new String(" ");
		sb.append(key).append(space);
		
		//Build the string with likely-proceeding characters.
		for (int k=0; k < numWords - 1; k++) {
			
			//follows = hashmap value
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			sb.append(follows.get(index)).append(space);
			key = follows.get(index);
			
			
		
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
		return hash.get(key);
	}
	
	public void buildHash() {
		
		for (int i = 0; i <= myText.length - 2; i++) {
			
			if ( !hash.containsKey(myText[i]) ) {
				buildHash(myText[i], myText[i+1]);
			}
		}
	}
	
	private void buildHash(String key1, String key2) {
		ArrayList<String> follows = new ArrayList<String>();
		for (int index = indexOf(myText, key1, 0); index < myText.length - 2; index = indexOf(myText, key1, index + 1)) {
			if (index == -1) {
				break;
			}
			if (key2.equalsIgnoreCase(myText[index + 1])) {
				follows.add( myText[index + 2] );
			}
		}
		hash.put(key1 + " " + key2, follows);
	}
	
	private int indexOf(String[] words, String target, int start) {
		
		for (int i = start; i < words.length; i++) {
			if (target.equalsIgnoreCase(words[i])) {
				return i;
			} 
		}
		return -1;
	}
	
	public void testIndexOf() {
		String test = "this is just a test yes this is a simple test";
		String[] words = test.split("\\s+");
		System.out.printf("The string is: \n| %s |\n", test);
		System.out.println("The length of the string in an array is: " + words.length);
		System.out.println(indexOf(words, "this", 0));
		System.out.println(indexOf(words, "this", 3));
		System.out.println(indexOf(words, "frog", 5));
		System.out.println(indexOf(words, "simple", 2));
		System.out.println(indexOf(words, "test", 5));
		this.myText = words;
		buildHash();
		System.out.println(hash.toString());
		
	}
}
