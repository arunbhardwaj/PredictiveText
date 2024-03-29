package assignment2.b;

/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel {
	private static final String name = "MarkovZero";
	
	public MarkovZero() {
		myRandom = new Random();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < numChars; k++){
			int index = myRandom.nextInt(myText.length());
			sb.append(myText.charAt(index));
		}
		
		return sb.toString();
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return "MarkovModel of order 0";
	}

}
