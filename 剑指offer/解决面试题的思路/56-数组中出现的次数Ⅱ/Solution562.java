public class Solution562 {
    public int singleNumber(int[] nums) {
        int[] dp = new int[32];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 32; j++) {
                dp[j] += (nums[i] >> j) & 1;
            }
        }
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            res <<= 1;
            if (dp[i] % 3 == 1) {
                res = res | 1;
            }
        }
        return res;
    }
}
