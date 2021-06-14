import java.util.HashMap;
import java.util.Map;

public class Solution03 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cache.containsKey(c)) {
                left = Math.max(left, cache.get(c) + 1);
            }
            cache.put(c, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cache.put(c, cache.computeIfAbsent(c, k -> 0) + 1);
            while (cache.get(c) > 1) {
                char c2 = s.charAt(left);
                cache.put(c2, cache.get(c2) - 1);
                left++;
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution03 solution03 = new Solution03();
        System.out.println(solution03.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution03.lengthOfLongestSubstring2("abcabcbb"));
    }
}
