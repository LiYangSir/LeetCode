import java.util.Arrays;

/**
 * @author LiYangSir
 * @date 2021/9/11
 */
public class Solution45 {
    public int jump(int[] nums) {
        int len = nums.length;
        int step = 0;
        int maxPosition = 0;
        int end = 0;
        for (int i = 0; i < len - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (end == i) {
                end = maxPosition;
                step++;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        Solution45 solution45 = new Solution45();
        System.out.println(solution45.jump(new int[]{2, 3, 1, 1, 4}));
    }
}
