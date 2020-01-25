package trees.binarysearchtree;

/**
 *  Binary search tree (BST) is a binary tree where the key of each node is larger or equal to the values in all the
 *  nodes in that node's left subtree and is smaller than the values in all the nodes in that node's right subtree.

 Write a function that checks if a given binary search tree contains a given key.

 For example, for the following tree:

 n1 (Value: 1, Left: null, Right: null)
 n2 (Value: 2, Left: n1, Right: n3)
 n3 (Value: 3, Left: null, Right: null)
 Call to contains(n2, 3) should return true since a tree with root at n2 contains number 3.

 refer to https://en.wikipedia.org/wiki/Binary_search_tree
 https://en.wikipedia.org/wiki/Binary_search_algorithm
 */

public class RecursiveBinarySearchTree implements BinarySearchTree<Integer> {

    private Node root = null;

    @Override
    public void insert(Integer key) {
        this.root = insertAt(this.root, key);
    }

    /**
     * @return the root node
     */
    private Node insertAt(Node currentNode, Integer key) {
        if (currentNode == null) {
            return Node.node(key);
        }
        if (key == currentNode.key) {
            // normally would update the root value if there was one
            return currentNode;

        } else if (key < currentNode.key) {
            // insert to the left subtree
            currentNode.left = insertAt(currentNode.left, key);

        } else {
            // insert to the right subtree
            currentNode.right = insertAt(currentNode.right, key);
        }
        return currentNode;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public boolean contains(Integer key) {
        return containsAt(this.root, key);
    }

    private boolean containsAt(Node currentNode, Integer key) {
        if (currentNode == null) {
            return false;
        }

        if (key == currentNode.key) {
            return true;
        }

        if (key < currentNode.key) {
            // search the left subtree
            return containsAt(currentNode.left, key);

        } else {
            // search the right subtree
            return containsAt(currentNode.right, key);
        }
    }
}
