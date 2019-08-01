package assignment2.b;

public interface IMarkovModel {
	public String getName();
	public void setRandom(int seed);
	public void setTraining(String st);
	public String getRandomText(int numChars);
}
