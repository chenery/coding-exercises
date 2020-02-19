package greedy.maxmin;

import java.util.Arrays;

/**
 *  https://www.hackerrank.com/challenges/angry-children/problem
 */
public class MaxMin {

    static int maxMin(int k, int[] arr) {
        System.out.println("k: " + k);
        // sort the arr n log n
        // iterate elements at i and j where j = i + k, and minimise the difference of values.
        int min = Integer.MAX_VALUE;
        Arrays.sort(arr);
        for (int i = 0; i <= arr.length - k; i++) {
            int j = i + k - 1;
//            System.out.println(String.format("arr[j]: %d, arr[i]: %d", arr[j], arr[i]));
            int range = arr[j] - arr[i];
            if (range < min) {
                min = range;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        // 20
        System.out.println(maxMin(3, new int[]{10, 100, 300, 200, 1000, 20, 30}));

        // 3
        System.out.println(maxMin(4, new int[]{1, 2, 3, 4, 10, 20, 30, 40, 100, 200}));

        // 2
        System.out.println(maxMin(3, new int[]{100, 200, 300, 350, 400, 401, 402}));
    }
}
