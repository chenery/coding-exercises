package frequencyqueries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class FrequencyQueries {

    /**
     * O(n)
     * @param queries
     * @return
     */
    static List<Integer> freqQuery(int[][] queries) {
        // System.out.println("Num queries: " + queries.size());
        List<Integer> output = new ArrayList<>(300000);
        Map<Integer, Integer> valueToFreq = new HashMap<>(1000000);
        Map<Integer, Integer> freqCount = new HashMap<>(1000000);
        int progress = 0;
        for (int[] query : queries) {
            int operation = query[0];
            int value = query[1];
            // System.out.println(String.format("operation: %d, value %d", operation, value));
            if (operation == 1) {
                int newFreq = valueToFreq.getOrDefault(value, 0) + 1;
                valueToFreq.put(value, newFreq);
                // increment count of new freq
                freqCount.put(newFreq, freqCount.getOrDefault(newFreq, 0) + 1);
                // decrement count of previous freq
                freqCount.put(newFreq - 1, freqCount.getOrDefault(newFreq - 1, 1) - 1);
            } else if (operation == 2) {
                int newFreq = valueToFreq.getOrDefault(value, 0) - 1;
                valueToFreq.put(value, Math.max(newFreq, 0));
                // increment this frequency
                freqCount.put(newFreq, freqCount.getOrDefault(newFreq, 0) + 1);
                // decrement count of previous freq
                freqCount.put(newFreq + 1, freqCount.getOrDefault(newFreq + 1, 1) - 1);
            } else {

                if (freqCount.getOrDefault(value, 0) > 0) {
                    output.add(1);
                } else {
                    output.add(0);
                }
            }
            progress++;
            if (progress % 10000 == 0) {
                System.out.println("Progress: " + progress);
            }
        }
        return output;
    }


    static List<Integer> freqQueryFirstPass(List<int[]> queries) {
        List<Integer> output = new ArrayList<>();
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int[] query : queries) {
            int operation = query[0];
            int value = query[1];
            System.out.println(String.format("operation: %d, value %d", operation, value));
            if (!freqs.containsKey(value)) {
                freqs.put(value, 0);
            }
            if (operation == 1) {
                freqs.put(value, freqs.get(value) + 1);
            } else if (operation == 2) {
                freqs.put(value, Math.max(freqs.get(value) - 1, 0));
            } else {
                if (freqs.containsValue(value)) {
                    output.add(1);
                } else {
                    output.add(0);
                }
            }
        }
        return output;
    }
}
