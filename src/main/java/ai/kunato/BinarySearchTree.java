package ai.kunato;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    private final List<TreeNode> versions;
    private TreeNode currentRoot;
    private int currentVersion;
    public BinarySearchTree() {
        this.versions = new ArrayList<>();
        this.currentVersion = 0;
    }

    public TreeNode search(int version, int key) {
        assert version < versions.size();
        TreeNode root = versions.get(version);
        return search(root, key);
    }

    private TreeNode search(TreeNode root, int key) {
        while (root != null) {
            if(root.getValue() == key) {
                return root;
            } else if (key > root.getValue()) {
                root = root.getRight();
            } else {
                root = root.getLeft();
            }
        }
        return null;
    }

    public int insert(int key) {
        TreeNode newRoot = insert(currentRoot, key);
        assert currentRoot == null || newRoot.hashCode() != currentRoot.hashCode();
        versions.add(currentRoot);
        currentRoot = newRoot;
        return ++currentVersion;
    }

    private TreeNode insert(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key, null, null);
        } else if(key > root.getValue()) {
            return new TreeNode(root.getValue(), root.getLeft(), insert(root.getRight(), key));
        } else if(key < root.getValue()) {
            return new TreeNode(root.getValue(), insert(root.getLeft(), key), root.getRight());
        } else {
            return new TreeNode(root.getValue(), root.getLeft(), root.getRight());
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }
}
