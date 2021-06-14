import java.util.HashMap;
import java.util.Map;

public class Solution560 {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        int res = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (cache.containsKey(sum - k)) {
                res += cache.get(sum - k);
            }
            cache.put(sum, cache.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (k == pre[j] - pre[i]) res++;
            }
        }
        return res;
    }
}
