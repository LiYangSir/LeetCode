/**
 * @author LiYangSir
 * @date 2021/9/10
 */
public class Solution413 {
    public int numberOfArithmeticSlices(int[] nums) {
        int[] dp = new int[nums.length];
        int sum = 0;
        if (nums.length < 3) return 0;

        for (int i = 2; i < nums.length; i++) {
            if (2 * nums[i - 1] == nums[i] + nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                sum += dp[i];
            }
        }
        return sum;
    }

    public int numberOfArithmeticSlices2(int[] nums) {
        int sum = 0;
        int pre = 0;
        if (nums.length < 3) return 0;
        for (int i = 2; i < nums.length; i++) {
            if (2 * nums[i - 1] == nums[i] + nums[i - 2]) {
                pre = pre + 1;
                sum += pre;
            } else {
                pre = 0;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution413 solution413 = new Solution413();
        System.out.println(solution413.numberOfArithmeticSlices2(new int[]{1, 2, 3, 4, 6 ,8}));
    }
}
