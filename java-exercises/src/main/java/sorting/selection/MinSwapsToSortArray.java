package sorting.selection;

import java.util.PriorityQueue;

/**
 *  https://www.hackerrank.com/challenges/minimum-swaps-2/problem
 */
public class MinSwapsToSortArray {

    /**
     * O(n^2) comparisons, O(n) swaps
     * @param arr unordered array consisting of consecutive integers
     * @return minimum number of swaps to sort the array in ascending order
     */
    static int minimumSwapsSelectionSort(int[] arr) {
        int swaps = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minRemainderIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minRemainderIndex] > arr[j]) {
                    minRemainderIndex = j;
                }
            }
            if (minRemainderIndex != i) {
                // swap
                int tmp = arr[i];
                arr[i] = arr[minRemainderIndex];
                arr[minRemainderIndex] = tmp;
                swaps++;
            }
        }
        return swaps;
    }

    static int minimumSwapsSpecialSort(int[] arr) {
        int swaps = 0;
        for (int i = 0; i < arr.length - 1; i++) {

            int expectedValue = i + 1;
            int currentValue = arr[i];
            // case 1: already the correct value
            if (currentValue != expectedValue) {

                // case 2: find the index of the current value
                int minRemainderIndex = i + 1;
                while (arr[minRemainderIndex] != expectedValue) {
                    minRemainderIndex++;
                }

                // swap
                int tmp = arr[i];
                arr[i] = arr[minRemainderIndex];
                arr[minRemainderIndex] = tmp;
                swaps++;
            }
        }
        return swaps;
    }

    static class Node implements Comparable<Node> {
        private Integer index;
        private Integer value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value.compareTo(o.value);
        }

        @Override
        public boolean equals(Object obj) {
            return this.value.equals(((Node) obj).value);
        }

        @Override
        public String toString() {
            return String.format("%d (at index %d)", value, index);
        }
    }

    /**
     * O(n^2) comparisons, O(n) swaps
     */
    static int minimumSwapsHeapSort(int[] arr) {
        int swaps = 0;
        PriorityQueue<Node> remainder = new PriorityQueue<>();
        for (int i = 1; i < arr.length; i++) {
            remainder.add(new Node(i, arr[i]));
        }
        for (int i = 0; i < arr.length - 1; i++) {
            Node minRemainder = remainder.peek();
            if (minRemainder.getValue() < arr[i]) {
                System.out.println(String.format("Swap %d at index %d with %d at index %d", arr[i], i,
                        minRemainder.getValue(), minRemainder.getIndex()));
                // swap
                minRemainder = remainder.poll();
                int tmp = arr[i];
                arr[i] = minRemainder.getValue();
                arr[minRemainder.getIndex()] = tmp;
                remainder.add(new Node(minRemainder.getIndex(), tmp));
                swaps++;
                remainder.remove(new Node(i, tmp));
            } else {
                remainder.remove(new Node(i, arr[i]));
                System.out.println(String.format("SKIP %d at index %d with %d at index %d", arr[i], i,
                        minRemainder.getValue(), minRemainder.getIndex()));
            }

        }
        return swaps;
    }

    public static void main(String[] args) {
        // 3
        System.out.println(minimumSwapsSpecialSort(new int[]{4, 3, 1, 2}));

        // 3
        System.out.println(minimumSwapsSpecialSort(new int[]{1, 3, 5, 2, 4, 6, 7}));

        // 9
        System.out.println(minimumSwapsSpecialSort(new int[]{3, 7, 6, 9, 1, 8, 10, 4, 2, 5}));
    }
}
