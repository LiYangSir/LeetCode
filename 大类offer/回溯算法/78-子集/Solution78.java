import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        dfs(nums, res, stack, 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, Deque<Integer> stack, int start) {
        res.add(new ArrayList<>(stack));
        for (int i = start; i < nums.length; i++) {
            stack.addLast(nums[i]);
            dfs(nums, res, stack, i + 1);
            stack.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution78 solution78 = new Solution78();
        System.out.println(solution78.subsets(new int[]{1, 2, 3}));
    }
}
