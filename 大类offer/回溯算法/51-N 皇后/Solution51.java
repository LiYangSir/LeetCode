import java.util.*;

public class Solution51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> positive = new HashSet<>();
        Set<Integer> used = new HashSet<>();
        Set<Integer> reverse = new HashSet<>();
        dfs(res, stack, n, used, positive, reverse);
        return res;
    }

    private void dfs(List<List<String>> res,  Deque<Integer> stack, int n, Set<Integer> used, Set<Integer> positive, Set<Integer> reverse) {
        if (stack.size() == n) {
            ArrayList<String> strings = new ArrayList<>();
            for (Integer integer : stack) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (integer == i) {
                        stringBuilder.append("Q");
                    } else {
                        stringBuilder.append(".");
                    }
                }
                strings.add(stringBuilder.toString());
            }
            res.add(strings);
        }
        for (int i = 0; i < n; i++) {
            int size = stack.size();
            if (used.contains(i) || positive.contains(i - size) || reverse.contains(i + size)) continue;
            stack.addLast(i);
            used.add(i);
            positive.add(i - size);
            reverse.add(i + size);
            dfs(res, stack, n, used, positive, reverse);
            stack.removeLast();
            used.remove(i);
            positive.remove(i - size);
            reverse.remove(i + size);
        }
    }

    public static void main(String[] args) {
        Solution51 solution51 = new Solution51();
        solution51.solveNQueens(5);

    }
}
