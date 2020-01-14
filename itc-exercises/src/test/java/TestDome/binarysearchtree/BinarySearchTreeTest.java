package TestDome.binarysearchtree;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 *
 */
public class BinarySearchTreeTest {

    @Test
    public void contains_nullNode() {
        assertThat(BinarySearchTree.contains(null, 1)).isFalse();
    }

    @Test
    public void contains_singleNodeTree_correctValue() {

        Node singleNode = new Node(1, null, null);

        assertThat(BinarySearchTree.contains(singleNode, 1)).isTrue();
    }

    @Test
    public void contains_singleNodeTree_incorrectValue() {

        Node singleNode = new Node(1, null, null);

        assertThat(BinarySearchTree.contains(singleNode, 2)).isFalse();
    }

    @Test
    public void contains_doubleNodeLeftTree_correctValue() {

        Node leftLeaf = new Node(1, null, null);
        Node root = new Node(2, leftLeaf, null);

        assertThat(BinarySearchTree.contains(leftLeaf, 1)).isTrue();
    }

    @Test
    public void contains_threeNodeTree_correctValue() {

        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);

        assertThat(BinarySearchTree.contains(n2, 3)).isTrue();
    }
}
