public class Solution221 {
    /**
     * 最大正方形
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 代表i,j 的最大边长
        int[][] dp = new int[m + 1][n + 1];
        int result = 0;
        // base case:无需处理
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result * result; // 注意：最后平方
    }
}
