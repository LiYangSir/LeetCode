import cn.quguai.TreeNode;

public class Solution54 {
    private int count = 0;
    private int val = 0;
    public int kthLargest(TreeNode root, int k) {
        dfs(root, k);
        return val;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) return;
        dfs(root.right, k);
        if (++count == k){
            val = root.val;
            return;
        }
        dfs(root.left, k);
    }
}
