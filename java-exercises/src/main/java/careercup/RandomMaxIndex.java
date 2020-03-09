package careercup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  https://www.careercup.com/question?id=5732335291465728
 *
 *  Generate random max index
 *
 * Given an array of integers, randomly return an index of the maximum value seen by far.
 *
 * e.g.
 * Given [11,30,2,30,30,30,6,2,62, 62]
 *
 * Having iterated up to the at element index 5 (where the last 30 is), randomly give an index among [1, 3, 4, 5] which are indices of 30 - the max value by far. Each index should have a ¼ chance to get picked.
 *
 * Having iterated through the entire array, randomly give an index between 8 and 9 which are indices of the max value 62.
 */
public class RandomMaxIndex {

    /*
  Assume postive integers
  Space: O(n) worst case if all the same, or if all different O(cN)
    Time: O(n)
   */

    static int randomMaxIndex(int[] input, int n) {
        // todo argument sanity checks

        int maxValue = 0;
        List<Integer> maxIndexes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int current = input[i];
            if (current >= maxValue) {
                if (current > maxValue) {
                    maxIndexes = new ArrayList<>();
                    maxValue = current;
                }
                maxIndexes.add(i);
            }
        }

        // random index of the max indexes
        int randomIndex = new Random().nextInt(maxIndexes.size());
        return maxIndexes.get(randomIndex);
    }



    public static void main(String[] args) {

        System.out.println(randomMaxIndex(new int[]{11,30,2,30,30,30,6,2,62, 62}, 5)) ;
        System.out.println(randomMaxIndex(new int[]{11,30,2,30,30,30,6,2,62, 62}, 10)) ;
    }
}
