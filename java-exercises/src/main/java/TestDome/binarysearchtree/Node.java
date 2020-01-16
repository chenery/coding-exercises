package TestDome.binarysearchtree;

/**
 *
 */
class Node {
    int key;
    Node left, right;

    Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    static Node node(int key) {
        return new Node(key, null, null);
    }
}
