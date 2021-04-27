import java.util.*;

class Solution47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[len];
        Arrays.sort(nums);
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
            if(used[i] || ( i > 0 && nums[i] == nums[i - 1] && !used[i - 1])){
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
        Solution47 solution46 = new Solution47();
        List<List<Integer>> permute = solution46.permuteUnique(new int[]{3, 3, 5, 3});
        System.out.println(permute);
    }


}