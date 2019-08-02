package assignment2.b;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {
	private static final String name = "MarkovOne";
	
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public String toString() {
		return "MarkovModel of order 1";
	}
	
}
