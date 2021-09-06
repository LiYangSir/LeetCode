public class Solution91 {
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        // base case:
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            if (i > 1 && Integer.parseInt(s.substring(i - 2, i)) > 9 && Integer.parseInt(s.substring(i - 2, i)) < 27) {
                dp[i] += dp[i - 2];
            }
            if (s.charAt(i - 1) - '0' > 0) {
                dp[i] += dp[i - 1];
            }
        }
        return dp[len];
    }

    public int numDecodings2(String s) {
        int len = s.length();
        // base case:
        int pre = 1;
        int prePre = 0;
        int result = 0;
        for (int i = 1; i <= len; i++) {
            result = 0;
            if (i > 1 && Integer.parseInt(s.substring(i - 2, i)) > 9 && Integer.parseInt(s.substring(i - 2, i)) < 27) {
                result += prePre;
            }
            if (s.charAt(i - 1) - '0' > 0) {
                result += pre;
            }
            prePre = pre;
            pre = result;
        }
        return result;
    }
}
