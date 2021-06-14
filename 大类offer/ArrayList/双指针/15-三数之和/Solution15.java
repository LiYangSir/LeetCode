import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < length - 2; i++) {
            int l = i + 1;
            int r = length - 1;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    while (l < r && nums[r] == nums[--r]);
                    while (l < r && nums[l] == nums[++l]);
                } else if (sum > 0) {
                    while (l < r && nums[r] == nums[--r]);
                } else {
                    while (l < r && nums[l] == nums[++l]);
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
        System.out.println(solution15.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
