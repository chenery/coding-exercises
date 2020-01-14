package TestDome.binarysearchtree;

/**
 *  Binary search tree (BST) is a binary tree where the value of each node is larger or equal to the values in all the
 *  nodes in that node's left subtree and is smaller than the values in all the nodes in that node's right subtree.

 Write a function that checks if a given binary search tree contains a given value.

 For example, for the following tree:

 n1 (Value: 1, Left: null, Right: null)
 n2 (Value: 2, Left: n1, Right: n3)
 n3 (Value: 3, Left: null, Right: null)
 Call to contains(n2, 3) should return true since a tree with root at n2 contains number 3.

 refer to https://en.wikipedia.org/wiki/Binary_search_tree
 */
public class BinarySearchTree {
    public static boolean contains(Node root, int value) {

        return nodeCheck(root, value);
    }

    /**
     *  Note the search can be implements either recursively or iteratively, here recursive is used.
     */
    private static boolean nodeCheck(Node node, int value) {

        if (node == null) {
            return false;
        }

        if (node.value == value) {
            return true;
        }

        boolean checkLeftBranch = node.value >= value;
        boolean checkRightBranch = node.value <= value;

        boolean isInLeftBranch = checkLeftBranch && nodeCheck(node.left, value);
        return isInLeftBranch || checkRightBranch && nodeCheck(node.right, value);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);

        System.out.println(contains(n2, 3));
    }
}
