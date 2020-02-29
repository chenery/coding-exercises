package careercup;

/**
 *  https://www.careercup.com/question?id=5715706939703296
 *
 *  Given K sorted (ascending) arrays with N elements in each array,
 *  implement an iterator for iterating over the elements of the arrays in ascending order.
 *
 * The constructor receives all of the input as array of arrays.
 *
 * You need to implement the MyIterator class with a constructor and the following methods:
 *
 *
 * class MyIterator<T> {
 * 	T next();
 * 	boolean hasNext();
 * }
 * You are allowed to use only O(K) extra space with this class.
 *
 * example:
 * input:
 *
 *
 * [[1,5,7], [2,3,10],[4,6,9]]
 * The iterator should return:
 *
 *
 * 1,2,3,4,5,6,7,9,10
 */
public class Iterator<T extends Comparable<T>> {

    // will be length of K input arrays
    private int[] indices;
    private T[][] inputs;
    // the cumulative index of the iterator
    private int counter;

    // input is always K arrays of N, always sorted
    Iterator(T[][] inputs) {
        // usage of space O(K)
        this.indices = new int[inputs.length];
        this.inputs = inputs;
    }

    // each call to next is O(K), so to iterate N*K values we get O(K*N^2)
    T next() {
        // for each K array, consider value at pointer in indices to get the least value
        T minValue = null;
        int minIndex = Integer.MAX_VALUE;
        for (int i = 0; i < this.indices.length; i++) {
            T[] input = this.inputs[i];
            int indexForInput = indices[i];
            if (indexForInput < input.length) {
                T value = input[indexForInput];
                if (minValue == null || value.compareTo(minValue) < 0) {
                    minValue = value;
                    minIndex = i;
                }
            }
        }
        this.indices[minIndex]++;
        this.counter++;
        return minValue;
    }

    boolean hasNext() {
        return this.counter < this.inputs.length * this.inputs[0].length;
    }

    public static void main(String[] args) {
        Integer[][] data = new Integer[][] {
                new Integer[]{1, 5, 7},
                new Integer[]{2, 3, 10},
                new Integer[]{4, 6, 9},
        };
        Iterator<Integer> it = new Iterator<>(data);

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
