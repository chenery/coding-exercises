package trees.binarysearchtree;

/**
 * A Tree with key of type T - no associated key is associated with the key
 *
 * Algorithm	Average	    Worst case
 * Space		O(n)	    O(n)
 * Search		O(log n)	O(n)
 * Insert		O(log n)	O(n)
 * Delete		O(log n)	O(n)
 */
public interface BinarySearchTree<T> {

    /**
     *
     * @param key
     * @return the node that was created
     */
    void insert(T key);

    boolean delete(T key);

    /**
     * Simplified search with no return 'value'
     * @return true if key is found in the tree
     */
    boolean contains(T key);
}
