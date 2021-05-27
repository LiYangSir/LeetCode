import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution645 {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            nums[(num - 1) % n] += n;
        }
        List<Integer> dup = new ArrayList<>();
        List<Integer> re = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n){
                re.add(i + 1);
            } else if (nums[i] > 2 * n) {
                dup.add(i + 1);
            }
        }
        int[] res = new int[dup.size() + re.size()];
        for (int i = 0; i < dup.size(); i++) {
            res[i] = dup.get(i);
        }
        for (int i = 0; i < re.size(); i++) {
            res[i + dup.size()] = re.get(i);
        }
        return res;
    }

    public int[] findErrorNums2(int[] nums) {
        int n = nums.length;
        List<Integer> dup = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                dup.add(Math.abs(nums[i]));
            } else {
                nums[index] *= -1;
            }
        }
        List<Integer> re = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0)
                re.add(i + 1);
        }
        int[] res = new int[dup.size() + re.size()];
        for (int i = 0; i < dup.size(); i++) {
            res[i] = dup.get(i);
        }
        for (int i = 0; i < re.size(); i++) {
            res[i + dup.size()] = re.get(i);
        }
        return res;
    }
    public static void main(String[] args) {
        Solution645 solution645 = new Solution645();
        System.out.println(Arrays.toString(solution645.findErrorNums2(new int[]{2, 2})));
    }
}
