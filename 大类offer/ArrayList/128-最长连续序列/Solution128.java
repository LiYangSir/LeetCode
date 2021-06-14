import java.util.HashSet;
import java.util.Set;

/**
 * 1. 首先进行去重
 * 2. 统计区域最小那个数的外伸大小，对于如果包含小一号，说明不是区域最小的
 * @author LiYangSir
 * @date 2021/6/6
 */
public class Solution128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }
        int result = 0;
        for (Integer num : numsSet) {
            if (!numsSet.contains(num - 1)) {
                int currentCount = 1;
                while (numsSet.contains(num + 1)) {
                    num++;
                    currentCount++;
                }
                result = Math.max(result, currentCount);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution128 solution128 = new Solution128();
        System.out.println(solution128.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }
}
