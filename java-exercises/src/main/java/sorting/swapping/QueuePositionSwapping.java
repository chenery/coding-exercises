package sorting.swapping;

/**
 *  https://www.hackerrank.com/challenges/new-year-chaos/problem
 */
public class QueuePositionSwapping {

    // Complete the minimumSwaps function below.

    /**
     * Insertion sort based implementation:
     * Worse case O(n^2) time
     * In place sort, so O(1) space
     *
     * todo implement just by counting, no need to actually do the swaps/sort.
     */
    static void minimumSwaps(int[] q) {
        int i = 0;
        int swaps = 0;
        while (i < q.length) {
            int j = i;

            if (q[i] - (i + 1) > 2) {
//                System.out.println(String.format("currentValue: %d, currentIndex: %d", q[i], i));
                System.out.println("Too chaotic");
                return;
            }

            while (j > 0 && q[j] < q[j -1]) {
                // swap
                int tmp = q[j];
                q[j] = q[j - 1];
                q[j - 1] = tmp;
                j--;
                swaps++;
            }
            i++;
        }
        System.out.println(swaps);
    }

    public static void main(String[] args) {

        // 3
        int[] test = {2, 1, 5, 3, 4};
        minimumSwaps(test);

        // too chaotic
        int[] test2 = {2, 5, 1, 3, 4};
        minimumSwaps(test2);

        // 7
        int[] test3 = {1, 2, 5, 3, 7, 8, 6, 4};
        minimumSwaps(test3);

        // Too chaotic
        int[] test4 = {5, 1, 2, 3, 7, 8, 6, 4};
        minimumSwaps(test4);
    }
}
