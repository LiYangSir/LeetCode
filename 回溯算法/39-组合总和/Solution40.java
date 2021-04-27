import java.util.*;

public class Solution40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[candidates.length];
        Arrays.sort(candidates);
        dfs(candidates, target, used, res, path, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, boolean[] used, List<List<Integer>> res, Deque<Integer> path, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        } else if (target < 0) {
            return;
        }
        int length = candidates.length;
        for (int i = index; i < length; i++) {
            if (used[i] || (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], used, res, path, i);
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution40 solution40 = new Solution40();
        System.out.println(solution40.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }
}
