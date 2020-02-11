package arrays.differencepairs;

import java.util.HashSet;
import java.util.Set;

/**
 *  https://www.hackerrank.com/challenges/pairs/problem
 */
public class DifferencePairs {

    // Complete the pairs function below.

    /**
     * O(n^2)
     * @param k
     * @param arr
     * @return
     */
    static int pairs(int k, int[] arr) {
        int numPairs = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(arr[i] - arr[j]) == k) {
                    // System.out.println(String.format("pair %d, %d equals %d", arr[i], arr[j], k));
                    numPairs++;
                    // break;
                }
            }
        }
        return numPairs;
    }

    /**
     * O(n)
     * @param k
     * @param arr
     * @return
     */
    static int pairsV2(int k, int[] arr) {
        int numPairs = 0;

        Set<Integer> values = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            values.add(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            int currentValue = arr[i];
            int valueRequired = currentValue - k;
            if (valueRequired != currentValue && values.contains(valueRequired)) {
                numPairs++;
            }
        }
        return numPairs;
    }

    public static void main(String[] args) {

        // 3
        System.out.println(pairsV2(2, new int[]{1, 5, 3, 4, 2}));
    }
}


