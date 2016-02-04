package week1;

import edu.duke.FileResource;

public class WordLengths {

	public static void countWordLengths(FileResource resource, int[] counts) {
		for (String word : resource.words()) {
			int wordLength = word.length();
			if (wordLength > 1 && !Character.isLetter(word.charAt(wordLength-1))) {
				wordLength--;
			}
			if (!Character.isLetter(word.charAt(0))) {
				wordLength--;
			}
			if (wordLength >= counts.length) {
				wordLength = counts.length - 1;
			}
			counts[wordLength]++;
		}
	}
	
	private static int indexOfMax(int[] values) {
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		for (int i = 0; i < values.length; i++) {
			if (values[i] > max) {
				max = values[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public static void main(String[] args) {
		testCountWordLengths();
	}
	
	private static void testCountWordLengths() {
		FileResource fr = new FileResource();
		int[] counts = new int[31];
		countWordLengths(fr, counts);
		for (int i = 0; i < counts.length; i++) {
			if (counts[i] > 0) {
				System.out.println("Count of words with length " + i + ": " + counts[i]);
			}
		}
		System.out.println("Most common word length: " + indexOfMax(counts));
	}

}
