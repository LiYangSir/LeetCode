import cn.quguai.TreeNode;

public class Solution55 {

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) >= 0;
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    private int getAbsHeight(TreeNode root){
        if (root == null) return 0;
        int left = getAbsHeight(root.left);
        int right = getAbsHeight(root.right);
        if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
