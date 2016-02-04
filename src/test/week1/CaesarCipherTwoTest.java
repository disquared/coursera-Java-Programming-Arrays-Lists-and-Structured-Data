package week1;

public class CaesarCipherTwoTest {

	public static void main(String[] args) {
		simpleTests();
	}
	
	private static void simpleTests() {
		String input = "Just a test string with lots of eeeeeeeeeeeeeeeees";
		CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
		String encrypted = cct.encryptTwoKeys(input);
		System.out.println("Encrypted string: " + encrypted);
		String decrypted = cct.decrypt(encrypted);
		System.out.println("Decrypted string: " + decrypted);
		
		String decrypted2 = breakCaesarCipher(encrypted);
		System.out.println("Break Caesar Cipher Decrypted string: " + decrypted2);
	}
	
	public static String breakCaesarCipher(String input) {
		String firstHalf = halfOfString(input, 0);
		String secondHalf = halfOfString(input, 1);
		int key1 = getKey(firstHalf);
		int key2 = getKey(secondHalf);
		System.out.println("Key 1: " + key1);
		System.out.println("Key 2: " + key2);
		CaesarCipherTwo cct = new CaesarCipherTwo(26-key1, 26-key2);
		return cct.encryptTwoKeys(input);
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

}
