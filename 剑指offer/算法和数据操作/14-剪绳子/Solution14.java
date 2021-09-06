/**
 * @author LiYangSir
 * @date 2021/6/15
 */
public class Solution14 {
    public int cuttingRope(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 0; dp[1] = 1; dp[2] = 2; dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }

    public int cuttingRope2(int n) {
        if (n < 3) return 1;
        if (n == 3) return 2;
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        return res * n;
    }
}
