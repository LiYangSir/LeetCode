import java.util.ArrayList;
import java.util.List;

public class Solution448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) {
            nums[(num - 1) % n] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n)
                res.add(i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution448 solution448 = new Solution448();
        System.out.println(solution448.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
