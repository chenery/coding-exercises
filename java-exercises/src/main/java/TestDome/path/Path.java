package TestDome.path;

/**
 * 30mins hard
 *
 *  Write a function that provides change directory (cd) function for an abstract file system.

 Notes:

 Root path is '/'.
 Path separator is '/'.
 Parent directory is addressable as "..".
 Directory names consist only of English alphabet letters (A-Z and a-z).
 The function will not be passed any invalid paths.
 Do not use built-in path-related functions.
 For example:

 Path path = new Path("/a/b/c/d");
 path.cd('../x');
 System.out.println(path.getPath());
 should display '/a/b/c/x'.
 */
public class Path {
    private TreeNode currentPath;

    public Path(String path) {
        if (path == null || !path.startsWith("/")) {
            throw new IllegalArgumentException("Path my start with /");
        }

        String[] folders = path.substring(1).split("/");
        TreeNode nextParentNode = new TreeNode("", null);
        for (String folder : folders) {
            TreeNode folderNode = new TreeNode(folder, nextParentNode);
            nextParentNode.addChild(folderNode);
            nextParentNode = folderNode;
        }
        currentPath = nextParentNode;
    }

    public String getPath() {
        return this.currentPath.getPath();
    }

    public void cd(String newPath) {
        String[] pathElements = newPath.split("/");
        for (String pathElement : pathElements) {
            if (pathElement.equals("..")) {
                this.currentPath = this.currentPath.getParent();
            } else {
                this.currentPath = new TreeNode(pathElement, this.currentPath);
            }
        }

    }

    public static void main(String[] args) {
        Path path = new Path("/a/b/c/d");
        path.cd("../x");
        System.out.println(path.getPath());
    }
}

