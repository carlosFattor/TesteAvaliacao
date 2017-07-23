package stream;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by carlos on 23/07/17.
 */
public class VogalStream {

    private static final String VOGAL = "aeiou";

    public VogalStream() {
    }

    public static char first(Stream input) {
        if (input == null) {
            throw new IllegalArgumentException("input can't be null");
        }

        Map<Character, Boolean> checked = new LinkedHashMap<Character, Boolean>();

        char valueToCheck = '\0';

        while (input.hasNext()){
            char current = input.getNext();

        }


        char c = '\0';
        return c;
    }

    private static boolean isVowel(String character) {
        return character.matches(VOGAL);
    }
}
