/**
 * @author LiYangSir
 * @date 2021/9/11
 */
public class Solution55 {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int maxPosition = 0;
        int end = 0;
        for (int i = 0; i < len - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                if (end == maxPosition) return false;
                end = maxPosition;
            }
        }
        return true;
    }
}
