package week4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    
    public Set<String> readDictionary(FileResource fr) {
        return StreamSupport.stream(fr.lines().spliterator(), false)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }
    
    public int countWords(String message, Set<String> dict) {
        int validWords = 0;
        String[] words = message.split("\\W+");
        for (String word : words) {
            if (dict.contains(word.toLowerCase())) {
                validWords++;
            }
        }
        return validWords;
    }
    
    public char mostCommonCharIn(Set<String> dict) {
        int[] charCounts = new int[26];
        for (String word : dict) {
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                if (letter >= 'a' && letter <= 'z') {
                    charCounts[letter - 'a']++;
                }
            }
        }
        char mostCommonChar = '0';
        int mostCommonCount = 0;
        for (int i = 0; i < 26; i++) {
            if (charCounts[i] > mostCommonCount) {
                mostCommonCount = charCounts[i];
                mostCommonChar = (char) (i + 'a');
            }
        }
        return mostCommonChar;
    }
    
    public String breakForLanguage(String encrypted, Set<String> dict) {
        int mostValidWords = 0;
        int[] keyMostValidWords = {};
        for (int i = 1; i <= 100; i++) {
            char mostCommonChar = mostCommonCharIn(dict);
            int[] key = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int validWords = countWords(decrypted, dict);
            if (validWords > mostValidWords) {
                mostValidWords = validWords;
                keyMostValidWords = key;
            }
        }
        //System.out.println("Num valid words: " + mostValidWords);
        //System.out.println("Key length: " + keyMostValidWords.length);
        return new VigenereCipher(keyMostValidWords).decrypt(encrypted);
    }
    
    public String breakForAllLanguages(String encrypted, Map<String, Set<String>> languages) {
        int maxNumWords = 0;
        String decryptedMaxWords = "";
        String decryptedLanguage = "";
        for (String language : languages.keySet()) {
            Set<String> dict = languages.get(language);
            String decrypted = breakForLanguage(encrypted, dict);
            int numWords = countWords(decrypted, dict);
            if (numWords > maxNumWords) {
                maxNumWords = numWords;
                decryptedMaxWords = decrypted;
                decryptedLanguage = language;
            }
        }
        System.out.println("maxNumWords: " + maxNumWords);
        System.out.println("language: " + decryptedLanguage);
        return decryptedMaxWords;
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource("data/vigenere/secretmessage3.txt");
        String encrypted = fr.asString();
        //System.out.println(Arrays.toString(dict.toArray()));
        Map<String, Set<String>> languageToDictMap = new HashMap<>();
        String[] languages = {"Danish", "Dutch", "English", "French",
                              "German", "Italian", "Portuguese", "Spanish"};
        for (String language : languages) {
            FileResource frDict = new FileResource("data/vigenere/dictionaries/" + language);
            Set<String> dict = readDictionary(frDict);
            languageToDictMap.put(language, dict);
        }
        String decrypted = breakForAllLanguages(encrypted, languageToDictMap);
        System.out.println(decrypted);
    }

    public void oldBreakVigenere () {
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
    	vb.breakVigenere();
    }
    
}
