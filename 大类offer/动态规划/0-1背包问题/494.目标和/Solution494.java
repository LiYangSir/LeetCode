import java.lang.annotation.Target;

/**
 * @author LiYangSir
 * @date 2021/9/14
 */
public class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < Math.abs(target) || (target + sum) % 2 != 0) {
            return 0;
        }
        int zh = (target + sum) / 2;
        int[] dp = new int[zh + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = zh; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[zh];
    }

    public static void main(String[] args) {
        Solution494 solution494 = new Solution494();
        solution494.findTargetSumWays(new int[]{1}, 1);
    }
}
