import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[len];

        dfs(nums, res, path, 0, used);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, Deque<Integer> path, int depth, boolean[] used) {
        int len = nums.length;
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if(used[i]){
               continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, res, path, depth + 1, used);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution46 solution46 = new Solution46();
        List<List<Integer>> permute = solution46.permute(new int[]{1, 2, 3, 4});
        System.out.println(permute);
    }


}