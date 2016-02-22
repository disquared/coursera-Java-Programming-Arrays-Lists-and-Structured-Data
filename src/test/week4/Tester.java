package week4;

import edu.duke.*;

public class Tester {

    public static void testCaesarCipher() {
        FileResource resource = new FileResource("data/vigenere/titus-small.txt");
        String input = resource.asString();
        CaesarCipher cc = new CaesarCipher(3);
        System.out.println("CaesarCipher: " + cc.encrypt(input) + "\n");
    }
    
    public static void testCaesarCracker1() {
        FileResource resource = new FileResource("data/vigenere/titus-small_key5.txt");
        String input = resource.asString();
        CaesarCracker cc = new CaesarCracker();
        System.out.println("CaesarCracker1: " + cc.decrypt(input) + "\n");
    }
    
    public static void testCaesarCracker2() {
        FileResource resource = new FileResource("data/vigenere/oslusiadas_key17.txt");
        String input = resource.asString();
        CaesarCracker cc = new CaesarCracker('a');
        System.out.println("CaesarCracker2: " + cc.decrypt(input) + "\n");
    }
    
    public static void testVigenereCipher() {
        FileResource resource = new FileResource("data/vigenere/titus-small.txt");
        String input = resource.asString();
        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
        System.out.println("VigenereCipher encrypt: " + vc.encrypt(input) + "\n");
        System.out.println("VigenereCipher decrypt: " + vc.decrypt(vc.encrypt(input)) + "\n");
    }
    
	public static void main(String[] args) {
	    testCaesarCipher();
	    testCaesarCracker1();
	    testCaesarCracker2();
	    testVigenereCipher();
	}

}
