package trees.binarysearchtree;

/**
 *  As per RecursiveBinarySearchTree, but iterative implementation
 */

public class IterativeBinarySearchTree implements BinarySearchTree<Integer> {

    private Node root = null;

    @Override
    public void insert(Integer key) {
        Node current = this.root;
        Node parent = null;

        while (current != null) {
            parent = current;

            if (key == current.key) {
                throw new IllegalStateException("Duplicate key: " + key);
            }

            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        Node newNode = Node.node(key);

        if (parent == null) {
            // there was no root node
            this.root = newNode;

        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public boolean contains(Integer key) {

        Node currentNode = this.root;

        while (currentNode != null) {
            if (key == currentNode.key) {
                return true;
            }

            if (key < currentNode.key) {
                currentNode = currentNode.left;
            }

            if (key > currentNode.key) {
                currentNode = currentNode.right;
            }
        }

        return false;
    }
}
