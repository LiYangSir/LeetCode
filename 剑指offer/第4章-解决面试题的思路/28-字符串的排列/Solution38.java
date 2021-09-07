import java.util.*;

public class Solution38 {
    public String[] permutation(String s) {
        char[] charArray = s.toCharArray();
        int len = s.length();
        Arrays.sort(charArray);
        Deque<Character> stack = new ArrayDeque<>();
        boolean[] used = new boolean[len];
        List<String> res = new ArrayList<>();
        dfs(charArray, stack, used, res, 0);
        return res.toArray(new String[len]);
    }

    private void dfs(char[] charArray, Deque<Character> stack, boolean[] used, List<String> res, int index) {
        int length = charArray.length;
        if (stack.size() == length) {
            StringBuilder sb = new StringBuilder();
            stack.forEach(sb::append);
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < length; i++) {
            if (used[i] || (i > 0 && charArray[i] == charArray[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            stack.add(charArray[i]);
            dfs(charArray, stack, used, res, i);
            stack.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution38 solution38 = new Solution38();
        System.out.println(Arrays.toString(solution38.permutation("sus")));
    }
}
