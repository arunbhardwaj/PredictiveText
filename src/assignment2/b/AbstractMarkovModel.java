package assignment2.b;

/**
 * Abstract class AbstractMarkovModel
 * 
 * @author Arun Bhardwaj
 * @version 8.2.2019
 */

import java.util.*;

//Implements for Interfaces
public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
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
    
    //This method varies for each markov model.
    abstract public String getRandomText(int numChars);
    
    //This method varies for each markov model.
    abstract public String toString();
    
    //This method varies for each markov model.
    abstract public String getName();
    
    /*
     * public = all classes
     * private = only this class
     * protected = this package + subclasses
     * abstract = you must implement + you can have non-static and non-final fields.
     * 			you can have protected and private methods.
     * interfaces = you must override and implement the methods
     * 			fields are public, static, AND final
     * 			methods are public
     */
}
