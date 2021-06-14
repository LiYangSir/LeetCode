import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author LiYangSir
 * @date 2021/6/11
 */
public class Solution216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        dfs(res, stack, k, n, 0, 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> stack, int k, int n, int sum, int index) {
        if (sum == n && stack.size() == k) {
            res.add(new ArrayList<>(stack));
            return;
        } else if (sum > n) {
            return;
        }
        for (int i = index; i < 10; i++) {
            stack.addLast(i);
            dfs(res, stack, k, n, sum + i, i + 1);
            stack.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution216 solution216 = new Solution216();
        System.out.println(solution216.combinationSum3(3, 9));
    }
}
