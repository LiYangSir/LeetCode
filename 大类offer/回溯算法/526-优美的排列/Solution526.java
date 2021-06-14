import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author LiYangSir
 * @date 2021/6/11
 */
public class Solution526 {
    private int count = 0;
    public int countArrangement(int n) {
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] used = new boolean[n];
        dfs(stack, used, n, 1);
        return count;
    }

    private void dfs(Deque<Integer> stack, boolean[] used, int n, int index) {
        if (stack.size() == n) count++;
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            if ((i + 1) >= index && (i + 1) % index == 0 || (i + 1) < index && index % (i + 1) == 0) {
                used[i] = true;
                stack.addLast(i + 1);
                dfs(stack, used, n, index + 1);
                stack.removeLast();
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution526 solution526 = new Solution526();
        System.out.println(solution526.countArrangement(2));
    }
}
