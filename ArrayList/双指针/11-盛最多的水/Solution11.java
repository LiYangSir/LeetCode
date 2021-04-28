public class Solution11 {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (right > left) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, area);
            int a = height[left] > height[right] ? right-- : left++;
        }
        return max;
    }
    public static void main(String[] args) {
        Solution11 solution11 = new Solution11();
        System.out.println(solution11.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
