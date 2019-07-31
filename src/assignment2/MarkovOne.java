package assignment2;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne implements IMarkovModel {
	private static final String name = "MarkovOne";
	private String myText;
	private Random myRandom;
	
	public MarkovOne() {
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
		int index = myRandom.nextInt(myText.length() - 1);
		sb.append(myText.charAt(index));
		
		for(int k=0; k < numChars - 1; k++){
			
			ArrayList<String> follows = getFollows(Character.toString(sb.charAt(k)));
			index = myRandom.nextInt(follows.size());
			sb.append(follows.get(index));
		}
		
		return sb.toString();
	}
	
	public ArrayList<String> getFollows(String key) {
		ArrayList<String> result = new ArrayList<String>();
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
				result.add(myText.substring(myText.indexOf(key, index) + key.length(), myText.indexOf(key, index) + key.length() + 1));
//				Whether you add 1 or key.length() poses an interesting question about the repetition
//				of characters in languages. To allow for repeating identical sequences, adding 1 would be ideal?
				index = myText.indexOf(key, index) + 1;
			} 
		}
		return result;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
