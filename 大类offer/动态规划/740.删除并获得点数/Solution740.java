/**
 * @author LiYangSir
 * @date 2021/9/18
 */
public class Solution740 {
    public int deleteAndEarn(int[] nums) {
        int[] temps = new int[10001];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            temps[nums[i]] += nums[i];
        }

        int[] dp = new int[10001];
        dp[0] = 0;
        dp[1] = temps[1];
        for (int i = 2; i < 10001; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + temps[i]);
        }
        return dp[10000];
    }
}
