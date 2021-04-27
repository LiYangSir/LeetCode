public class Solution11 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return isSubStructure2(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val == B.val) {
            return isSubStructure2(A.left, B.left) && isSubStructure2(A.right, B.right);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(1);
        B.left = new TreeNode(-4);
        TreeNode Al = new TreeNode(0);
        A.left = Al;
        A.right = new TreeNode(1);
        Al.left = new TreeNode(-4);
        Al.right = new TreeNode(-3);
        System.out.println(new Solution11().isSubStructure(A, B));
    }
}
