public class Solution123 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp_i_00 = 0, dp_i_01 = Integer.MIN_VALUE;
        int dp_i_10 = 0, dp_i_11 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp_i_10 = Math.max(dp_i_10, prices[i] + dp_i_11);
            dp_i_11 = Math.max(dp_i_11, dp_i_00 - prices[i]);
            dp_i_00 = Math.max(dp_i_00, prices[i] + dp_i_01);
            dp_i_01 = Math.max(dp_i_01, - prices[i]);
        }
        return dp_i_10;
    }
}
