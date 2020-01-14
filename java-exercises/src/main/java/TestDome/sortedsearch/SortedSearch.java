package TestDome.sortedsearch;

/**
 *  20mins hard
 *
 *  Implement function countNumbers that accepts a sorted array of integers and counts the number of array elements
 *  that are less than the parameter lessThan.

 For example, SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4) should return 2 because there are two array
 elements less than 4.

 https://en.wikipedia.org/wiki/Binary_search_algorithm
 */
public class SortedSearch {
    public static int countNumbers(int[] sortedArray, int lessThan) {

        if (sortedArray == null || sortedArray.length == 0) {
            return 0;
        }

        int upperIndex = sortedArray.length - 1;
        return check(sortedArray, 0, upperIndex, lessThan);
    }

    private static int check(int[] sortedArray, int lowerBoundIndex, int upperBoundIndex, int lessThan) {

        int indexToCheck = (lowerBoundIndex + upperBoundIndex) / 2;
        int valueAtCheck = sortedArray[indexToCheck];
        if (valueAtCheck < lessThan) {

            if (lowerBoundIndex == upperBoundIndex) {
                return indexToCheck + 1;
            }

            // ascending
            int newLowerBound = indexToCheck + 1;

            return check(sortedArray, newLowerBound, upperBoundIndex, lessThan);
        } else {

            // descending
            int newUpperBound = indexToCheck;

            if (newUpperBound <= lowerBoundIndex) {
                return indexToCheck;
            }

            return check(sortedArray, lowerBoundIndex, newUpperBound, lessThan);
        }
    }

    private static int countNumbersBruteForce(int[] sortedArray, int lessThan) {
        if (sortedArray == null || sortedArray.length == 0) {
            return 0;
        }

        int count = 0;
        for (int value : sortedArray) {
            if (value < lessThan) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
    }
}
