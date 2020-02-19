package hashtable.counttriplets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  https://www.hackerrank.com/challenges/count-triplets-1/problem
 *  triplet indices i, j, k such that i < j < k
 */
public class CountTriplets {

    /**
     * O(n)
     * @param arr
     * @param r
     * @return
     */
    static long countTripletsV2(List<Long> arr, long r) {
        System.out.println("r: " + r);
        System.out.println("arr size: " + arr.size());
        // for triplet, <a, b, c>
        Map<Long, Long> countRequireValueForB = new HashMap<>();
        Map<Long, Long> countRequireValueForC = new HashMap<>();
        long count = 0;
        for (int i = 0; i < arr.size(); i++) {
            long value = arr.get(i);
            long nextProgression = r * value;
            count += countRequireValueForC.getOrDefault(value, 0L);
            if (countRequireValueForB.containsKey(value)) {
                countRequireValueForC.put(nextProgression, countRequireValueForC.getOrDefault(nextProgression, 0L) + countRequireValueForB.get(value));
            }
            countRequireValueForB.put(nextProgression, countRequireValueForB.getOrDefault(nextProgression, 0L) + 1);
        }
        return count;
    }

    /**
     * Worse case when r = 1 and all the values are the same, O(n^3), O(n) best case
     * @param arr
     * @param r
     * @return
     */
    static long countTriplets(List<Long> arr, long r) {
        System.out.println("r: " + r);
        System.out.println("arr size: " + arr.size());

        long count = 0;
        Map<Long, Set<Integer>> valueToIndices = new HashMap<>();
        for (int j = 0; j < arr.size(); j++) {
            long value = arr.get(j);
            System.out.print(" " + value);
            Set<Integer> indices = valueToIndices.getOrDefault(value, new HashSet<>());
            indices.add(j);
            valueToIndices.put(value, indices);
        }
        System.out.println("");

        for (int i = 0; i < arr.size(); i++) {
            long firstValue = arr.get(i);
            long secondValue = r * firstValue;
            long thirdValue = r * secondValue;

            if (valueToIndices.containsKey(secondValue) && valueToIndices.containsKey(thirdValue)) {
                int firstIndex = i;
                for (Integer secondIndex : valueToIndices.get(secondValue)) {
                    for (Integer thirdIndex : valueToIndices.get(thirdValue)) {
                        if (firstIndex < secondIndex && secondIndex < thirdIndex) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // 6
        System.out.println(countTripletsV2(Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L), 3L));

        // 4
        System.out.println(countTripletsV2(Arrays.asList(1L, 1L, 1L, 1L), 1L));
    }
}
