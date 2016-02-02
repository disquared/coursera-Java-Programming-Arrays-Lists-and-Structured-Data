package module1;

import edu.duke.FileResource;

public class CaesarBreaker {

	public static String decrypt(String encrypted) {
		int[] freqs = countLetters(encrypted);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4;
		if (maxDex < 4) {
			dkey = 26 - (4 - maxDex);
		}
		return CaesarCipher.encrypt(encrypted, 26 - dkey);
	}
	
	public static String decryptTwoKeys(String encrypted) {
		String firstHalf = halfOfString(encrypted, 0);
		String secondHalf = halfOfString(encrypted, 1);
		int key1 = getKey(firstHalf);
		int key2 = getKey(secondHalf);
		System.out.println("Key 1: " + key1);
		System.out.println("Key 2: " + key2);
		return CaesarCipher.encryptTwoKeys(encrypted, 26-key1, 26-key2);
	}
	
	private static String halfOfString(String message, int start) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < message.length(); i += 2) {
			sb.append(message.charAt(i));
		}
		return sb.toString();
	}
	
	private static int getKey(String s) {
		int[] counts = countLetters(s);
		int maxIndex = maxIndex(counts);
		int dkey = maxIndex - 4;
		if (maxIndex < 4) {
			dkey = 26 - (4 - maxIndex);
		}
		return dkey;
	}
	
	private static int[] countLetters(String message) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		for (int i = 0; i < message.length(); i++) {
			char ch = Character.toLowerCase(message.charAt(i));
			int dex = alphabet.indexOf(ch);
			if (dex != -1) {
				counts[dex]++;
			}
		}
		return counts;
	}
	
	private static int maxIndex(int[] vals) {
		int maxDex = 0;
		for (int i = 0; i < vals.length; i++) {
			if (vals[i] > vals[maxDex]) {
				maxDex = i;
			}
		}
		return maxDex;
	}

	public static void main(String[] args) {
		String encrypted1 = CaesarCipher.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 15);
		System.out.println(encrypted1);
		System.out.println(decrypt(encrypted1));
		
		String encrypted2 = CaesarCipher.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 2);
		System.out.println(encrypted2);
		System.out.println(decrypt(encrypted2));
		
		System.out.println(halfOfString("Qbkm Zgis", 0));
		System.out.println(halfOfString("Qbkm Zgis", 1));
		
		System.out.println(getKey("Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth"));
		
		String encrypted3 = CaesarCipher.encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees", 23, 2);
		System.out.println(encrypted3);
		System.out.println(decryptTwoKeys(encrypted3));
		
		System.out.println(CaesarCipher.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6));
		System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
		
		System.out.println(decryptTwoKeys(new FileResource("mysteryTwoKeysPractice.txt").asString()));
	}

}
