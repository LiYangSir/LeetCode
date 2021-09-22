import java.util.HashSet;
import java.util.Set;

/**
 * @author LiYangSir
 * @date 2021/6/14
 */
public class Solution03 {
    /* 使用HashSet进行存储 */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> cache = new HashSet<>();
        for (int num : nums) {
            if (!cache.add(num)) {
                return num;
            }
        }
        return -1;
    }
    /* 原地置换 ： 最优解 */
    // 原则对应位置对应坑 https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/yuan-di-zhi-huan-shi-jian-kong-jian-100-by-derrick/
    public int findRepeatNumber2(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] != i) {
                int temp = nums[i];
                if (nums[temp] == temp) return temp;
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}
