public class Solution714 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = -prices[0] - fee;  // 第一天持有股票初始值
        for (int i = 1; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, prices[i] + dp_i_1);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }
    public int maxProfit2(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;  // 第零天持有股票
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, prices[i] + dp_i_1);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }
}
