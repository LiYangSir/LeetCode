import java.util.Arrays;

public class Solution300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1)
            return 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {  // 更新DP
                if (nums[j] < nums[i])  // 注意这里是大于号
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
