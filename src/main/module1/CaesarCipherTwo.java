package module1;

public class CaesarCipherTwo {
	
	private final int key1;
	private final int key2;
	private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final String shiftedAlphabet1;
	private final String shiftedAlphabet2;
	
	public CaesarCipherTwo(int key1, int key2) {
		this.key1 = key1;
		this.key2 = key2;
		shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
		shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
	}

    public String encryptTwoKeys(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char currCharUpper = Character.toUpperCase(encrypted.charAt(i));
            int idx = alphabet.indexOf(currCharUpper);
            if (idx != -1) {
                char newChar = (i % 2 == 0 ? shiftedAlphabet1.charAt(idx) : shiftedAlphabet2.charAt(idx));
                encrypted.setCharAt(i, (Character.isUpperCase(currChar) ? newChar : Character.toLowerCase(newChar)));
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
    	CaesarCipherTwo cct = new CaesarCipherTwo(26-key1, 26-key2);
		return cct.encryptTwoKeys(input);
	}
}
