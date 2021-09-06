

public class Solution47 {
    public int maxValue(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // base case
        for (int i = 0; i < m; i++) {
            dp[i][0] = (i > 0 ? dp[i - 1][0] : 0) + grid[i][0];
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = (i > 0 ? dp[0][i - 1] : 0) + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution47 solution47 = new Solution47();
        solution47.maxValue(new int[][]{{1, 2, 5}, {3, 2, 1}});
    }
}
