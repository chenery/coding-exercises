package arrays;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * FB Q1:
 *
 * Given an array of integers, determine how many pairs of
 * matching integers there are.
 *
 * E.g. array of length 7, sampleArray = [1,2,1,2,1,3,2]
 * There is one pair of 1's and one pair of 2's.
 * There are three unpaired number left, a 1, a 2 and a 3.
 * The number of pairs is 2.
 *
 * Complete the numberPairs function, it must return the number of
 * matching pairs in the array.
 */
public class NumberPairs {

    /**
     * Space: O(n) where n is the range of possible integer values. Not the actual values received.
     * Execution: O(n + m) where m is the length of ar
     *
     * @param n the length of the array where 1 <= n <= 100
     * @param ar the array of numbers, where each number is 1 <= n <= 100
     * @return the number of matching pairs in the array.
     */
    public static int numberPairs(int n, int[] ar) {
        if (ar == null || ar.length == 0) {
            return 0;
        }

        int[] counts = new int[100];
        for (int i : ar) {
            int count = counts[i - 1];
            counts[i - 1] = count + 1;
        }
        int totalPairs = 0;
        for (int count: counts) {
            totalPairs += count / 2;
        }
        return totalPairs;
    }

    /**
     * Space: O(n) where n is the number of received integer values. Also need to consider the default allocations of
     * the hashtable buckets.
     *
     * Executions: O(n)
     *
     * @param n
     * @param ar
     * @return
     */
    public static int numberPairsV2(int n, int[] ar) {
        if (ar == null || ar.length == 0) {
            return 0;
        }

        int totalPairs = 0;
        Set<Integer> counts = new HashSet<>();
        for (int i : ar) {
            if (counts.contains(i)) {
                // then we now have pair
                totalPairs++;
                // reset count for integer value
                counts.remove(i);
            } else {
                counts.add(i);
            }
        }

        return totalPairs;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int n = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        int[] ar = new int[n];
//
//        String[] arItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < n; i++) {
//            int arItem = Integer.parseInt(arItems[i]);
//            ar[i] = arItem;
//        }
        int[] ar = {10, 20, 20, 10, 10, 30, 50, 10, 20};
        int result = NumberPairs.numberPairsV2(9, ar);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
//
//        scanner.close();
    }
}
