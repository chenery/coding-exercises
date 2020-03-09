package careercup;

/**
 * https://www.careercup.com/question?id=5732335291465728
 *
 *  Given an integer 'n', create an array such that each value is repeated twice. For example
 *
 * n = 3 --> [1,1,2,2,3,3]
 * n = 4 --> [1,1,2,2,3,3,4,4]
 *
 * After creating it, find a permutation such that each number is spaced in such a way, they are at a "their value"
 * distance from the second occurrence of the same number.
 *
 * For example: n = 3 --> This is the array - [1,1,2,2,3,3]
 *
 * Your output should be [3,1,2,1,3,2]
 *
 * The second 3 is 3 digits away from the first 3.
 * The second 2 is 2 digits away from the first 2.
 * The second 1 is 1 digit away from the first 1.
 *
 * Return any 1 permutation if it exists. Empty array if no permutation exists.
 *
 * Follow up: Return all possible permutations.
 *
 * Thoughts:
 *  - bt
 *      output array is 2n in size
 *      choose a position for the first 1, 2n choices, then there are up to 2 choices for the second 1
 *          choose a position for the first 2, there there are you to 2 choices for the second 2
 *
 *   Space: 2N output -> O(N)
 *   Time: 2N + 2   +     2N - 2 + 2    +
 *
 *   todo retry this -> it's a mess, backtracking state seems to be deleting incorrect stuff
 *
 */
public class ArraySpacing {
    static boolean bt(int n, int nextValue, boolean isFirst, int nextFirstIndex, int nextSecondIndex, int lastUsedIndex, int[] output) {
        if (nextValue > n) {
            // success case
            return true;
        }

        if (isFirst) {
            // System.out.println("Checking first value for " + nextValue + " at: " + nextFirstIndex);
            // can the next value be positioned at the next index?
            if (output[nextFirstIndex] != 0) {
                System.out.println("First already taken, failing candidate");
                output[lastUsedIndex] = 0;
                return false;
            }
            System.out.println("Selecting first value for " + nextValue + " at: " + nextFirstIndex);
            output[nextFirstIndex] = nextValue;

            // try the second value
            return bt(n, nextValue, false, nextFirstIndex, nextFirstIndex - (n + 1), nextFirstIndex, output)
                    || bt(n, nextValue, false, nextFirstIndex, nextFirstIndex + n + 1, nextFirstIndex, output);

        } else {
            // can the second next value be positioned?
            if (nextSecondIndex < 0 || nextSecondIndex >= output.length || output[nextSecondIndex] != 0) {
                System.out.println("Second already taken, failing candidate");
                output[lastUsedIndex] = 0;
                return false;
            }
            output[nextSecondIndex] = nextValue;
            // try the next value for all indexes
            ++nextValue;
            for (int i = 0; i < output.length; i++) {
                boolean result = bt(n, nextValue, true, i, 0, nextSecondIndex, output);
                if (result) {
                    return result;
                }
            }
            System.out.println("Completed all candidates");
            return false;
        }
    }


    public static void main(String[] args) {

        // n = 3 -> [1,1,2,2,3,3]
        int n = 3;
        int[] output = new int[2 * n];
        boolean result = false;
        for (int i = 0; i < output.length; i++) {
            output = new int[2 * n];
            result = bt(n, 1, true, i, 0, 0, output);
            if (result) {
                break;
            }
        }
        System.out.println("Result: " + result);
        if (result) {
            for (int i : output) {
                System.out.println(i + ",");
            }
        }
    }
}
