public class Solution53 {
    public int search(int[] nums, int target) {
        // 寻找左侧边界
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left == nums.length || nums[left] != target) {
            return 0;
        }
        int res = 0;
        while (left < nums.length && nums[left++] == target) {
            res++;
        }
        return res;
    }
}
