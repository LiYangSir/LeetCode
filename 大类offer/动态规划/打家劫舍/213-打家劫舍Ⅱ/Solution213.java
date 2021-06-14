public class Solution213 {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(subRob(nums, 0, n - 2), subRob(nums, 1, n - 1));
    }
    private int subRob(int[] nums, int start, int end){
        int n = nums.length;
        int pre = 0, next = 0;
        int dpi = 0;
        for (int i = end; i >= start; i--) {
            dpi = Math.max(nums[i] + next, pre);
            next = pre;
            pre = dpi;
        }
        return dpi;
    }
}
