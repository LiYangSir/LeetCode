public class Solution96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution96 solution96 = new Solution96();
        System.out.println(solution96.numTrees(3));
    }
}
