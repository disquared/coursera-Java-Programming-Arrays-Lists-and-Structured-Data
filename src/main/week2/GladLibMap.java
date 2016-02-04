package module2;

import edu.duke.*;
import java.util.*;
import java.util.Map.Entry;

public class GladLibMap {
	
	private Map<String, List<String>> labelsToWordsMap;
	
	private List<String> seenWordList;
	private List<String> seenLabelList;
	
	private Random myRandom;
	
	@SuppressWarnings("unused")
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		initializeFromSource(dataSourceDirectory);
	}
	
	public GladLibMap(String source){
		initializeFromSource(source);
	}
	
	private void initializeFromSource(String source) {
		labelsToWordsMap = new HashMap<>();
		String[] labels = {"country", "noun", "animal",
						   "adjective", "name", "color",
						   "timeframe", "verb", "fruit"};
		for (String label :labels) {
			List<String> wordList = readIt(source + "/" + label + ".txt");
			labelsToWordsMap.put(label, wordList);
		}
		seenWordList = new ArrayList<>();
		seenLabelList = new ArrayList<>();
		myRandom = new Random();
	}
	
	private String randomFrom(List<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return randomFrom(labelsToWordsMap.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String label  = w.substring(first+1,last);
		String sub;
		do {
			sub = getSubstitute(label);
		} while (seenWordList.contains(sub));
		seenWordList.add(sub);
		if (labelsToWordsMap.containsKey(label) && !seenLabelList.contains(label)) {
			seenLabelList.add(label);
		}
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public int totalWordsInMap() {
		int total = 0;
		for (Entry<String, List<String>> entry : labelsToWordsMap.entrySet()) {
			total += entry.getValue().size();
		}
		return total;
	}
	
	public int totalWordsConsidered() {
		int total = 0;
		for (String label : seenLabelList) {
			total += labelsToWordsMap.get(label).size();
		}
		return total;
	}
	
	public void makeStory(){
	    System.out.println("\n");
	    seenWordList.clear();
		String story = fromTemplate("datalong/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n\nTotal words replaced: " + seenWordList.size());
		System.out.println("Total words in map: " + totalWordsInMap());
		System.out.println("Total words considered: " + totalWordsConsidered());
	}
	
	public static void main(String[] args) {
		GladLibMap glad = new GladLibMap();
		glad.makeStory();
	}
}
