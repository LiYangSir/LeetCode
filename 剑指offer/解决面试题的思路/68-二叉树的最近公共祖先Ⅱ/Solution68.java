import cn.quguai.TreeNode;

public class Solution68 {

    /**
     * 节点相同的一定是可疑点直接返回，要么是祖宗，要么是旁支，无需向下继续进行遍历
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left == null ? right: left;
    }
}
