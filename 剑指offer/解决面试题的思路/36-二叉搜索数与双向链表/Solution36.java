import cn.quguai.TreeNode;

/**
 * @author LiYangSir
 * @date 2021/6/16
 */
public class Solution36 {
    TreeNode pre, head;
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (pre == null) head = root;
        else pre.right = root;
        root.left = pre;
        pre = root;
        dfs(root.right);
    }
}
