import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution3 {
    // 滑动窗口解决最长字串问题
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (cache.containsKey(s.charAt(i))) {
                left = Math.max(left, cache.get(s.charAt(i)) + 1);
            }
            cache.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
