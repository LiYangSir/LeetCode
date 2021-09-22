/**
 * @author LiYangSir
 * @date 2021/9/10
 */
public class Solution416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 0x01) == 1) {
            return false;
        }
        int target = sum >> 1;
        int[] dp = new int[target + 1];
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = Math.max(dp[i], dp[i - num] + num);
            }
        }
        return dp[target] == target;
    }

    public static void main(String[] args) {
        Solution416 solution416 = new Solution416();
        solution416.canPartition(new int[]{1, 5, 11, 5});
    }
}
