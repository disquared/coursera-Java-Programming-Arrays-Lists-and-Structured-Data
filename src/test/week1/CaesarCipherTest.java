package week1;

import week1.CaesarCipher;

public class CaesarCipherTest {

	public static void main(String[] args) {
		CaesarCipher cc1 = new CaesarCipher(23); 
		System.out.println(cc1.encrypt("FIRST LEGION ATTACK EAST FLANK!"));
        System.out.println(cc1.encrypt("First Legion!!"));
        
        CaesarCipher cc2 = new CaesarCipher(17);
        System.out.println(cc2.encrypt("First Legion!!"));
        //System.out.println(encryptTwoKeys("First Legion", 23, 17));

        CaesarCipher cc3 = new CaesarCipher(15);
        System.out.println(cc3.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!"));
        //System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));

        System.out.println(cc3.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
        //System.out.println(encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
        
        simpleTests();
	}
	
	public static void simpleTests() {
		String input = "FIRST LEGION ATTACK EAST FLANK!";
		CaesarCipher cc = new CaesarCipher(18);
		String encrypted = cc.encrypt(input);
		System.out.println("Encrypted string: " + encrypted);
		String decrypted = cc.decrypt(encrypted);
		System.out.println("Decrypted string: " + decrypted);
		
		String encrypted2 = cc.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees");
		String decrypted2 = breakCaesarCipher(encrypted2);
		System.out.println("Break Caesar Cipher Decrypted string: " + decrypted2);
	}
	
	public static String breakCaesarCipher(String input) {
		int[] freqs = countLetters(input);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4;
		if (maxDex < 4) {
			dkey = 26 - (4 - maxDex);
		}
		CaesarCipher cc = new CaesarCipher(26 - dkey);
		return cc.encrypt(input);
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
