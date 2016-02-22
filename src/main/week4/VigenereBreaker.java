package week4;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int k = 0; k < klength; k++) {
            StringBuilder sb = new StringBuilder();
            for (int i = k; i < encrypted.length(); i += klength) {
                sb.append(encrypted.charAt(i));
            }
            key[k] = cc.getKey(sb.toString());
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource resource = new FileResource("data/vigenere/secretmessage1.txt");
        String input = resource.asString();
        int[] key = tryKeyLength(input, 4, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(input);
        System.out.println(Arrays.toString(key));
        System.out.println(decrypted);
    }
    
    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
    	//System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    	vb.breakVigenere();
    }
    
}
