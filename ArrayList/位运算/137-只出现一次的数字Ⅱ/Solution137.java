import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution137 {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.computeIfAbsent(nums[i], k -> 0) + 1);
        }
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == 1)
                return map.get(integer);
        }
        return -1;
    }
}
