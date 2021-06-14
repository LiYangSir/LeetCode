import java.util.ArrayDeque;
import java.util.Deque;

public class Solution239 {
    class MonitorQueue {
        private Deque<Integer> stack = new ArrayDeque<>();

        public void pop(int key) {
            if (!stack.isEmpty() && stack.getFirst() == key) {
                stack.removeFirst();
            }
        }

        public int max() {
            return stack.getFirst();
        }

        public void push(int key) {
            while (!stack.isEmpty() && stack.getLast() < key) {  //  必须是小于号
                stack.removeLast();
            }
            stack.addLast(key);
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonitorQueue monitorQueue = new MonitorQueue();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            if (i < k - 1) {
                monitorQueue.push(nums[i]);
            } else {
                monitorQueue.push(nums[i]);
                res[i - k + 1] = monitorQueue.max();
                monitorQueue.pop(nums[i - k + 1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution239 solution239 = new Solution239();
        solution239.maxSlidingWindow(new int[]{-7, -8, 7, 5, 7, 1, 6, 0}, 4);
    }
}
