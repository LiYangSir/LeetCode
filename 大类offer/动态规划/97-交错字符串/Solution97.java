/**
 * @author LiYangSir
 * @date 2021/9/10
 */
public class Solution97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        if (s1.length() == 0 && s2.length() == 0) return true;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        // base case
        for (int i = 1; i <= s2.length(); i++) {
            boolean flag = s2.charAt(i - 1) == s3.charAt(i - 1);
            if (flag) {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= s1.length(); i++) {
            boolean flag = s1.charAt(i - 1) == s3.charAt(i - 1);
            if (flag) {
                dp[i][0] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                        || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        Solution97 solution97 = new Solution97();
        System.out.println(solution97.isInterleave("", "", ""));
    }
}
