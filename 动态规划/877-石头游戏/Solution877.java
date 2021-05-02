public class Solution877 {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];  // dp数组保存着先手减去后手之间的差值
        // base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
            dp[i][i] = 0;
        }
        // 斜着进行遍历
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程：该点（先手-后手） = 石头 - （后手 - 先手）= （先手 + 石头 - 后手）最大化
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[i] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }

    public static void main(String[] args) {
        Solution877 solution877 = new Solution877();
        System.out.println(solution877.stoneGame(new int[]{3, 2, 10, 4}));
    }
}
