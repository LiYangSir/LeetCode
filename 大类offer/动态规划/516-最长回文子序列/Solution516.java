public class Solution516 {
    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];

        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }
    public static void main(String[] args) {
        Solution516 solution516 = new Solution516();
        System.out.println(solution516.longestPalindromeSubseq("bbbab"));
    }
}
