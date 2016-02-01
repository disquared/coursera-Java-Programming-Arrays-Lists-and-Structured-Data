package module1;

public class CaesarCipher {

    public static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
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

    public static String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
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

    public static void main(String[] args) {
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        System.out.println(encrypt("First Legion!!", 23));
        System.out.println(encrypt("First Legion!!", 17));
        System.out.println(encryptTwoKeys("First Legion", 23, 17));

        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));

        System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
        System.out.println(encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
    }
}
