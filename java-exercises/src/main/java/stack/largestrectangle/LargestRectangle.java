package stack.largestrectangle;

/**
 *  https://www.hackerrank.com/challenges/largest-rectangle/problem
 */
public class LargestRectangle {
    static long largestRectangle(int[] h) {
        // todo optimised solution using a stack
        return -1;
    }


    // O(n^3)
    static long largestRectangleBruteForce(int[] h) {
        int length = h.length;
        long maxRec = 1;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                long area = min(h, i, j) * (j - i + 1);
                if (area > maxRec) {
                    maxRec = area;
                }
            }
        }
        return maxRec;
    }

    static int min(int[] h, int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            if (h[k] < min) {
                min = h[k];
            }
        }
        return min;
    }

    public static void main(String[] args) {

        // 9
        System.out.println(largestRectangle(new int[]{1, 2, 3, 4, 5}));

        // 18
        System.out.println(largestRectangle(new int[]{1, 3, 5, 9, 11}));

        // 50
        System.out.println(largestRectangle(new int[]{11, 11, 10, 10, 10}));
    }
}
