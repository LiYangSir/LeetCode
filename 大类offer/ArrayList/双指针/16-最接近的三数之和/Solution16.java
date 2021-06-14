import java.util.Arrays;

public class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int best = 10000;
        for (int i = 0; i < length - 2; i++) {
            int l = i + 1;
            int r = length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(target - sum) < Math.abs(target - best)) {
                    best = sum;
                }
                if (sum > target) {
                    while (l < r && nums[r] == nums[--r]);
                } else {
                    while (l < r && nums[l] == nums[++l]);
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        Solution16 solution16 = new Solution16();
        System.out.println(solution16.threeSumClosest(new int[]{-3,-2,-5,3,-4}, -1));
    }
}
