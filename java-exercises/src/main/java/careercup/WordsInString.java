package careercup;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/discuss/interview-question/338192/Facebook-or-Onsite-or-Word-Break
 *
 * Question: Can you break the given string into words, provided by a given hashmap of frequency of word as <word: n>
 *
 *     - Don't have to use all the words in the map
 *     - Could be multiple solutions
 *
 * Example:
 * HashMap -> {"abc":3, "ab":2, "abca":1}
 * String: abcabcabcabca
 * output: Yes; [ abc, abc, abc , abca ]
 *
 * Example:
 * HashMap -> {"abc":3, "ab":2}
 * String: abcabab
 * output: No
 *
 * Example:
 * HashMap -> {"abc":3, "ab":2, "abca":1}
 * String: abcx
 * output: No
 *
 * Thinking:
 *
 *  Options:
 *      - Brute force: test every combination & permutation of words to see if these consume the string, k*nCn + k*nCn-1... k*nC1
 *      - Greedy algorithm: Assume using largest strings first will achieve result quickest -> not guaranteed to work
 *      - DP: memoization: brute force plus remembering if this word x is contained in the string at a certain state (index offset),
 *      - DP: tabulation, require a recursive definition:
 *          words(str, map)
 *            for each word in remaining words:
 *              - True if str.contains(word) && words(str - word, map-word)
 *
 *          -> not sure how to build this bottom up
 *
 *      - Back tracking
 *
 */
public class WordsInString {

    /**
     *  O(NM) in worst case where N is length of input, and M is the number of items in the dictionary.
     */
    static Collection<String> words(String input, Map<String, Integer> wc) {
        Deque<String> output = new ArrayDeque<>();
        boolean result = bt(null, input, wc, output);
        System.out.println("Found: " + result);
        return output;
    }

    static String first(Map<String, Integer> wc) {
        return wc.keySet().iterator().next();
    }

    static void backTrackState(String candidate, Map<String, Integer> wc, Deque<String> output) {
        // Backtrack, by returning candidate state
        wc.put(candidate, wc.get(candidate) + 1);
        output.removeLast();
    }

    static boolean bt(String candidate, String input, Map<String, Integer> wc, Deque<String> output) {

        if (candidate == null) {
            // get first()
            candidate = first(wc);
            System.out.println("Taking first: " + candidate);
        }

        // update state assuming this is a partial solution
        wc.put(candidate, wc.get(candidate) - 1);
        output.addLast(candidate);

        System.out.println("Input: " + input + " candidate: " + candidate);

        if (!input.startsWith(candidate)) {
            System.out.println("Backtracking");
            // This branch will never be a solution
            backTrackState(candidate, wc, output);
            return false;
        }

        if (input.equals(candidate)) {
            System.out.println("Found solution");
            // This branch is a solution
            return true;
        }

        System.out.println("Assuming partial solution");
        String reducedInput = input.substring(candidate.length());
        for (String next : wc.keySet()) {
            if (bt(next, reducedInput, wc, output)) {
                return true;
            }
        }
        backTrackState(candidate, wc, output);
        return false;
    }

    public static void main(String[] args) {

        Map<String, Integer> wordCount = new LinkedHashMap<>();
        wordCount.put("abc", 3);
        wordCount.put("ab", 2);
        wordCount.put("abca", 1);
        for (String word : words("abcabcabcabca", wordCount)) {
            System.out.println(word);
        }
    }
}
