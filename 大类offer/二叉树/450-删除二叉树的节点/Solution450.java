public class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // 节点左右两边都有节点
            TreeNode min = getMin(root.right);
            root.val = min.val;
            root.right = deleteNode(root.right, min.val);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMin(TreeNode right) {
        while (right.left != null)
            right = right.left;
        return right;
    }
}
