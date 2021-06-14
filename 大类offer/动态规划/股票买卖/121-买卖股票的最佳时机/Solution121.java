public class Solution121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2]; // {天数}{持有/不持有}
        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] + dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);  //不允许再次购买
        }
        return dp[n - 1][0];
    }
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        // base case
        int dp_i_0 = 0, dp_i_1 = -prices[0];
        for (int i = 1; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0, prices[i] + dp_i_1);
            dp_i_1 = Math.max(dp_i_1, - prices[i]);  //不允许再次购买
        }
        return dp_i_0;
    }
    public static void main(String[] args) {

    }
}
