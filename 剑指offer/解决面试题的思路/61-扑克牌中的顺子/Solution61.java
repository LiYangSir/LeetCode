import java.util.Arrays;

public class Solution61 {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int count0 = 0;
        while (nums[count0] == 0) {
            count0++;
        }
        for (int i = count0 + 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff == 0) return false;
            if (diff != 1) {
                count0 -= diff - 1;
                if (count0 < 0) return false;
            }
        }
        return true;
    }
}
