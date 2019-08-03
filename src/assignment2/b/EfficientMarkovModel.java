package assignment2.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import assignment2.NumberToWordsConverter;

public class EfficientMarkovModel extends AbstractMarkovModel {
	private static String name = "MarkovModel";
	private int nCharPredict;
	private NumberToWordsConverter c = new NumberToWordsConverter();
	public HashMap<String, ArrayList<String>> hash = new HashMap<>();
	
	public EfficientMarkovModel(int N) {
		nCharPredict = N;
		myRandom = new Random();
		name = "EfficientMarkov" + c.numToWords(N);
	}
	
	public void setTraining(String s) {
		this.myText = s;
		buildMap(nCharPredict);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("This is the EfficientMarkovModel class of order %d.", nCharPredict);
	}
		
	public void buildMap(int stringsize) {
		String key;
		
		//Builds a follows ArrayList<String> for every substring in the text
		for (int i = 0; i <= myText.length() - stringsize; i++) {
			key = myText.substring(i, i + stringsize);
			
			//Checks if the key already exists
			if (!hash.containsKey(key)) {
				hash.put(key, getFollowArrayForHash(key));
			}
		}
//		printHashMapInfo();
	}
	
	private void printHashMapInfo() {
		if (hash.size() < 100) {
			System.out.println(hash.toString());
		} 
		System.out.println("The number of keys in the hash: " + hash.size());
		
		ArrayList<String> max = new ArrayList<String>(0);
		ArrayList<String> maxKeys = new ArrayList<String>();
		
		for (Map.Entry<String, ArrayList<String>> m : hash.entrySet()) {
			if (m.getValue().size() > max.size()) {
				max = m.getValue();
			} 
		}
		
		for (Map.Entry<String, ArrayList<String>> m : hash.entrySet()) {
			if (m.getValue().size() == max.size()) {
				maxKeys.add(m.getKey());
			}
		}
		
		System.out.println("The size of the largest value: " + max.size());
		System.out.println("The key(s) with the largest value: ");
		for (String key : maxKeys) {
			System.out.print("[ " + key + " ]");
		}
	}
	
	private ArrayList<String> getFollowArrayForHash (String key) {
		ArrayList<String> followArray = new ArrayList<String>();
		int index = 0;
		// initial check
		if (myText.contains(key) && !key.isEmpty()) {
			
			/*
			 * While myText contains key from the starting index onwards, continue.
			 bc indexOf will return -1 if there is no key found from the 
			 index onwards. Also checks if the instance containing the key is 
			 at the end of the text and therefore has no next index. In that case, 
			 it skips the loop and returns an empty ArrayList<String>.
			 *
			 */
			while(myText.indexOf(key, index) >= 0 && myText.indexOf(key, index) < myText.length() - key.length()) {
				followArray.add(myText.substring(myText.indexOf(key, index) + key.length(), myText.indexOf(key, index) + key.length() + 1));
				index = myText.indexOf(key, index) + 1;
			} 
		}
		return followArray;
	}
	
	public ArrayList<String> getFollows(String key) {
		return hash.get(key);
	}
	
	public String getRandomText(int numChars) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - nCharPredict);
		
		//Initialize the string with a random selection from the text.
		String key = myText.substring(index, index + nCharPredict);
		sb.append(key);
		
		//Build the string with likely-proceeding characters.
		for (int k=0; k < numChars - nCharPredict; k++) {
			
			//follows = hashmap value
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			sb.append(follows.get(index));
			key = sb.substring(sb.length() - nCharPredict, sb.length());
		}
		
		return sb.toString();
	}
}
