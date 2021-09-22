import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author LiYangSir
 * @date 2021/9/7
 */
public class Solution31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int push : pushed) {
            stack.push(push);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
