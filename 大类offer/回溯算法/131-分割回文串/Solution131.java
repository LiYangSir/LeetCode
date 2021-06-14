import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author LiYangSir
 * @date 2021/6/10
 */
public class Solution131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        dfs(res, stack, s, 0);
        return res;
    }

    private void dfs(List<List<String>> res, Deque<String> stack, String s, int index) {
        if (index == s.length()) {
            res.add(new ArrayList<>(stack));
            return;
        }
        int len = s.length();
        for (int i = index + 1; i <= len; i++) {
            String substring = s.substring(index, i);
            if (isReturn(substring)) {
                stack.addLast(substring);
                dfs(res, stack, s, i);
                stack.removeLast();
            }
        }
    }

    private boolean isReturn(String substring) {
        int left = 0, right = substring.length() - 1;
        while (left <= right) {
            if (substring.charAt(left++) != substring.charAt(right--)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution131 solution131 = new Solution131();
        System.out.println(solution131.partition("aab"));
    }
}
