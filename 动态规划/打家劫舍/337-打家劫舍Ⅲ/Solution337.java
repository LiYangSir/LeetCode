import java.util.HashMap;
import java.util.Map;

public class Solution337 {
    private Map<TreeNode, Integer> cache = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (cache.containsKey(root)) return cache.get(root);
        int do_it = root.val +
                (root.left == null? 0: rob(root.left.left) + rob(root.left.right)) +
                (root.right == null? 0: rob(root.right.left) + rob(root.right.right));
        int not_do = rob(root.left) + rob(root.right);
        int max = Math.max(do_it, not_do);
        cache.put(root, max);
        return max;
    }
}
