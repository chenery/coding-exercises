package strings;

/**
 *  FB: Q3
 *  During a walk a hiker takes n steps.
 *  Each step is uphill, U, or downhill D, and represents 1 unit.
 *  The walk starts and ends at altitude 0.
 *  A mountain is a sequence of consecutive steps above zero altitude, ending with a step down to zero.
 *  A valley is the opposite.
 */
public class CountingValleys {

    /**
     * Space: O(n) where n is length of s
     * Execution: O(n)
     *
     * @param n number of steps taken, 2 <= n <= 10,000,000
     * @param s a string describing the path, contains only U and D
     * @return number of valleys walked through
     */
    static int countingValleys(int n, String s) {

        int altitude = 0;
        boolean lastWasValley = false;
        int numValleys = 0;

        for (char c : s.toCharArray()) {
            if (c == 'U') {
                altitude++;
            } else {
                altitude--;
            }

            if (altitude == 0) {
                // transition from valley to hill or vice versa
                if (lastWasValley) {
                    numValleys++;
                }
            } else lastWasValley = altitude < 0;
        }
        return numValleys;
    }

    /**
     * Space: O(1)
     * Execution: O(n)
     *
     * @param n number of steps taken, 2 <= n <= 10,000,000
     * @param s a string describing the path, contains only U and D
     * @return number of valleys walked through
     */
    static int countingValleysV2(int n, String s) {

        int altitude = 0;
        boolean lastWasValley = false;
        int numValleys = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == 'U') {
                altitude++;
            } else {
                altitude--;
            }

            if (altitude == 0) {
                // transition from valley to hill or vice versa
                if (lastWasValley) {
                    numValleys++;
                }
            } else lastWasValley = altitude < 0;
        }

        return numValleys;
    }

    public static void main(String[] args) {
        int result = countingValleysV2(8, "UDDDUDUU");
        System.out.println(result);
    }
}
