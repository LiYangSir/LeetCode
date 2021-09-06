
import cn.quguai.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution32 {
    public int[] levelOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return new int[0];
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            list.add(poll.val);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
