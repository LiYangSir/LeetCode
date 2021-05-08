import java.util.ArrayDeque;
import java.util.Deque;

class MyStack {
    private Deque<Integer> queue = new ArrayDeque<>();
    int top_element = 0;

    public void push(int x) {
        queue.offer(x);
        top_element = x;
    }

    public int pop() {
        int size = queue.size();
        while (size - 2 > 0) {
            queue.offer(queue.poll());
            size--;
        }
        top_element = queue.poll();
        queue.offer(top_element);
        return queue.poll();
    }

    public int top() {
        return top_element;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}