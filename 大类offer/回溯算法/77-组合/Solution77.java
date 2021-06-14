import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author LiYangSir
 * @date 2021/6/10
 */
public class Solution77 {
    public List<List<Integer>> combine(int n, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k, stack, res, 0);
        return res;
    }

    private void dfs(int n, int k, Deque<Integer> stack, List<List<Integer>> res, int index) {
        if (stack.size() == k) {
            res.add(new ArrayList<>(stack));
        }
        for (int i = index; i < n; i++) {
            stack.addLast(i + 1);
            dfs(n, k, stack, res, i + 1);
            stack.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution77 solution77 = new Solution77();
        System.out.println(solution77.combine(4, 2));
    }
}
