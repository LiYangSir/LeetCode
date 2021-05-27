import java.util.Random;

public class Solution398 {

}

class Solution1x {

    private int[] nums;

    public Solution1x(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        int n = nums.length;
        Random random = new Random();
        int res = 0, j = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                if (random.nextInt(++j) == 0) {
                    res = i;
                }
            }
        }
        return res;
    }
}
