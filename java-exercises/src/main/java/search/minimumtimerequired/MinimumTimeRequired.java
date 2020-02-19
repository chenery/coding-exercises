package search.minimumtimerequired;

/**
 *  https://www.hackerrank.com/challenges/minimum-time-required/problem
 */
public class MinimumTimeRequired {

    /**
     *  Binary search the range of possible values, 1 <= n <= 10^5, calculating the number of production on that day.
     *  O(mlog n) where m is the number of machines.
     */
    static long minTime(long[] machines, long goal) {
        System.out.println("machines: " + machines.length);
        System.out.println("goal: " + goal);

        // todo work out the min and max number of days based on the min and max machine speeds
        long l = 1;
        long r = (long) Math.pow(10, 13);
        long minGoalAchieved = -1;
        while (l <= r) {
            long m = (l + r) / 2;
            long prod = prodAfterDays(machines, m);
//            System.out.println(String.format("l: %d, r: %d, m: %d, prod: %d", l, r, m, prod));
            if (prod < goal) {
                l = m + 1;
            } else {
                minGoalAchieved = m;
                r = m - 1;
            }
        }
        return minGoalAchieved;
    }

    static long prodAfterDays(long[] machines, long day) {
        long prod = 0;
        for (Long machine: machines) {
            prod += day / machine;
        }
        return prod;
    }

    /**
     *  O(nm) where n is the number of days and m is the number of machines
     */
    static long minTimeBruteForce(long[] machines, long goal) {
        System.out.println("machines: " + machines.length);
        System.out.println("goal: " + goal);
        long day = 1;
        long prod = 0;
        while (prod < goal) {
            for (Long machine: machines) {
                if (day % machine == 0) {
                    prod++;
                }
            }
            day++;
        }
        return day - 1;
    }

    public static void main(String[] args) {
        // 6
        System.out.println(minTime(new long[]{2, 3}, 5));

        // 20
        System.out.println(minTime(new long[]{4, 5, 6}, 12));
    }
}
