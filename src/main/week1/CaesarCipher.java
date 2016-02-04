package week1;

public class CaesarCipher {
	
	private final int key;
	private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final String shiftedAlphabet;
	
	public CaesarCipher(int key) {
		this.key = key;
		shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
	}

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char currCharUpper = Character.toUpperCase(encrypted.charAt(i));
            int idx = alphabet.indexOf(currCharUpper);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, (Character.isUpperCase(currChar) ? newChar : Character.toLowerCase(newChar)));
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String encrypted) {
		CaesarCipher cc = new CaesarCipher(26 - key);
		return cc.encrypt(encrypted);
	}
}
