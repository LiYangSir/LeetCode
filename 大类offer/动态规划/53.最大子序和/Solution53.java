/**
 * @author LiYangSir
 * @date 2021/9/11
 */
public class Solution53 {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int max = nums[0];
        // base case
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution53 solution53 = new Solution53();
        System.out.println(solution53.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
