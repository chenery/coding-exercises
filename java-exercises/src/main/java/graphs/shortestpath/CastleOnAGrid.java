package graphs.shortestpath;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 *  https://www.hackerrank.com/challenges/castle-on-the-grid/problem
 */
public class CastleOnAGrid {

    static class Node {
        private Node parent;
        private int row;
        private int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        List<Node> getAdjacentEdges(char[][] board) {
            List<Node> adjacent = new ArrayList<>();

            // get all South moves
            int proposedRow = this.row + 1;
            int proposedCol = this.col;
            while(isValid(proposedRow, proposedCol, board)) {
                adjacent.add(new Node(proposedRow, proposedCol));
                proposedRow += 1;
            }

            // get all North moves
            proposedRow = this.row - 1;
            proposedCol = this.col;
            while(isValid(proposedRow, proposedCol, board)) {
                adjacent.add(new Node(proposedRow, proposedCol));
                proposedRow -= 1;
            }

            // get all East moves
            proposedRow = this.row;
            proposedCol = this.col + 1;
            while(isValid(proposedRow, proposedCol, board)) {
                adjacent.add(new Node(proposedRow, proposedCol));
                proposedCol += 1;
            }

            // get all West moves
            proposedRow = this.row;
            proposedCol = this.col - 1;
            while(isValid(proposedRow, proposedCol, board)) {
                adjacent.add(new Node(proposedRow, proposedCol));
                proposedCol -= 1;
            }

            return adjacent;
        }

        boolean isValid(int row, int col, char[][] board) {
            int boardSize = board.length;
            boolean result = (row >= 0 && row < boardSize
                && col >= 0 && col < boardSize
                && board[row][col] == '.');
//            System.out.println(String.format("isValid() row: %d, col %d: %b", row, col, result));
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return row == node.row &&
                    col == node.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    /**
     *  Performance O(|V| + |E|) in the worse case
     *  Space O(|V|)
     */
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        /*
            todo:
                - implement BFS to traverse the graph and the goal node
         */
        int boardSize = grid.length;
        char[][] board = new char[boardSize][];
        for (int i = 0; i < boardSize; i++) {
            board[i] = grid[i].toCharArray();
        }
        int result = bfs(board, new Node(startX, startY), new Node(goalX, goalY));
        System.out.println("Found target in moves: " + result);
        return result;
    }

    /**
     * @return number of moves to find the goal or -1 if not found.
     */
    static int bfs(char[][] board, Node start, Node goal) {
        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> discovered = new HashSet<>();
        queue.add(start);
        discovered.add(start);

        while(!queue.isEmpty()) {
            Node v = queue.remove();
            System.out.println("Considering v: " + v);
            if (v.equals(goal)) {
                int numMoves = 1;
                Node parent = v.parent;
                while (!parent.equals(start)) {
                    numMoves++;
                    parent = parent.parent;
                }
                return numMoves;
            }
            for (Node edge : v.getAdjacentEdges(board)) {
                // stop infinite iterative of cyclic graphs (and going backwards)
                if (!discovered.contains(edge)) {
                    edge.parent = v;
                    discovered.add(edge);
                    queue.add(edge);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // 3
        System.out.println(minimumMoves(new String[]{".X.", ".X.", "..."}, 0, 0, 0, 2));
    }
}
