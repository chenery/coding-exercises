package greedy.florist;

import java.util.Arrays;

/**
 *  https://www.hackerrank.com/challenges/greedy-florist/problem
 */
public class GreedyFlorist {

    /**
     * O(n + nlogn) - nlogn for the sort
     * O(k) space -> this can be avoided todo, by introducing a nested for loop
     * @param k
     * @param c
     * @return
     */
    static int getMinimumCost(int k, int[] c) {
        // number of flowers to buy
        int numToBuy = c.length;
        int numPurchased = 0;
        int totalCost = 0;

        // count of flowers purchased by each friend
        int[] purchases = new int[k];

        Arrays.sort(c);

        while (numPurchased != numToBuy) {
            // each person takes turns to buy
            int person = (numPurchased % numToBuy) % k;

            // always buy the most expensive remaining flower
            int originalCost = c[numToBuy - numPurchased - 1];

            int cost = (purchases[person] + 1) * originalCost;
            totalCost += cost;
            numPurchased++;
            purchases[person]++;
        }
        return totalCost;
    }

    public static void main(String[] args) {
        // 13
        System.out.println(getMinimumCost(3, new int[]{2, 5, 6}));

        // 15
        System.out.println(getMinimumCost(2, new int[]{2, 5, 6}));
    }
}
