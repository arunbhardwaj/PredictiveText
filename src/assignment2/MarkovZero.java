package assignment2;

/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;

import assignment2.IMarkovModel;

public class MarkovZero implements IMarkovModel {
	private static final String name = "MarkovZero";
    private String myText;
	private Random myRandom;
	
	public MarkovZero() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
