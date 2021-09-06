import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution39 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        Set<String> word = new HashSet<>(wordDict);
        boolean[] dp = new boolean[len + 1];
        // base case
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && word.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        Solution39 solution39 = new Solution39();
        solution39.wordBreak(
                "leetcode", Arrays.asList("leet", "code"));
    }
}