/**
 * @author LiYangSir
 * @date 2021/9/18
 */
public class Solution264 {
    public int nthUglyNumber(int n) {
        int p2 = 0, p3 = 0, p5 = 0;
        int[] dp = new int[n];
        // base case
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));
            if (min == dp[p2] * 2) {
                p2++;
            }
            if (min == dp[p3] * 3) {
                p3++;
            }
            if (min == dp[p5] * 5) {
                p5++;
            }
            dp[i] = min;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution264 solution264 = new Solution264();
        solution264.nthUglyNumber(10);
    }
}
