package assignment2.b;

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel {
	private static final String name = "MarkovFour";
	
	public MarkovFour() {
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
		int index = myRandom.nextInt(myText.length() - 4);
		String key = myText.substring(index, index + 4);
		sb.append(key);
		
		
		for(int k=0; k < numChars - 4; k++){
			
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0) {break;}
			index = myRandom.nextInt(follows.size());
			sb.append(follows.get(index));
			key = sb.substring(sb.length() - 4, sb.length());
		}
		
		return sb.toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}
