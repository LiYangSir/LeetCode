import java.util.ArrayDeque;
import java.util.Deque;

public class Solution739 {
    public int[] dailyTemperatures(int[] T) { // 保存索引
        Deque<Integer> stack = new ArrayDeque<>();
        int n = T.length;
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty()? 0: stack.peek() - i;
            stack.push(i);
        }
        return res;
    }
}
