public class Solution977 {
    public int[] sortedSquares(int[] nums) {
        if (nums.length == 0) return new int[0];
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int i = nums.length - 1;
        while (left <= right) {
            int pLeft = nums[left] * nums[left];
            int pRight = nums[right] * nums[right];
            if (pLeft < pRight) {
                res[i--] = pRight;
                right--;
            } else {
                res[i--] = pLeft;
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution977 solution977 = new Solution977();
        solution977.sortedSquares(new int[]{-5,-3,-2,-1});
    }
}
