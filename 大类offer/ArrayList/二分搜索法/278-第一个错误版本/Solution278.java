import java.util.*;

public class Solution278 {
    public static int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean isBadVersion(int mid) {
        return mid >= 2;
    }

    public static void main(String[] args) {
        System.out.println(Solution278.firstBadVersion(3));
    }
}

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(root.val);
        dfs(root, target - root.val, res, stack);
        return res;
    }

    private void dfs(TreeNode root, int target, List<List<Integer>> res, Deque<Integer> stack) {
        if (root == null) return;
        if (root.left == null && root.right == null && target == 0) {
            res.add(new ArrayList<>(stack));
            return;
        } else if ((root.left == null && root.right == null)) {
            return;
        }
        if (root.left != null && root.left.val <= target) {
            stack.addLast(root.left.val);
            dfs(root.left, target - root.left.val, res, stack);
            stack.removeLast();
        }
        if (root.right != null && root.right.val <= target) {
            stack.addLast(root.right.val);
            dfs(root.right, target - root.right.val, res, stack);
            stack.removeLast();
        }
    }

    public static void main(String[] args) {
        SolutionPdd solution = new SolutionPdd();
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode11 = new TreeNode(11);
        TreeNode treeNode13 = new TreeNode(13);
        TreeNode treeNode4_1 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode5_1 = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(1);
        treeNode5.left = treeNode4;
        treeNode5.right = treeNode8;
        treeNode4.left = treeNode11;
        treeNode8.left = treeNode13;
        treeNode8.right = treeNode4_1;
        treeNode11.left = treeNode7;
        treeNode11.right = treeNode2;
        treeNode4_1.left = treeNode5_1;
        treeNode4_1.right = treeNode1;
    }
}
// 5,4,8,11,null,13,4,7,2,null,null,5,1
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}