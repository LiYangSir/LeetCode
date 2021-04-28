## 回溯算法
总结
对于节点的不重用，引入used数组，并进行回溯的变换。
对于节点包含重复的节点， 首先进行节点的排序，并增加判断条件

### 39. 组合总数
给定一个无重复元素的数组candidates和一个目标数`target`，找出candidates中所有可以使数字和为 'target' 的组合。
candidates中的数字可以无限制重复被选取。

说明：
所有数字（包括target）都是正整数。
解集不能包含重复的组合。

示例1：

> 输入：candidates = [2,3,6,7], target = 7,
> 所求解集为：
> [[7],[2,2,3]]

示例2：

> 输入：candidates = [2,3,5], target = 8,
> 所求解集为：
> [ [2,2,2,2],
>  [2,3,3],
>  [3,5]]

```java
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
        for (int i = index; i < length; i++) {   // 重用的写法
            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], res, path, i);
            path.removeLast();
        }
    }
}
```

### 40. 组合总数Ⅱ
给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。

candidates中的每个数字在每个组合中只能使用一次。

说明：
所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。

示例1:
>输入: candidates =[10,1,2,7,6,1,5], target =8,
>所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

示例2:
>输入: candidates =[2,5,2,1,2], target =5,
>所求解集为:
[
 [1,2,2],
 [5]
]

```java
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
                continue;  // 不重用并且重复
            }
            used[i] = true;
            path.addLast(candidates[i]);
            dfs(candidates, target - candidates[i], used, res, path, i);
            used[i] = false;
            path.removeLast();
        }
    }
}

```

### 46. 全排列

给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

> 输入: [1,2,3]
> 输出:
> [ [1,2,3],
>   [1,3,2],
>   [2,1,3],
>   [2,3,1],
>   [3,1,2],
>   [3,2,1]]

```java
class Solution {
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
            if(used[i]){  // 不重用的写法
               continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, res, path, depth + 1, used);
            path.removeLast();
            used[i] = false;
        }
    }
}
```

### 47 全排列 II
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

示例 1：

>输入：nums = [1,1,2]
>输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]

示例 2：
>输入：nums = [1,2,3]
>输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

```java
class Solution47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[len];
        Arrays.sort(nums);   // 先进行排序
        dfs(nums, res, path, 0, used);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, Deque<Integer> path, int depth, boolean[] used) {
        int len = nums.length;
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {  // 不重用且重复
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
}
```