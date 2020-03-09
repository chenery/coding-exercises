package careercup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  https://www.careercup.com/question?id=5687609083297792
 *
 *  Given a list of arrays of time intervals, write a function that calculates the total amount of time covered by the intervals.
 *  For example:
 *  input = [(1,4), (2,3)]
 *  return 3
 *  input = [(4,6), (1,2)]
 *  return 3
 *  input = {{1,4}, {6,8}, {2,4}, {7,9}, {10, 15}}
 *  return 11
 *
 *  Thoughts:
 *      - Brute force approach - merge each on to the next, repeat until no merges are made O(n^2)?
 *      - Brute force approach - merge each to every previous
 *      - once merges are done then can simply sum the diffs of the periods
 *
 *      - iterative each interval and write to a set a key for each hour, then count the keys. O(nm) n number of intervals,
 *      m is the total time worse case. O(m) space
 *
 *      - Simplify the merge by first sorting by the start time, then merge the next back into previous
 *      https://stackoverflow.com/questions/31670849/merge-overlapping-intervals, this gives T(n log n) + T(n) -> O(n log n)
 */
public class TimeIntervals {

    static class Interval implements Comparable<Interval> {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Interval b) {
            if (this.start <= b.start) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    /*
     * Assume intervals are valid, and result in positive integer intervals
     */
    static int totalTime(List<Interval> intervals) {

        // sort the intervals by start time
        Collections.sort(intervals);

        Interval first = intervals.get(0);
        int currentStart = first.start;
        int currentEnd = first.end;
        int total = 0;
        for (int i = 1; i < intervals.size(); i++) {
            Interval i2 = intervals.get(i);

            if (i2.start < currentEnd) {
                // overlapping
                currentEnd = i2.end;

            } else {
                // not overlapping
                // add the previous window to the total and ignore the gap
                total += currentEnd - currentStart;
                currentStart = i2.start;
                currentEnd = i2.end;
            }
        }

        return currentEnd - currentStart + total;
    }

    public static void main(String[] args) {
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(6, 8));
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(7, 9));
        intervals.add(new Interval(10, 15));

        System.out.println(totalTime(intervals));
    }
}
