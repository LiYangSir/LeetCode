import java.util.HashMap;
import java.util.Map;

public class Solution10 {
    private Map<Integer, Boolean> cache = new HashMap<>();
    public boolean isMatch(String s, String p) {
        return bp(s, 0, p, 0);
    }

    private boolean bp(String s, int l, String p, int r) {
        int key = l * 30 + r;
        if (cache.containsKey(key)) return cache.get(key);
        int m = s.length(), n = p.length();
        if (n == r) {
            return m == l;
        }
        // base case
        if (m == l) {
            if ((n - r) % 2 == 1) return false;
            for (; r < n - 1; r += 2) {
                if (p.charAt(r + 1) != '*') return false;
            }
            return true;
        }
        boolean res = false;
        if (s.charAt(l) == p.charAt(r) || p.charAt(r) == '.') {
            if (r + 1 < n && p.charAt(r + 1) == '*') {
                res = bp(s, l, p, r + 2) || bp(s, l + 1, p, r);  // 匹配零次，匹配1次
            } else {
                res = bp(s, l + 1, p, r + 1);
            }
        } else {
            if (r + 1 < n && p.charAt(r + 1) == '*') {
                res = bp(s, l, p, r + 2);
            } else {
                return false;
            }
        }
        cache.put(key, res);
        return res;
    }
}
