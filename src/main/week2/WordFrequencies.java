package module2;

import java.util.ArrayList;
import java.util.List;

import edu.duke.FileResource;

public class WordFrequencies {
	
	private List<String> myWords;
	private List<Integer> myFreqs;
	
	public WordFrequencies() {
		myWords = new ArrayList<>();
		myFreqs = new ArrayList<>();
	}
	
	public void findUnique() {
		myWords.clear();
		myFreqs.clear();
		FileResource resource = new FileResource();
		for (String word : resource.words()) {
			word = word.toLowerCase();
			int index = myWords.indexOf(word);
			if (index == -1) {
				myWords.add(word);
				myFreqs.add(1);
			} else {
				myFreqs.set(index, myFreqs.get(index) + 1);
			}
		}
	}
	
	public int findIndexOfMax() {
		int maxSoFar = 0;
		int indexOfMaxSoFar = -1;
		for (int i = 0; i < myFreqs.size(); i++) {
			if (myFreqs.get(i) > maxSoFar) {
				indexOfMaxSoFar = i;
				maxSoFar = myFreqs.get(i);
			}
		}
		return indexOfMaxSoFar;
	}
	
	private void tester() {
		findUnique();
		System.out.println("Number of unique words: " + myWords.size());
		for (int i = 0; i < myWords.size(); i++) {
			System.out.println(myFreqs.get(i) + " " + myWords.get(i));
		}
		int indexOfMax = findIndexOfMax();
		System.out.println("The word that occurs most often and its count are: " + myWords.get(indexOfMax) + " " + myFreqs.get(indexOfMax));
	}

	public static void main(String[] args) {
		WordFrequencies wf = new WordFrequencies();
		wf.tester();
	}

}
