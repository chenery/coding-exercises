package TestDome.path;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TreeNode {

    private String value;
    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<TreeNode>();

    public TreeNode(String value, TreeNode parent) {
        this.value = value;
        this.parent = parent;
    }

    public void addChild(TreeNode childNode) {
        children.add(childNode);
    }

    public String getValue() {
        return value;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getParent() {
        return parent;
    }

    public boolean isRootNode() {
        return parent == null;
    }

    public String getPath() {
        String path = "";
        TreeNode currentNode = this;
        while (!currentNode.isRootNode()) {
            path = "/" + currentNode.getValue() + path;
            currentNode = currentNode.getParent();
        }

        return path;
    }
}
