import java.util.*;

public class Solution59 {

}

class MaxQueue {
    private Deque<Integer> queue;
    private Deque<Integer> maxQueue;

    public MaxQueue() {
        queue = new ArrayDeque<>();
        maxQueue = new ArrayDeque<>();
    }

    public int max_value() {
        if (queue.isEmpty()) {
            return -1;
        }
        return maxQueue.peek();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!maxQueue.isEmpty() && maxQueue.peekLast() < value) {
            maxQueue.pollLast();
        }
        maxQueue.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int res = queue.poll();
        if (maxQueue.peek() == res) {
            maxQueue.poll();
        }
        return res;
    }
}
