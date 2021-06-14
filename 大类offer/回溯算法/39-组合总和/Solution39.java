import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, target, res, path, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> res, Deque<Integer> path, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        } else if (target < 0) {
            return;
        }
        int length = candidates.length;
        for (int i = index; i < length; i++) {
            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], res, path, i);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution39 solution39 = new Solution39();
        List<List<Integer>> lists = solution39.combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(lists);
    }

}