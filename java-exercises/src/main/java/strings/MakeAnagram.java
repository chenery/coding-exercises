package strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  FB: Q6
 */
public class MakeAnagram {

    /**
     * Space: O(1), fixed
     * Performance: O(n + m) where n is the length of a, and m is the length of b
     *
     * a and b have length at least 1
     * a and b range [a-z]
     * @return the number of deletions required to make a and b anagrams
     */
    public static int makeAnagramV3(String a, String b) {

        // map frequency of each char
        int[] aFreq = new int[26], bFreq = new int[26];
        int numDeletions = 0;

        for(int i = 0; i < a.length(); i++) {
            int index = a.charAt(i) - 'a';
            aFreq[index] = aFreq[index] + 1;
        }

        for(int i = 0; i < b.length(); i++) {
            int index = b.charAt(i) - 'a';
            bFreq[index] = bFreq[index] + 1;
        }

        for (int i = 0; i < 26; i++) {
            numDeletions += Math.abs(aFreq[i] - bFreq[i]);
        }

        return numDeletions;
    }

    /**
     * Space: O(n + m) for the char arrays where n is the length of a, and m is the length of b,
     * then some aux for the sorts.
     *
     * Performance: O(a1 + a2 + b1 + b2 + n)
     * a1 is the O(n) cost of the char array creation for string a
     * a2 is the O(n log n) cost of the char array sort for string a
     * b1 is the O(n) cost of the char array creation for string b
     * b2 is the O(n log n) cost of the char array sort for string b
     * n is the max of length of a and b
     */
    public static int makeAnagram(String a, String b) {

        char[] s1Chars = a.toCharArray();
        Arrays.sort(s1Chars);

        char[] s2Chars = b.toCharArray();
        Arrays.sort(s2Chars);

        int s1Length = s1Chars.length;
        int s2Length = s2Chars.length;
        int s1Index = 0, s2Index = 0;

        int numDeletions = 0;

        while(s1Index < s1Length || s2Index < s2Length) {

            if (s1Index >= s1Length) {
                // delete the rest of s2 and end
                numDeletions = numDeletions + (s2Length - s2Index);
                break;
            }

            if (s2Index >= s2Length) {
                // delete the rest of s1 and end
                numDeletions = numDeletions + (s1Length - s1Index);
                break;
            }

            char s1Char = s1Chars[s1Index];
            char s2Char = s2Chars[s2Index];

            if (s1Char != s2Char) {
                // delete the smallest char, due to sort it cannot be in the other string
                if (s1Char < s2Char) {
                    // delete modelled by moving a single pointer forward
                    s1Index++;
                } else {
                    s2Index++;
                }
                numDeletions++;

            } else {
                // move to the next char in each string
                s1Index++;
                s2Index++;
            }
        }

        return numDeletions;
    }

    public static int makeAnagramV2(String a, String b) {

        // map frequency of each char
        Map<Character, Integer> aFreq = mapCharFreq(a);
        Map<Character, Integer> bFreq = mapCharFreq(b);

        Set<Character> combinedChars = new HashSet<>(aFreq.keySet());
        combinedChars.addAll(bFreq.keySet());
        int numDeletions = 0;
        for (char c : combinedChars) {
            int aFreqForC = aFreq.getOrDefault(c, 0);
            int bFreqForC = bFreq.getOrDefault(c, 0);
            numDeletions = numDeletions + Math.abs(aFreqForC - bFreqForC);
        }

        return numDeletions;
    }

    public static Map<Character, Integer> mapCharFreq(String ofString) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < ofString.length(); i++) {
            char c = ofString.charAt(i);
            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) + 1);
            } else {
                freqMap.put(c, 1);
            }
        }
        return freqMap;
    }

    public static void main(String[] args) {
        System.out.println(makeAnagramV3("cde", "abc"));
    }
}
