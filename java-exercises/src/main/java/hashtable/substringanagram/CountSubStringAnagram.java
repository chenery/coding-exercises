package hashtable.substringanagram;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem
 */
public class CountSubStringAnagram {

    /*
     * Complete the 'anagramPairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int anagramPairsBlah(String s) {
        // Write your code here
        int total = 0;
        Set<String> subStrs = new HashSet<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                String subStr = s.substring(i, j + 1);
                char[] ssChars = subStr.toCharArray();
                Arrays.sort(ssChars);
                String sortedSubStr = new String(ssChars);
                if (subStrs.contains(sortedSubStr)) {
                    System.out.println(subStr);
                    total++;
                }
                subStrs.add(sortedSubStr);
            }
        }

        return total;
    }

    public static int anagramPairs(String s) {
        Map<String, Integer> ssCounts = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                String subStr = s.substring(i, j + 1);
                char[] ssChars = subStr.toCharArray();
                Arrays.sort(ssChars);
                String sortedSubStr = new String(ssChars);
                ssCounts.put(sortedSubStr, ssCounts.getOrDefault(sortedSubStr, 0) + 1);
            }
        }

        int total = 0;
        for (int count : ssCounts.values()) {
            if (count > 1) {
                // n*(n-1)/2
                total += count * (count - 1) /2;
            }
        }
        return total;
    }

    static BigInteger binomial(final int N, final int K) {
        BigInteger ret = BigInteger.ONE;
        for (int k = 0; k < K; k++) {
            ret = ret.multiply(BigInteger.valueOf(N-k))
                    .divide(BigInteger.valueOf(k+1));
        }
        return ret;
    }

    public static void main(String[] args) {

        // 5
        System.out.println(anagramPairs("cdcd"));

        // 4
        System.out.println(anagramPairs("abba"));

        // 0
        System.out.println(anagramPairs("abcd"));

        // 3
        System.out.println(anagramPairs("ifailuhkqq"));

        // 10
        System.out.println(anagramPairs("kkkk"));
    }
}
