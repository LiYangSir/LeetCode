public class Solution283 {
    public void moveZeroes(int[] nums) {
        int left = 0;
        while (left < nums.length && nums[left] != 0) {
            ++left;
        }
        int right = left + 1;
        while (right < nums.length && nums[right] == 0){
            ++right;
        }
        if (left >= nums.length || right >= nums.length) {
            return;
        }
        while (right < nums.length) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            while (right < nums.length && nums[right] == 0){
                ++right;
            }
        }
    }

    public static void main(String[] args) {
        Solution283 solution283 = new Solution283();
        solution283.moveZeroes(new int[]{0, 1, 0, 3, 12});

    }
}
