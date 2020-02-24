package tree.swapnodes;

import java.util.ArrayList;
import java.util.List;

/**
 *  https://www.hackerrank.com/challenges/swap-nodes/problem
 */
public class SwapNodes {

    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int[][] output = new int[queries.length][];
        for (int i = 0; i < queries.length ; i++) {
            int query = queries[i];
            List<Integer> traverseOutput = new ArrayList<>();
            traverse(indexes, 1, 0, query, traverseOutput);
            int[] traverseArr = new int[traverseOutput.size()];
            for (int j = 0; j < traverseOutput.size(); j++) {
                traverseArr[j] = traverseOutput.get(j);
            }
            output[i] = traverseArr;
        }
        return output;
    }

    /**
     * In order traversal
     */
    static void traverse(int[][] indexes, int currentNode, int currentDepth, int query, List<Integer> output) {
        if (currentNode != -1) {
            currentDepth++;
            boolean swap = currentDepth % query == 0;
            if (swap) {
                int tmp = indexes[currentNode - 1][0];
                indexes[currentNode - 1][0] = indexes[currentNode - 1][1];
                indexes[currentNode - 1][1] = tmp;
            }
            // left tree
            traverse(indexes, indexes[currentNode - 1][0], currentDepth, query, output);
            System.out.println(String.format("node value: %d, at depth: %d, (swapped: %b)", currentNode, currentDepth, swap));
            output.add(currentNode);
            // right tree
            traverse(indexes, indexes[currentNode - 1][1], currentDepth, query, output) ;
        }
    }

    public static void main(String[] args) {
        int[][] output = swapNodes(
                new int[][]{
                        {2, 3},
                        {-1, -1},
                        {-1, -1}
                        },
                new int[]{1, 1}
                );
        System.out.println(output);
    }
}
