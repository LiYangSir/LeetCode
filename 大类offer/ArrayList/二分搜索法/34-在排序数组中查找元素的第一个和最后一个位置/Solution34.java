public class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                int l = mid;
                int r = mid;
                while (l - 1 >= 0 && nums[l - 1] == nums[mid]){l--;};
                while (r + 1 < nums.length && nums[r + 1] == nums[mid]){r++;};
                return new int[]{l, r};
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Solution34 solution34 = new Solution34();
        solution34.searchRange(new int[]{5, 7 , 7, 8, 8, 10}, 8);
    }
}
