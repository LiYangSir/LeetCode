import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
    private Deque<Integer> s1 = new ArrayDeque<>();
    private Deque<Integer> s2 = new ArrayDeque<>();

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        peek();
        return s2.pop();
    }

    public int peek() {
        if (s2.isEmpty()){
            while (!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
