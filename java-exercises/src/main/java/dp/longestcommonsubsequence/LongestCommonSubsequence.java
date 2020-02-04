package dp.longestcommonsubsequence;

/**
 *  Constraints: s1 length = s2 length = n
 */
public class LongestCommonSubsequence {

    /**
     * Recursive implementation of LCS
     * Common Child is synonymous with common subsequence.
     *
     * Performance time: O(2^n)
     *
     * @param s1
     * @param s2
     * @return The length of the longest string that is common child to s1 and s2
     */
    static int commonChildRec(String s1, String s2) {
        /*
         * Compare the last char of the each of the strings,
         *  if there is a match, add 1 to total, and recurse to the remainder of the string
         *  else no, match, don't add 1, consider if we remove either of the chars,
         *  and take the max result from the subproblems.
         */
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len == 0 || s2Len == 0) {
            return 0;
        }
        if (s1.charAt(s1Len - 1) == s2.charAt(s2Len - 1)) {
            return 1 + commonChildRec(s1.substring(0, s1Len - 1), s2.substring(0, s2Len - 1));
        } else {
            return Math.max(
                    commonChildRec(s1.substring(0, s1Len), s2.substring(0, s2Len - 1)),
                    commonChildRec(s1.substring(0, s1Len - 1), s2.substring(0, s2Len))
            );
        }
    }

    /**
     * iterative solution with tabulated sub-problem results
     * start bottom up...
     *
     * Time complexity: O(n^2) where n is the length of s1 and s2, same for space.
     */
    static int commonChildTab(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        int[][] tab = new int[s1Len + 1][s2Len + 1];

        for (int i = 0; i <= s1Len; i++) {
            for (int j = 0; j <= s2Len; j++) {
                if (i == 0 || j == 0) {
                    tab[i][j] = 0;
                } else {
                    char c1 = s1.charAt(i - 1);
                    char c2 = s2.charAt(j - 1);
                    if (c1 == c2) {
                        tab[i][j] = 1 + tab[i - 1][j - 1];
                    } else {
                        tab[i][j] = Math.max(tab[i - 1][j], tab[i][j - 1]);
                    }
                }
            }
        }
        return tab[s1Len][s2Len];
    }

    public static void main(String[] args) {

        // should be 3
        System.out.println(commonChildTab("SHINCHAN", "NOHARAAA"));
        // should be 2
        System.out.println(commonChildTab("HARRY", "SALLY"));
    }
}
