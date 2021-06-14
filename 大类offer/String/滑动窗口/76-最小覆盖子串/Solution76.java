import java.util.HashMap;
import java.util.Map;

public class Solution76 {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        for (char c : t.toCharArray()) {
            needs.put(c, needs.computeIfAbsent(c, k -> 0) + 1);
        }
        int left = 0;
        int match = 0;
        int minLen = s.length() + 1;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            windows.put(c, windows.computeIfAbsent(c, k -> 0) + 1);
            if(windows.get(c).equals(needs.get(c)))
                match++;
            while (match == needs.size()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    start = left;
                }
                char c1 = s.charAt(left);
                windows.put(c1, windows.get(c1) - 1);
                if (needs.containsKey(c1) && windows.get(c1) < needs.get(c1)) {
                    match--;
                }
                left++;
            }
        }
        return minLen > s.length() ? "" : s.substring(start, start + minLen);
    }
    public static void main(String[] args) {
        Solution76 solution76 = new Solution76();
        System.out.println(solution76.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
    }
}
