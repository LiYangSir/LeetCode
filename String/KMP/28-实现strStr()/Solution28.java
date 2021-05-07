public class Solution28 {
    private String pat;
    private int[][] dp;

    public void init(String pat) {   // 类似于动态规划
        this.pat = pat;
        int length = pat.length();
        this.dp = new int[length][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 构建数组
        int X = 0;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < 256; j++)
                dp[i][j] = dp[X][j];
            dp[i][pat.charAt(i)] = i + 1;
            X = dp[X][pat.charAt(i)];
        }
    }
    public int search(String txt){
        int M = pat.length();
        int N = txt.length();
        int j = 0;
        for (int i = 0; i < N; i++) {
            j = dp[j][txt.charAt(i)];
            if (j == M) return i - M + 1;
        }
        return -1;
    }
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) return -1;
        if (needle.equals("")) return 0;
        init(needle);
        return search(haystack);
    }
}
