package TestDome.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 *  Write a function that, given a list and a target sum, returns zero-based indices of any two distinct elements
 *  whose sum is equal to the target sum. If there are no such elements, the function should return null.

 For example, findTwoSum(new int[] { 1, 3, 5, 7, 9 }, 12) should return a single dimensional array with two elements
 and contain any of the following pairs of indices:

 1 and 4 (3 + 9 = 12)
 2 and 3 (5 + 7 = 12)
 3 and 2 (7 + 5 = 12)
 4 and 1 (9 + 3 = 12)

 30 mins
 */
public class TwoSum {
    public static int[] findTwoSum(int[] list, int sum) {

        if (list == null || list.length == 0) {
            return null;
        }

        // map values is the list to their index
        Map<Integer, Integer> valueMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < list.length; i++) {
            int element = list[i];
            // ignore the key if it's greater than our sum
            if (element <= sum) {
                valueMap.put(element, i);
            }
        }

        for (int j = 0; j < list.length; j++) {
            int secondElement = list[j];
            // determine what is necessary to make the sum
            int requiredValue = sum - secondElement;
            // skip negative values, they break this logic
            if (requiredValue > 0 && valueMap.containsKey(requiredValue)) {
                int indexOfRequiredValue = valueMap.get(requiredValue);
                // we found a corresponding key to make the sum -> return the indices
                return new int[] {indexOfRequiredValue, j};
            }
        }

        return null;
    }

    private static int[] findTwoSumBruteForce(int[] list, int sum) {
        if (list == null || list.length == 0) {
            return null;
        }

        for (int i = 0; i < list.length; i++) {
            int firstElement = list[i];
            for (int j = 0; j < list.length; j++) {
                // skip the firstElement
                if (i == j) {
                    continue;
                }
                int secondElement = list[j];
                int sumOfElements = firstElement + secondElement;
                if (sumOfElements == sum) {
                    return new int[] {i, j};
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[] { 1, 3, 5, 7, 9 }, 12);
        System.out.println(indices[0] + " " + indices[1]);
    }
}
