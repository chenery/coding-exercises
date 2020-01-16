package trees.binarysearchtree;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 *
 */
public class RecursiveBinarySearchTreeTest {

    @Test
    public void contains_nullNode() {
        assertThat(new RecursiveBinarySearchTree().contains(1)).isFalse();
    }

    @Test
    public void contains_singleNodeTree_correctValue() {
        BinarySearchTree<Integer> tree = new RecursiveBinarySearchTree();
        tree.insert(1);
        assertThat(tree.contains(1)).isTrue();
    }

    @Test
    public void contains_singleNodeTree_incorrectValue() {
        BinarySearchTree<Integer> tree = new RecursiveBinarySearchTree();
        tree.insert(1);
        assertThat(tree.contains(2)).isFalse();
    }

    @Test
    public void contains_doubleNodeLeftTree_correctValue() {
        BinarySearchTree<Integer> tree = new RecursiveBinarySearchTree();
        tree.insert(2);
        tree.insert(1);

        assertThat(tree.contains(1)).isTrue();
        assertThat(tree.contains(2)).isTrue();
        assertThat(tree.contains(3)).isFalse();
    }

    @Test
    public void contains_threeNodeTree_correctValue() {

        BinarySearchTree<Integer> tree = new RecursiveBinarySearchTree();
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);

        assertThat(tree.contains(1)).isTrue();
        assertThat(tree.contains(2)).isTrue();
        assertThat(tree.contains(3)).isTrue();

        // todo how to assert the node is created in the correct place?
    }
}
