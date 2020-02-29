package dp.fibonacci;

import java.util.Date;

/**
 *  4 methods, demonstrating recursive, dynamic programming and smart thinking
 */
public class Fibonacci {

    /**
     * Time: O(2^n)
     * Space: O(n) including the stack
     * @param n
     * @return the nth number in the sequence
     */
    static long fibonacciRec(int n) {
        if (n < 2) {
            return 1;
        }

        return fibonacciRec(n - 1) + fibonacciRec(n - 2);
    }

    // O(n) time & space
    static long fibonacciTab(int n) {

        if (n < 2) {
            return 1L;
        }

        long[] fValues = new long[n];
        fValues[0] = 1;
        fValues[1] = 1;

        // bottom up tabulation
        for (int i = 2; i < n; i++) {
            fValues[i] = fValues[i - 1] + fValues[i - 2];
        }

        return fValues[n - 1];
    }

    /**
     * Time:
     * Space:
     * @param n
     * @return the nth number in the sequence
     */
    static long fibonacciMem(int n, long[] mem) {
        if (n <= 2) {
            return 1;
        }

        if (mem[n] == 0) {
            mem[n] = fibonacciMem(n - 1, mem) + fibonacciMem(n - 2, mem);
        }

        return mem[n];
    }

    /**
     * Time O(n)
     * Space O(1)
     * @param n
     * @return
     */
    static long fibonacciBest(int n) {
        if (n <= 2) {
            return 1;
        }

        long nMinusTwo = 1, nMinusOne = 1, nth = -1;
        for (int i = 3; i <= n; i++) {
            nth = nMinusOne + nMinusTwo;
            nMinusTwo = nMinusOne;
            nMinusOne = nth;
        }
        return nth;
    }

    public static void main(String[] args) {
        Date start = new Date();
        for (int i = 0; i < 40; i++) {
            System.out.println(fibonacciRec(i));
        }
        Date end = new Date();
        System.out.println("Time: " + (end.getTime() - start.getTime()));

        Date start2 = new Date();
        for (int i = 1; i <= 40; i++) {
            System.out.println(fibonacciTab(i));
        }
        Date end2 = new Date();
        System.out.println("Time: " + (end2.getTime() - start2.getTime()));

        Date start3 = new Date();
        for (int i = 1; i <= 40; i++) {
            System.out.println(fibonacciMem(i, new long[i + 1]));
        }
        Date end3 = new Date();
        System.out.println("Time: " + (end3.getTime() - start3.getTime()));

        Date start4 = new Date();
        for (int i = 1; i <= 40; i++) {
            System.out.println(fibonacciBest(i));
        }
        Date end4 = new Date();
        System.out.println("Time: " + (end4.getTime() - start4.getTime()));
    }
}
