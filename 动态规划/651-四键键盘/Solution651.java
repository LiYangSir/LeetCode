public class Solution651 {

    public int maxA(int n){
        int[] dp = new int[n + 1]; // 按下这次按键对应A的数量
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[n];
    }
}
