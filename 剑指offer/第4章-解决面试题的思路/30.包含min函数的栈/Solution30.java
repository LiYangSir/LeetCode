import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author LiYangSir
 * @date 2021/9/7
 */
public class Solution30 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.min();
        minStack.pop();
        minStack.top();
        minStack.min();
    }
}

class MinStack {
    private Deque<Integer> queue = new ArrayDeque<>();
    private Deque<Integer> minQueue = new ArrayDeque<>();
    public MinStack() {

    }

    public void push(int x) {
        queue.push(x);
        if (minQueue.isEmpty() || minQueue.peek() >= x) {
            minQueue.push(x);
        }
    }

    public void pop() {
        int pop = queue.pop();
        if (!minQueue.isEmpty() && minQueue.peek() == pop) {
            minQueue.pop();
        }
    }

    public int top() {
        return queue.peek();
    }

    public int min() {
        return minQueue.peek();
    }
}
