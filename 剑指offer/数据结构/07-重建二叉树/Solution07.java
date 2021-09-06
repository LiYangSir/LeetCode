package s07_重建二叉树;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiYangSir
 * @date 2021/6/14
 */
public class Solution07 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            cache.put(inorder[i], i);
        }
        int n = preorder.length;
        return dfs(cache, preorder, inorder, 0, n - 1, 0, n -1);
    }

    private TreeNode dfs(Map<Integer, Integer> cache, int[] preorder, int[] inorder, int pLeft, int pRight, int iLeft, int iRight) {
        if (pLeft > pRight) return null;
        int iRoot = cache.get(preorder[pLeft]);
        TreeNode root = new TreeNode(preorder[pLeft]);
        int size = iRoot - iLeft;
        root.left = dfs(cache, preorder, inorder, pLeft + 1, pLeft + size, iLeft, iRoot - 1);
        root.right = dfs(cache, preorder, inorder, pLeft + size + 1, pRight, iRoot + 1, iRight);
        return root;
    }
}
