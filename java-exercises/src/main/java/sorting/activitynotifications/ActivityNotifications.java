package sorting.activitynotifications;

import java.util.Arrays;

/**
 *  https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem
 */
public class ActivityNotifications {

    /**
     * Counting sort implementation
     * @param expenditure
     * @param d
     * @return
     */
    static int activityNotificationsV2(int[] expenditure, int d) {

        System.out.println("expenditure length: " + expenditure.length);
        System.out.println("d: " + d);
        int numNotes = 0;

        final int range = 200;

        // maintain the count array for every iteration
        int[] count = new int[range + 1];

        // iterate over the data after the trailing window
        for (int j = d; j < expenditure.length; j++) {

            if (j > d) {
                // increment count freq for jth value
                System.out.println("Adding count for: " + expenditure[j - 1]);
                ++count[expenditure[j - 1]];
                // decrement count freq for jth - d value
                System.out.println("Removing count for: " + expenditure[j - d - 1]);
                --count[expenditure[j - d - 1]];
            } else {
                // prime with counts before d
                for (int i = 0; i < d; i++) {
                    ++count[expenditure[i]];
                }
            }

            // prefix sum of count array
            int[] prefix = new int[range + 1];
            prefix[0] = count[0];

            for (int i = 1; i < count.length; i++) {
                prefix[i] += prefix[i - 1] + count[i];
            }

            float medianValue;
            if (d % 2 == 0) {
                int firstIndex = d / 2;
                int secondIndex = firstIndex + 1;
                medianValue = findPrefixIndex(prefix, firstIndex)
                        + findPrefixIndex(prefix, secondIndex) / 2f;
            } else {
                medianValue = findPrefixIndex(prefix,d / 2 + 1);
            }
            // count if a notification is sent
            int currentValue = expenditure[j];
            if (currentValue >= medianValue * 2) {
                System.out.println(String.format("Notification sent. Current Value: %d, Median: %f", currentValue, medianValue));
                numNotes++;
            } else {
                System.out.println(String.format("Nothing sent. Current Value: %d, Median: %f", currentValue, medianValue));
            }
        }

        return numNotes;
    }

    static int findPrefixIndex(int[] prefix, int forFrequency) {
        for (int p = 0; p < prefix.length; p++) {
            if (prefix[p] >= forFrequency) {
                return p;
            }
        }
        throw new RuntimeException("findPrefixIndex not found");
    }

    static int activityNotificationsCountingSort(int[] expenditure, int d) {

        System.out.println("expenditure length: " + expenditure.length);
        System.out.println("d: " + d);
        int numNotes = 0;

        final int range = 200;

        // iterate over the data after the trailing window
        for (int j = d; j < expenditure.length; j++) {

            int[] count = new int[range + 1];
            int[] output = new int[d];

            for (int i = 0 ; i < d; i++) {
                ++count[expenditure[j + i - d]];
            }

            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            for (int i = 0; i < d; i++) {
                int exValue = expenditure[j + i - d];
                int indexForValue = count[exValue] - 1; // -1 just moves to zero based indexing
                output[indexForValue] = exValue;
                --count[exValue];
            }

            float medianValue;
            if (d % 2 == 0) {
                medianValue = (output[d / 2 - 1] + output[d / 2]) / 2f;
            } else {
                medianValue = output[d / 2];
            }
            // count if a notification is sent
            int currentValue = expenditure[d];
            if (currentValue >= medianValue * 2) {
                numNotes++;
            }
        }

        return numNotes;
    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {

        System.out.println("expenditure length: " + expenditure.length);
        System.out.println("d: " + d);
        int numNotes = 0;
        // iterate over the data after the trailing window
        for (int i = d; i < expenditure.length; i++) {

            // create an array of size d in which to sort the window data (don't mess with the original data)
            int[] window = new int[d];
            for (int j = 0 ; j < d; j++) {
                window[j] = expenditure[i + j - d];
            }
            // sort the array using insertion sort
            Arrays.sort(window);
            // locate/calculate the median
            float medianValue;
            if (d % 2 == 0) {
                medianValue = (window[d / 2 - 1] + window[d / 2]) / 2f;
            } else {
                medianValue = window[d / 2];
            }
            // count if a notification is sent
            int currentValue = expenditure[d];
            if (currentValue >= medianValue * 2) {
                numNotes++;
            }
        }
        return numNotes;
    }

    public static void main(String[] args) {
        // 2
        System.out.println(activityNotificationsV2(new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5}, 5));

        // 0
        System.out.println(activityNotificationsV2(new int[]{1, 2, 3, 4, 4}, 4));

        // 2
        System.out.println(activityNotificationsV2(new int[]{1, 2, 3, 4, 8, 20}, 4));
    }
}
