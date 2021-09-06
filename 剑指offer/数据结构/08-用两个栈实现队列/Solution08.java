package s08_用两个栈实现队列;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author LiYangSir
 * @date 2021/6/14
 */
public class Solution08 {

}

class CQueue {

    private Deque<Integer> left;
    private Deque<Integer> right;

    public CQueue() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        right.push(value);
    }

    public int deleteHead() {
        if (left.isEmpty()) {
            while (!right.isEmpty()) {
                left.push(right.pop());
            }
        }
        return left.isEmpty()? -1: left.pop();
    }
}
