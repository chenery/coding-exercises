package hashtable.commonsubstring;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class CommonSubString {

    /**
     * Constraints:
     * - strings can be from 1 to 10,000 chars
     *
     * Second idea:
     * Map/flag for every letter that is found in one string
     * Iterative each char is the other string to check exists in map
     * Performance:
     * Worse case is when there is substring:
     * O(n + m)
     */
    public static String twoStringsV2(String s1, String s2) {

        Set<Character> s1Chars = new HashSet<>();
        for (int i = 0; i < s1.length(); i++) {
            s1Chars.add(s1.charAt(i));
        }

        for (int i = 0; i < s2.length(); i++) {
            if (s1Chars.contains(s2.charAt(i))) {
                return "YES";
            }
        }

        return "NO";
    }

    /**
     * Constraints:
     * - strings can be from 1 to 10,000 chars
     *
     * First idea:
     * Start with smallest string
     * Check for the simplest case, a single char length common substring
     * Use string contains to check the presence of the string
     *
     * Performance:
     * Worse case is when there is no substring:
     * O(nm) where n is length of s1 and m is length of s2
     */
    public static String twoStrings(String s1, String s2) {

        String shorterStr = s1.length() < s2.length() ? s1 : s2;
        String longerStr = s1.length() < s2.length() ? s2 : s1;
        for (int i = 0; i < shorterStr.length(); i++) {
            String subStr = shorterStr.substring(i, i + 1);
            if (longerStr.contains(subStr)) {
                return "YES";
            }
        }
        return "NO";
    }

    public static void main(String[] args) {
        System.out.println(twoStringsV2("hello", "world"));
        System.out.println(twoStringsV2("hi", "world"));
    }
}
