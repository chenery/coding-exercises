package dp.abbr;

/**
 *
 */
public class AbbreviationCopy {

    static String abbrevationTab(String a, String b) {
        boolean[][] dp = new boolean[b.length() + 1][a.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j < dp[0].length; j++) {
            if (Character.isLowerCase(a.charAt(j - 1))) {
//                dp[0][j] = dp[0][j - 1];
                dp[0][j] = true;
            }
        }
        print(dp, b, a);
//        for (int i = 1; i < dp.length; i++) {
//            for (int j = 1; j < dp[0].length; j++) {
//                char ca = a.charAt(j - 1), cb = b.charAt(i - 1);
//                if (ca >= 'A' && ca <= 'Z') {
//                    if (ca == cb) {
//                        dp[i][j] = dp[i - 1][j - 1];
//                    }
//                }else {
//                    ca = Character.toUpperCase(ca);
//                    if (ca == cb) dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1];
//                    else dp[i][j] = dp[i][j - 1];
//                }
//            }
//        }
//        print(dp, b, a);
        return dp[b.length()][a.length()] ? "YES" : "NO";
    }

    private static void print(boolean[][] dp, String a, String b) {

        System.out.print("    ");
        for (int i = 0; i < b.length(); i++) {
            System.out.printf("%2s", b.charAt(i));
        }
        System.out.println();
        System.out.print("  ");
        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[0].length; col++) {
                if (col == 0 && row > 0) {
                    System.out.printf("%2s", a.charAt(row - 1));
                }
                if (dp[row][col]) {
                    System.out.printf("%2d", 1);
                } else {
                    System.out.printf("%2d", 0);
                }
            }
            System.out.println();
        }
        System.out.println();
    }



    public static void main(String[] args) {
        // YES
        System.out.println(abbrevationTab("bBccC", "BBC"));
        // NO
        System.out.println(abbrevationTab("AfPZN", "APZNC"));
    }
}
