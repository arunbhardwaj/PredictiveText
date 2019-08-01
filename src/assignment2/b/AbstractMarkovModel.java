package assignment2.b;

/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
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
}
