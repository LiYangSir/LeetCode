import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] used = new boolean[n];
        dfs(res, stack, n, used);
        return res;
    }

    private void dfs(List<List<String>> res,  Deque<Integer> stack, int n, boolean[] used) {
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
            if (used[i] || (stack.size() != 0 && (stack.getLast() == i + 1 || stack.getLast() == i - 1))) continue;
            stack.addLast(i);
            used[i] = true;
            dfs(res, stack, n, used);
            stack.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution51 solution51 = new Solution51();
        solution51.solveNQueens(5);

    }
}
