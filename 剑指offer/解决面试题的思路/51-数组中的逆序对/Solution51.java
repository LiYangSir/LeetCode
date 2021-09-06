public class Solution51 {
    private int[] assist;
    public int reversePairs(int[] nums) {
        int len = nums.length;
        assist = new int[len];
        if (len < 2) {
            return 0;
        }
        return reversePairs(nums, 0, len - 1);
    }

    private int reversePairs(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return 0;
        }
        int mid = lo + (hi - lo) / 2;
        int leftCount = reversePairs(nums, lo, mid);
        int rightCount = reversePairs(nums, mid + 1, hi);
        int count = merge(nums, lo, mid, hi);
        return leftCount + rightCount + count;
    }

    private int merge(int[] nums, int lo, int mid, int hi) {
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;
        int count = 0;
        while (p1 <= mid && p2 <= hi) {
            if (nums[p1] <= nums[p2]) {
                assist[i++] = nums[p1++];
            } else {
                assist[i++] = nums[p2++];
                count += mid - p1 + 1;
            }
        }
        while (p1 <= mid) {
            assist[i++] = nums[p1++];
        }
        while (p2 <= hi) {
            assist[i++] = nums[p2++];
        }
        for (int j = lo; j <= hi; j++) {
            nums[j] = assist[j];
        }
        return count;
    }
}
