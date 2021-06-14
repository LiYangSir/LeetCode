public class Solution31 {
    /**
     * 核心是从后往前找到一个较小的数，也就是突然递减的数
     * 根据这个较小的数，找到一个比它大的数进行替换，保证后面的一个有序性
     * 并反转较小数后面的数，保证数据的递增
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i] <= nums[i - 1]){
            i--;
        }
        i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }
        for (int m = i + 1; m < i + 1+ (nums.length - i - 1) / 2; m++) {
            swap(nums, m, nums.length - m + i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution31 solution31 = new Solution31();
        solution31.nextPermutation(new int[]{1, 2, 3});
    }
}
