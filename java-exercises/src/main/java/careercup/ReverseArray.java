package careercup;

/**
 * reverse an array for k distance.
 * [1,2,3,4,5] and k =3
 * output : [2,3,1,5,4]
 * method
 * void reverse(int[] arr, k)
 *
 * this method will only reverse the array
 *
 * write another method which will sort the array by incorporating reverse method inside sort.
 *
 * You must have to call reverse(arr,k) method to sort the array. You are not allowed to modify the reverse method
 */
public class ReverseArray {

    /**
     *
     * @param input, can be odd or even length
     * @param k, assume less or equal length of input
     */
    static int[] reverse(int[] input, int k) {

        // N / K * O(K/2) -> O(N)
        for (int startIndex = 0; startIndex < input.length; startIndex += k) {
            // swap elements at opposite ends of the array, moving to the centre, O(0.5K)
            for (int i = startIndex, j = Math.min(startIndex + k - 1, input.length - 1); i < j; i++, j--) {
                int tmp = input[i];
                input[i] = input[j];
                input[j] = tmp;
            }
        }

        return input;
    }

    // O(N^2) as reverse becomes swap which is O(1)
    static int[] sort(int[] input) {
        for (int i = 1; i < input.length; i++) {
            for (int j = 1; j < input.length; j++) {
                if (input[j] < input[j - 1]) {
                    int[] sortedFragment = reverse(new int[]{input[j - 1], input[j]}, 2);
                    input[j - 1] = sortedFragment[0];
                    input[j] = sortedFragment[1];
                }
            }
        }
        return input;
    }

    public static void main(String[] args) {
        // 3, 2, 1, 5, 4
        for (int index : reverse(new int[]{1, 2, 3, 4, 5}, 3)) {
            System.out.println(index);
        }

        for (int index : sort(new int[]{4, 1, 3, 2, 5})) {
            System.out.println(index);
        }

        for (int index : sort(new int[]{5, 4, 3, 2, 1})) {
            System.out.println(index);
        }
    }
}
