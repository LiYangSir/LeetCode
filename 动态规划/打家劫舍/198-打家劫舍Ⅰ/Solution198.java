public class Solution198 {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(nums[i] + dp[i + 2], dp[i + 1]); // 两种状态要么在这家偷，要么不在这家偷盗
        }
        return dp[0];
    }
    public int rob2(int[] nums) {
        int n = nums.length;
        int pre = 0, next = 0;
        int dpi = 0;
        for (int i = n - 1; i >= 0; i--) {
            dpi = Math.max(nums[i] + next, pre);
            next = pre;
            pre = dpi;
        }
        return dpi;
    }
}
