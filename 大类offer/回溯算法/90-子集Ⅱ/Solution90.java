import java.util.*;

/**
 * @author LiYangSir
 * @date 2021/6/10
 */
public class Solution90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        dfs(nums, res, deque, used, 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, Deque<Integer> deque, boolean[] used, int index) {
        res.add(new ArrayList<>(deque));
        for (int i = index; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            deque.addLast(nums[i]);
            dfs(nums, res, deque, used, i + 1);
            used[i] = false;
            deque.removeLast();
        }
    }
    public static void main(String[] args) {
        Solution90 solution90 = new Solution90();
        System.out.println(solution90.subsetsWithDup(new int[]{1, 1, 1, 2}));

    }
}
