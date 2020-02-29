package careercup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 *  You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class TwoSum {

    static int[] twoSumV2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] b = new int[2];

        for(int i=0; i<nums.length ; i++) {
            if(map.containsKey(nums[i])) {
                b[0] =i;
                b[1] =map.get(nums[i]);
                break;
            }else
                map.put( target - nums[i] , i);
        }
        return b;
    }

    // O(N)
    static int[] twoSum(int[] input, int target) {

        // map the inputs value to a set of indexes (to handle duplicate values), O(N)
        Map<Integer, Set<Integer>> valueToIndex = new HashMap<>();
        for (int i = 0; i< input.length; i++) {
            int value = input[i];
            Set<Integer> indexes = valueToIndex.getOrDefault(value, new HashSet<>());
            indexes.add(i);
            valueToIndex.put(input[i], indexes);
        }

        for (int i = 0; i < input.length; i++) {
            int value = input[i];
            int requires = target - value;
            if (valueToIndex.containsKey(requires)) {
                Set<Integer> indexes = valueToIndex.get(requires);
                // cannot use same element
                indexes.remove(i);
                if (!indexes.isEmpty()) {
                    return new int[]{i, indexes.iterator().next()};
                }
                // add back this element for future
                indexes.add(i);
            }
        }

        throw new RuntimeException("No solution found");
    }

    public static void main(String[] args) {
        for (int index : twoSumV2(new int[]{2, 7, 11, 15}, 9)) {
            System.out.println(index);
        }

        for (int index : twoSumV2(new int[]{2, 3, 3, 15}, 6)) {
            System.out.println(index);
        }
    }
}
