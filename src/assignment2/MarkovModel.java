package assignment2;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel implements IMarkovModel {
	private static String name = "MarkovModel";
	private int nCharPredict;
	private String myText;
	private Random myRandom;
	private NumberToWordsConverter c = new NumberToWordsConverter();
	
	public MarkovModel(int N) {
		nCharPredict = N;
		myRandom = new Random();
		name = "Markov" + c.numToWords(N);
//		switch (N) {
//		case 0: name = "MarkovZero";
//				break;
//		case 1: name = "MarkoveOne";
//				break;
//		case 2: name = "MarkovTwo";
//				break;
//		case 3:	name = "MarkovThree";
//				break;
//		case 4: name = "MarkovFour";
//				break;
//		}
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
	
	public ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<String>();
		int index = 0;
		// initial check
		if (myText.contains(key) && !key.isEmpty()) {
			/*
			 While myText contains key from the index onwards, continue.
			 bc indexOf will return -1 if there is no key found from the 
			 index onwards. Also checks to make sure that the next
			 instance containing the key is at the end of the text and
			 therefore has no next index.
			 *
			 */
			while(myText.indexOf(key, index) >= 0 && myText.indexOf(key, index) < myText.length() - key.length()) {
				follows.add(myText.substring(myText.indexOf(key, index) + key.length(), myText.indexOf(key, index) + key.length() + 1));
				index = myText.indexOf(key, index) + 1;
			} 
		}
		return follows;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
