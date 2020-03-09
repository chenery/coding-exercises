package careercup;

/**
 *  Find whether string S is periodic.
 *  Periodic indicates S = nP.
 *  e.g.
 *  S = "ababab", then n = 3, and P = "ab"
 *  S = "xxxxxx", then n = 1, and P = "x"
 *  S = "aabbaaabba", then n = 2, and P = "aabba"
 *  follow up:
 *  Given string S, find out the P (repetitive pattern) of S.
 *
 *  Thoughts:
 *
 *      - recursive
 *      - assume solution with the least n
 *      - when you consider chars rather than substrings there are overlapping subproblems
 *      - DP -> mem
 */
public class PeriodicString {

    static boolean isPeriodicV2(String s) {
        int sLen = s.length();

        // test increasing lengths of substrings todo only go to sLen / 2
        for (int i = 1; i < sLen; i++) {
            String p = s.substring(0, i);
            boolean result = isPeriodical(s, i, p, i);
            if (result) {
                return true;
            }
        }

        return false;
    }

    static boolean isPeriodical(String s, int startIndex, String p, int len) {
        if (startIndex + len > s.length()) {
            System.out.println("Reached end of string");
            return false;
        }
        String testPeriod = s.substring(startIndex, startIndex + len);
        System.out.println(String.format("Testing %s against p: %s at startIndex: %d", testPeriod, p, startIndex));
        if (!testPeriod.equals(p)) {
            System.out.println("Not periodic");
            return false;
        }
        if (startIndex + len == s.length()) {
            System.out.println("Is periodic");
            return true;
        }
        // todo remove the recursion it can be expressed easily as a loop
        return isPeriodical(s, startIndex + len, p, len);
    }

    public static void main(String[] args) {
//        System.out.println(isPeriodicV2("ababab"));
        System.out.println(isPeriodicV2("abcabcabcabcabc"));
    }


    static boolean isPeriodic(String s) {
        int len = s.length();

        // test increasing lengths of substrings
        for (int i = 1; i < len; i++) {

            // todo test len % i == 0 to short circuit to false
            // for each substring in s of length i
            for (int j = i; j < len - 1; j += i) {
                // test every chars in the substring equals that from the initial period
                for (int k = j; k < j + i; k++) {
                    if (s.charAt(k - i) != s.charAt(k)) {
                        break;
                    }
                }
            }

            // here can be true
        }
        return false;
    }

}
