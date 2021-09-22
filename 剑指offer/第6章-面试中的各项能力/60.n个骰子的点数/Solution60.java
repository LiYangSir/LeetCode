/**
 * @author LiYangSir
 * @date 2021/9/9
 */
public class Solution60 {
    public double[] dicesProbability(int n) {
        int[][] dp = new int[15][70];
        // base case
        for (int i = 1; i < 6; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i; j < 6 * n; j++) {
                for (int cur = 1; cur <= 6; cur++) {
                    if (j <= cur) break;
                    dp[i][j] = dp[i - 1][j - cur];
                }
            }
        }
        double[] result = new double[5 * n + 1];
        for (int i = n; i < 6 * n; i++) {
            result[i - n] = dp[n][i] * 1.0 /  Math.pow(6, n);
        }
        return result;
    }
}
