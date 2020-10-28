package problem.facebook.old;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date: 28/10/20
 * Time: 2:13 pm
 */
public class NearbyWords {
    public static void main(String[] args) {
        System.out.println(nearbyWords("gi"));
    }

    static Set<String> nearbyWords(String input) {
        char[] letters = input.toCharArray();
        Set<String> nearbyPermutations = nearbyPermutations(letters, 0);
        Set<String> words = new HashSet<>();
        for (String pw : nearbyPermutations) {
            if (isWord(pw)) {
                words.add(pw);
            }
        }
        return words;
    }

    private static Set<String> nearbyPermutations(char[] letters, int index) {
        if (index >= letters.length) {
            HashSet<String> strings = new HashSet<>();
            strings.add("");
            return strings;
        }

        Set<String> subWords = nearbyPermutations(letters, index + 1);
        Set<Character> nearbyLetters = getNearbyChars(letters[index]);

        return permutations(subWords, nearbyLetters);
    }

    private static Set permutations(Set<String> subWords, Set<Character> nearbyLetters) {
        Set permutations = new HashSet<>();

        for (String subWord : subWords) {
            for (Character letter : nearbyLetters) {
                permutations.add(letter + subWord);
            }
        }
        return permutations;
    }


    //Lame implementation of helpers.
    private static Set<Character> getNearbyChars(Character character) {
        HashSet<Character> characters = new HashSet<>();
        if (character == 'g') {
            characters.add('g');
            characters.add('h');
            characters.add('f');
        } else {
            characters.add('i');
            characters.add('o');
            characters.add('k');
        }
        return characters;
    }

    static boolean isWord(String word) {
        return word.equals("go") || word.equals("hi");
    }
}
