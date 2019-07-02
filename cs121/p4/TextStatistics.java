import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;


public class TextStatistics implements TextStatisticsInterface {
	private File file;
	private int charCount;
	private int wordCount;
	private int lineCount;
	private int[] letterCount = new int[26];
	private int[] wordLengCount = new int[23];
	private double sumWordLengths;

	public TextStatistics(File file) {
		this.file = file;
		

		try {
			// Read the file line by line (while more lines exist).
			Scanner fileScan = new Scanner(file);

			String line;
			while (fileScan.hasNextLine()) {
				line = fileScan.nextLine();
				charCount += line.length() + 1;
				lineCount++;
				// Read each line, word by word (while there are more words).
				// Scanner lineScan = new Scanner(line);

				StringTokenizer tokenizer = new StringTokenizer(
						line.toLowerCase(),
						" , .;:'\"&!?-_\n\t12345678910[]{}()@#$%^*/+-");
				while (tokenizer.hasMoreTokens()) {
					String tempWord = tokenizer.nextToken();
					wordCount++;
					sumWordLengths += tempWord.length();
					for (int i = 0; i < tempWord.length(); i++) {
						letterCount[tempWord.charAt(i) - 'a']++;
					}
					if (tempWord.length() <= 23){
						wordLengCount[tempWord.length()]++;
					}

				}
			}
			fileScan.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error trying to open file. " + e.getMessage());
			System.exit(1);
		}
		/*
		 * for (int i = 0; i < letterCount.length; i++) {
		 * System.out.println(letterCount[i]); //move to toString }
		 */

	}

	@Override
	public int getCharCount() {
		return charCount;
	}

	@Override
	public int getWordCount() {
		return wordCount;
	}

	@Override
	public int getLineCount() {
		return lineCount;
	}

	@Override
	public int[] getLetterCount() {
		return letterCount;
	}

	@Override
	public int[] getWordLengthCount() {
		return wordLengCount;
	}

	@Override
	public double getAverageWordLength() {
		return (sumWordLengths / wordCount);
	}

	public String toString() {
		String s = "";
		s += "Statistics for " + file + "\n";
		s += "=========================================================="
				+ "\n";
		s += getLineCount() + " lines" + "\n";
		s += getWordCount() + " words" + "\n";
		s += getCharCount() + " characters" + "\n";
		s += "------------------------------" + "\n";
		for (int i = 0; i < letterCount.length / 2; i++) {
			char letter = (char) ('a' + i);
			int j = i + 13;
			char secLetter = (char) ('a' + j);
			String tempString = String.format("%-12s", letter + " = "
					+ letterCount[i]);
			String tempString2 = String.format("%-12s", secLetter + " = "
					+ letterCount[j]);
			s += (tempString + "    " + tempString2 + "\n");
		}
		;
		s += "------------------------------" + "\n";
		s += "length  frequency" + "\n";
		s += "------  ---------" + "\n";
		for (int i = 1; i < wordLengCount.length; i++){
			if (wordLengCount[i] != 0){
				String fString = Integer.toString(wordLengCount[i]);
				s += Integer.toString(i) + "\t" + fString + "\n";
			}

		}
		s += "------------------------------" + "\n";
		DecimalFormat df = new DecimalFormat("0.00");
		s += "Average word Length = " + df.format(getAverageWordLength()) + "\n";
		s += "=========================================================="
				+ "\n";

		return s;
	}

}