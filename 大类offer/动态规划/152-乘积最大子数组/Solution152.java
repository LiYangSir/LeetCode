public class Solution152 {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] dpMax = new int[len];
        int[] dpMin = new int[len];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < len; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1]));
            dpMin[i] = Math.min(nums[i], Math.min(nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1]));
            result = Math.max(result, dpMax[i]);
        }
        return result;
    }
}