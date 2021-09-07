public class Solution63 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        int goin = prices[0];
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (prices[i] - goin < 0) {
                goin = prices[i];
            } else {
                max = Math.max(max, prices[i] - goin);
            }
        }
        return max;
    }
}
