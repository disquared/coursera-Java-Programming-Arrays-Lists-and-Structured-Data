package module1;

public class WordPlay {

    private static boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(String.valueOf(ch));
    }

    public static String replaceVowels(String phrase, char ch) {
        if (phrase == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(phrase.charAt(i))) {
                sb.append(ch);
            } else {
                sb.append(phrase.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String emphasize(String phrase, char ch) {
        if (phrase == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ch || phrase.charAt(i) == Character.toUpperCase(ch)) {
                sb.append((i % 2 == 0) ? '*' : '+');
            } else {
                sb.append(phrase.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(isVowel('E'));
        System.out.println(replaceVowels("Hello world!", '*'));
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
