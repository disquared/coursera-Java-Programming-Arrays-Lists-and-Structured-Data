package week2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import edu.duke.FileResource;

public class CodonCounter {

	private Map<String, Integer> codonCounts;
	
	public CodonCounter() {
		codonCounts = new HashMap<>();
	}
	
	public void buildCodonMap(String dna, int start) {
		codonCounts.clear();
		for (int i = start; i < dna.length()-2; i += 3) {
			String codon = dna.substring(i, i+3);
			if (codonCounts.containsKey(codon)) {
				codonCounts.put(codon, codonCounts.get(codon) + 1);
			} else {
				codonCounts.put(codon, 1);
			}
		}
	}
	
	public String getMostCommonCodon() {
		String mostCommonCodon = "";
		int largestCount = 0;
		for (Entry<String, Integer> entry : codonCounts.entrySet()) {
			if (entry.getValue() > largestCount) {
				mostCommonCodon = entry.getKey();
				largestCount = entry.getValue();
			}
		}
		return mostCommonCodon;
	}
	
	public void printCodonCounts(int start, int end) {
		System.out.println("Counts of codon between " + start + " and " + end + " inclusive are: ");
		for (Entry<String, Integer> entry : codonCounts.entrySet()) {
			int count = entry.getValue();
			if (count >= start && count <= end) {
				System.out.println(entry.getKey() + " " + count);
			}
		}
	}
	
	public void tester() {
		FileResource fr = new FileResource();
		String dna = fr.asString().trim().toUpperCase();
		for (int start = 0; start <= 2; start++) {
			buildCodonMap(dna, start);
			System.out.println("Reading frame starting with " + start + " results in " + codonCounts.size() + " unique codons");
			String mostCommonCodon = getMostCommonCodon();
			System.out.println("and most common codon is " + mostCommonCodon + " with count " + codonCounts.get(mostCommonCodon));
			printCodonCounts(1, 99);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		CodonCounter cc = new CodonCounter();
		cc.tester();
	}

}
