package wordNGram;


public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
    	
        return myWords.length;
    }

    public String toString(){
        StringBuilder ret = new StringBuilder("");
        for (int i = 0; i < length(); i++) {
        	ret.append(myWords[i] + " ");
        }
       
        return ret.toString().trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.toString().equalsIgnoreCase(other.toString()) ) {
        	return true;
        }
        return false;

    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. You lose the first word.
        System.arraycopy(myWords, 1, out.myWords, 0, myWords.length - 1);
        // you add the next word
        out.myWords[myWords.length - 1] = word;
        return out;
    }

}