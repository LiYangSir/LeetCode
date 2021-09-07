import java.util.HashMap;
import java.util.Map;

public class Solution48 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cache.containsKey(c)) {
                left = Math.max(left, cache.get(c) + 1);
            }
            max = Math.max(max, i - left + 1);
            cache.put(c, i);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution48 solution48 = new Solution48();
        solution48.lengthOfLongestSubstring("abba");
    }
}
