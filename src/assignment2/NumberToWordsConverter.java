package assignment2;

public class NumberToWordsConverter {
	private StringBuilder words = new StringBuilder();;
	private static String[] unitArray = {"zero", "one", "two" , "three", "four", "five", "six", "seven", "eight", "nine",
								"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
								"eighteen", "nineteen" };
	private static String[] tensArray = {"zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

	
	public String numToWords(int number) {
		if (number == 0) {
			return "Zero";
		}
		
		if (number < 0) {
			words.append("negative ");
			number = Math.abs(number);
		}
		
		if (number / 1000000000 > 0) {
			numToWords(number / 1000000000);
			words.append(" billion ");
			number %= 1000000000;
		}
		
		if (number / 1000000 > 0) {
			numToWords(number / 1000000);
			words.append(" million ");
			number %= 1000000;
		}
		
		if (number / 1000 > 0) {
			numToWords(number / 1000);
			words.append(" thousand ");
			number %= 1000;
		}
		
		if (number / 100 > 0) {
			numToWords(number / 100);
			words.append(" hundred ");
			number %= 100;
		}
		
		if (number > 0 && number < 20) {
			words.append(unitArray[number]);
		} else if (number >= 20) {
			words.append(tensArray[number / 10]);
			number %= 10;
			words.append("-").append(unitArray[number]);
		}
		words.replace(0, 1, words.toString().substring(0, 1).toUpperCase());
		return words.toString();
	}
	
	public static void main(String[] args) {
		NumberToWordsConverter c = new NumberToWordsConverter();
		System.out.println(c.numToWords(102131));
		// It doesn't properly print out numbers like One hundred thousand (100000)
		// or one hundred million. Not sure how to isolate edge cases like these, yet.
		// It's over complicated for what I need to do.
	}
}
