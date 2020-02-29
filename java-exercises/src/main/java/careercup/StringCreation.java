package careercup;

/**
 *  Given a length n, count the number of strings of length n that can be made using ‘a’, ‘b’ and ‘c’
 *  with at-most one ‘b’ and two ‘c’s allowed.
 *
 *  todo:
 *
 *  A straightforward DP problem
 *
 *
 * N = 10
 * dp = [[[1] * (N+1) for _ in [0,1]] for _ in [0,1,2]]
 * for l in range(1,N+1):
 *     dp[2][1][l] = dp[2][1][l-1] + dp[2][0][l-1] + dp[1][1][l-1]
 *     dp[1][1][l] = dp[1][1][l-1] + dp[1][0][l-1] + dp[0][1][l-1]
 *     dp[0][1][l] = dp[0][1][l-1] + dp[0][0][l-1]
 *     dp[2][0][l] = dp[2][0][l-1] + dp[1][0][l-1]
 *     dp[1][0][l] = dp[1][0][l-1] + dp[0][0][l-1]
 *
 * print (dp[2][1][N])
 *
 * n=1 => the output is 3
 * n=2 => the output is 8
 * n=3 => the output is 19
 * n=4 => the output is 39
 *
 * And todo memoization
 */
public class StringCreation {

    private int methodCalls;

    int[] countViaRec(int n) {
        return new int[]{countRec(n, 2, 1), this.methodCalls};
    }

    /**
     *
     * @param n the remaining n (total) chars
     * @param b the remaining b's
     * @param c the remaining c's
     * @return
     */
    int countRec(int n, int b, int c) {
        System.out.println(String.format("n: %d, b: %d, c: %d", n, b, c));
        this.methodCalls++;
        if (n == 0) {
            // The leaves of the recursion tree, each leaf gives a single permutation
            System.out.println("Counted 1");
            return 1;
        }
        // select an 'a' - always possible
        int count = countRec(n - 1, b, c);
        if (b > 0) {
            // select a 'b'
            count += countRec(n - 1, b - 1, c);
        }
        if (c > 0) {
            // select a 'c'
            count += countRec(n - 1, b, c - 1);
        }
        return count;
    }

    int[] countViaTab(int n) {
        int[][][] tab = new int[n + 1][2 + 1][1 + 1];
        return new int[]{countTab(tab, n, 2, 1), this.methodCalls};
    }

    int countTab(int[][][] tab, int n, int b, int c) {
        System.out.println(String.format("n: %d, b: %d, c: %d", n, b, c));
        this.methodCalls++;

        for (int j = 0; j <= b; j++) {
            for (int k = 0; k <= c; k++) {
                tab[0][j][k] = 1;
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < b; j++) {
                for (int k = 0; k < c; k++) {
                    count += tab[i - 1][j][k];
                    if (j > 0) {
                        count += tab[i - 1][j - 1][k];
                    }
                    if (k > 0) {
                        count += tab[i - 1][j][k - 1];
                    }
                    tab[i][j][k] = count;
                    System.out.println(String.format("n: %d, b: %d, c: %d, count: %d", i, j, k, count));
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // 19 in 31 calls
//        int[] result = new StringCreation().countViaRec(3);
//        System.out.println(result[0] + " in " + result[1] + " calls");

        // 19 in 31 calls
        int[] result = new StringCreation().countViaTab(3);
        System.out.println(result[0] + " in " + result[1] + " calls");
    }
}
