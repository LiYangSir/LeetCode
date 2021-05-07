import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums1.length, m = nums2.length;
        int[] res = new int[n];
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = m - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            hashMap.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }
        for (int i = 0; i < n; i++) {
            res[i] = hashMap.get(nums1[i]);
        }
        return res;
    }
}
