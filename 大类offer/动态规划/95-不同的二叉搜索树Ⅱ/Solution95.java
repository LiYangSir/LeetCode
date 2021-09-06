import cn.quguai.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        ArrayList<TreeNode> allTrees = new ArrayList<>();
        if (start > end) {
            allTrees.add(null); // 很关键！！
            return allTrees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftNodes = generateTrees(start, i - 1);
            List<TreeNode> rightNodes = generateTrees(i + 1, end);
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    allTrees.add(root);
                }
            }
        }
        return allTrees;
    }

    public static void main(String[] args) {
        Solution95 solution95 = new Solution95();
        solution95.generateTrees(3);
    }
}
