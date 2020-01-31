package tree.lca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class LowestCommonAncestor {

    /**
     * The LCA for binary search tree is the node n where it's data is > v1 and < v2
     * Time complexity is O(h) where h is the height of the tree.
     * Space: O(h) for the addition call stack frame memory using during the recursion.
     *
     * @param root
     * @param v1
     * @param v2
     * @return
     */
    public static Node lcaV2(Node root, int v1, int v2) {
        if (root == null || root.data == v1 || root.data == v2) {
            return root;
        }

        if (root.data < v1 && root.data < v2) {
            return lcaV2(root.right, v1, v2);
        }

        if (root.data > v1 && root.data > v2) {
            return lcaV2(root.left, v1, v2);
        }
        return root;
    }

    /**
     * The LCA for binary search tree is the node n where it's data is > v1 and < v2
     * Time complexity is O(h) where h is the height of the tree.
     * Space: O(1) for this iterative solution
     *
     * @param root
     * @param v1
     * @param v2
     * @return
     */
    public static Node lcaIterative(Node root, int v1, int v2) {

        while (root != null) {
            if (root.data < v1 && root.data < v2) {
                root = root.right;
            } else if (root.data > v1 && root.data > v2) {
                root = root.left;
            } else {
                break;
            }
        }

        return root;
    }

    /*
    This has poor performance due to multiple passes of the datastructure
    */
    public static Node lca(Node root, int v1, int v2) {
        List<Node> v1Ancestors = new ArrayList<>();
        List<Node> v2Ancestors = new ArrayList<>();

        ancestors(root, v1, v1Ancestors);
        ancestors(root, v2, v2Ancestors);

        for (int i = v1Ancestors.size() - 1; i < v1Ancestors.size(); i--) {
            Node v1Ancestor = v1Ancestors.get(i);
            if (v2Ancestors.contains(v1Ancestor)) {
                return v1Ancestor;
            }
        }
        System.out.println("Unable to find LCA");
        return null;
    }

    public static void ancestors(Node root, int key, List<Node> ancestors) {
        ancestors.add(root);
        if (root == null || root.data == key) {
            return;
        }
        if (key < root.data) {
            ancestors(root.left, key, ancestors);
        } else {
            ancestors(root.right, key, ancestors);
        }
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner("6\n4 2 3 1 7 6\n1 7");
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node ans = lcaIterative(root,v1,v2);
        System.out.println(ans.data);
    }
}