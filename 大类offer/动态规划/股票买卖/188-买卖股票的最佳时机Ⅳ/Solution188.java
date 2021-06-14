public class Solution188 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 1) return 0;
        if (k > n / 2) {
            return maxProfit_inf(prices);
        }
        int[][][] dp = new int[n][k + 1][2]; // {天数}{持有/不持有}
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0){
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], prices[i] + dp[i - 1][j][1]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0]- prices[i]);  //只需修改dp[i - 1][0]- prices[i]
            }
        }
        return dp[n - 1][k][0];
    }

    private int maxProfit_inf(int[] prices) {
        int n = prices.length;
        // base case
        int dp_i_0 = 0, dp_i_1 = -prices[0];
        for (int i = 1; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, prices[i] + dp_i_1);
            dp_i_1 = Math.max(dp_i_1, temp- prices[i]);
        }
        return dp_i_0;
    }
}
