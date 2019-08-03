package assignment2.b;

import java.util.ArrayList;
import java.util.Random;

import assignment2.NumberToWordsConverter;

//Extends the base class to inherit from the super class
public class MarkovModel extends AbstractMarkovModel {
	private static String name = "MarkovModel";
	private int nCharPredict;
	private NumberToWordsConverter c = new NumberToWordsConverter();
	
	public MarkovModel(int N) {
		nCharPredict = N;
		myRandom = new Random();
		name = "Markov" + c.numToWords(N);
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - nCharPredict);
		String key = myText.substring(index, index + nCharPredict);
		sb.append(key);
		
		
		for(int k=0; k < numChars - nCharPredict; k++){
			
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0) {break;}
			index = myRandom.nextInt(follows.size());
			sb.append(follows.get(index));
			key = sb.substring(sb.length() - nCharPredict, sb.length());
		}
		
		return sb.toString();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public String toString() {
		return String.format("MarkovModel of order %d.", nCharPredict);
	}
	
}
