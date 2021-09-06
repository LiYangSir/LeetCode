## 回溯算法
总结
对于节点的不重用，引入used数组，并进行回溯的变换。
对于节点包含重复的节点， 首先进行节点的排序，并增加判断条件

### 22. 括号生成

数字 `n` 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

**示例 1：**

```
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
```

**示例 2：**

```
输入：n = 1
输出：["()"]
```

```java
/**
 * 将括号看成["(", ")"]
 */
public class Solution22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        dfs(res, builder,0, 0, n);
        return res;
    }

    private void dfs(List<String> res, StringBuilder builder, int open, int close, int max) {
        if (open + close == max * 2) {
            res.add(builder.toString());
        }
        if (open < max) {
            builder.append("(");
            dfs(res, builder, open + 1, close, max);
            builder.delete(builder.length() - 1, builder.length());
        }
        if (close < open) {
            builder.append(")");
            dfs(res, builder, open, close + 1, max);
            builder.delete(builder.length() - 1, builder.length());
        }
    }
}

```

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
            dfs(candidates, target - candidates[i], res, path, i);  // 不同于子集，是从当前位置开始: index = i
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

### 51. N皇后

```java
import java.util.*;

public class Solution51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> positive = new HashSet<>();
        Set<Integer> used = new HashSet<>();
        Set<Integer> reverse = new HashSet<>();
        dfs(res, stack, n, used, positive, reverse);
        return res;
    }

    private void dfs(List<List<String>> res,  Deque<Integer> stack, int n, Set<Integer> used, Set<Integer> positive, Set<Integer> reverse) {
        if (stack.size() == n) {
            ArrayList<String> strings = new ArrayList<>();
            for (Integer integer : stack) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (integer == i) {
                        stringBuilder.append("Q");
                    } else {
                        stringBuilder.append(".");
                    }
                }
                strings.add(stringBuilder.toString());
            }
            res.add(strings);
        }
        for (int i = 0; i < n; i++) {
            int size = stack.size();
            if (used.contains(i) || positive.contains(i - size) || reverse.contains(i + size)) continue;
            stack.addLast(i);
            used.add(i);
            positive.add(i - size);
            reverse.add(i + size);
            dfs(res, stack, n, used, positive, reverse);
            stack.removeLast();
            used.remove(i);
            positive.remove(i - size);
            reverse.remove(i + size);
        }
    }
}
```

### 77. 组合

```java
public class Solution77 {
    public List<List<Integer>> combine(int n, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k, stack, res, 0);
        return res;
    }

    private void dfs(int n, int k, Deque<Integer> stack, List<List<Integer>> res, int index) {
        if (stack.size() == k) {
            res.add(new ArrayList<>(stack));
        }
        for (int i = index; i < n; i++) {
            stack.addLast(i + 1);
            dfs(n, k, stack, res, i + 1);
            stack.removeLast();
        }
    }
}
```

### 78. 子集

给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

示例 1：

> 输入：nums = [1,2,3]
> 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

示例 2：

> 输入：nums = [0]
> 输出：[[],[0]]

```java
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
}
```

### 90-子集Ⅱ

给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

示例 1：

> 输入：nums = [1,2,2]
> 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]

示例 2：

> 输入：nums = [0]
> 输出：[[],[0]]

```java
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
}
```

### 79-单词搜索

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 

示例 1：

![img](https://markdown-liyang.oss-cn-beijing.aliyuncs.com/blog/elasticsearch/word2.jpg)

> 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
> 输出：true

```java
public class Solution79 {

    public boolean exist(char[][] board, String word) {
        int h = board.length;
        int w = board[0].length;
        boolean[][] used = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (dfs(board, word, used, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[][] used, int index, int i, int j) {
        if (board[i][j] != word.charAt(index)) return false;
        else if (index + 1 == word.length()) return true;

        int m = board.length;
        int n = board[0].length;
        used[i][j] = true;
        boolean flag = false;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] direction : directions) {
            int newi = i + direction[0];
            int newj = j + direction[1];
            if (newi >= 0 && newi < m && newj >= 0 && newj < n && !used[newi][newj]) {
                if (dfs(board, word, used, index + 1, newi, newj)) {
                    flag = true;
                    break;
                }
            }
        }
        used[i][j] = false;
        return flag;
    }
}

```

### 93-复原IP地址

给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。

有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。

示例 1：

> 输入：s = "25525511135"
> 输出：["255.255.11.135","255.255.111.35"]

```java
public class Solution93 {
    public List<String> restoreIpAddresses(String s) {
        Deque<String> stack = new ArrayDeque<>();
        List<String> res = new ArrayList<>();

        dfs(s, res, stack, 0);
        return res;
    }

    private void dfs(String s, List<String> res, Deque<String> stack, int index) {
        if (stack.size() == 4) {
            if (index < s.length()) return;
            // 拼接stack
            List<String> list = new ArrayList<>(stack);
            StringBuilder sb = new StringBuilder();
            for (String l : list) {
                sb.append(l + '.');
            }
            sb.delete(sb.length() - 1, sb.length());
            res.add(sb.toString());
            return;
        }
        List<Integer> list = listActive(s, index);
        if (list.size() == 0) return;
        for (int i = 0; i < list.size(); i++) {
            stack.addLast(s.substring(index, list.get(i)));
            dfs(s, res, stack, list.get(i));
            stack.removeLast();
        }
    }

    private List<Integer> listActive(String s, int index) {
        List<Integer> res = new ArrayList<>();
        int len = s.length();
        if (index == len) return res;
        char c = s.charAt(index);
        if (c == '0') {
            res.add(index + 1);
        } else if (c == '1') {
            res.add(index + 1);
            if (index + 2 <= len) res.add(index + 2);
            if (index + 3 <= len) res.add(index + 3);
        } else if (c == '2') {
            res.add(index + 1);
            if (index + 2 <= len) res.add(index + 2);
            if (index + 3 <= len && Integer.parseInt(s.substring(index, index + 3)) < 256) res.add(index + 3);
        } else {
            res.add(index + 1);
            if (index + 2 <= len) res.add(index + 2);
        }
        return res;
    }
}
```

### 126-单词接龙Ⅱ

按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：

每对相邻的单词之间仅有单个字母不同。
转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
sk == endWord
给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。

示例 1：

> 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
> 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
> 解释：存在 2 种最短的转换序列：
> "hit" -> "hot" -> "dot" -> "dog" -> "cog"
> "hit" -> "hot" -> "lot" -> "log" -> "cog"

```java
public class Solution126 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        // 因为需要快速判断扩展出的单词是否在 wordList 里，因此需要将 wordList 存入哈希表，这里命名为「字典」
        Set<String> dict = new HashSet<>(wordList);
        // 特殊用例判断
        if (!dict.contains(endWord)) {
            return res;
        }

        dict.remove(beginWord);

        // 第 1 步：广度优先遍历建图
        // 记录扩展出的单词是在第几次扩展的时候得到的，key：单词，value：在广度优先遍历的第几层
        Map<String, Integer> steps = new HashMap<>();
        steps.put(beginWord, 0);
        // 记录了单词是从哪些单词扩展而来，key：单词，value：单词列表，这些单词可以变换到 key ，它们是一对多关系
        Map<String, List<String>> from = new HashMap<>();
        int step = 1;
        boolean found = false;
        int wordLen = beginWord.length();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] charArray = currWord.toCharArray();
                // 将每一位替换成 26 个小写英文字母
                for (int j = 0; j < wordLen; j++) {
                    char origin = charArray[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        charArray[j] = c;
                        String nextWord = String.valueOf(charArray);
                        if (steps.containsKey(nextWord) && step == steps.get(nextWord)) {
                            from.get(nextWord).add(currWord);
                        }
                        if (!dict.contains(nextWord)) {
                            continue;
                        }
                        // 如果从一个单词扩展出来的单词以前遍历过，距离一定更远，为了避免搜索到已经遍历到，且距离更远的单词，需要将它从 dict 中删除
                        dict.remove(nextWord);
                        // 这一层扩展出的单词进入队列
                        queue.offer(nextWord);

                        // 记录 nextWord 从 currWord 而来
                        from.putIfAbsent(nextWord, new ArrayList<>());
                        from.get(nextWord).add(currWord);
                        // 记录 nextWord 的 step
                        steps.put(nextWord, step);
                        if (nextWord.equals(endWord)) {
                            found = true;
                        }
                    }
                    charArray[j] = origin;
                }
            }
            step++;
            if (found) {
                break;
            }
        }

        // 第 2 步：深度优先遍历找到所有解，从 endWord 恢复到 beginWord ，所以每次尝试操作 path 列表的头部
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            dfs(from, path, beginWord, endWord, res);
        }
        return res;
    }

    public void dfs(Map<String, List<String>> from, Deque<String> path, String beginWord, String cur, List<List<String>> res) {
        if (cur.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String precursor : from.get(cur)) {
            path.addFirst(precursor);
            dfs(from, path, beginWord, precursor, res);
            path.removeFirst();
        }
    }
}
```

### 131-分割回文串

给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

回文串 是正着读和反着读都一样的字符串。

示例 1：

> 输入：s = "aab"
> 输出：[["a","a","b"],["aa","b"]]

```java
public class Solution131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        dfs(res, stack, s, 0);
        return res;
    }

    private void dfs(List<List<String>> res, Deque<String> stack, String s, int index) {
        if (index == s.length()) {
            res.add(new ArrayList<>(stack));
            return;
        }
        int len = s.length();
        for (int i = index + 1; i <= len; i++) {
            String substring = s.substring(index, i);
            if (isReturn(substring)) {
                stack.addLast(substring);
                dfs(res, stack, s, i);
                stack.removeLast();
            }
        }
    }

    private boolean isReturn(String substring) {
        int left = 0, right = substring.length() - 1;
        while (left <= right) {
            if (substring.charAt(left++) != substring.charAt(right--)) return false;
        }
        return true;
    }
}
```

### 216-组合总和Ⅲ

找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。 

示例 1:

> 输入: k = 3, n = 7
> 输出: [[1,2,4]]

```java
public class Solution216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        dfs(res, stack, k, n, 0, 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, Deque<Integer> stack, int k, int n, int sum, int index) {
        if (sum == n && stack.size() == k) {
            res.add(new ArrayList<>(stack));
            return;
        } else if (sum > n) {
            return;
        }
        for (int i = index; i < 10; i++) {
            stack.addLast(i);
            dfs(res, stack, k, n, sum + i, i + 1);
            stack.removeLast();
        }
    }
}
```

### 526-优美的排列

假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：

第 i 位的数字能被 i 整除
i 能被第 i 位上的数字整除
现在给定一个整数 N，请问可以构造多少个优美的排列？

示例1:

> 输入: 2
> 输出: 2
> 解释: 
> 第 1 个优美的排列是 [1, 2]:
>   第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
>   第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
> 第 2 个优美的排列是 [2, 1]:
>   第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
>   第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除

```java
public class Solution526 {
    private int count = 0;
    public int countArrangement(int n) {
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] used = new boolean[n];
        dfs(stack, used, n, 1);
        return count;
    }

    private void dfs(Deque<Integer> stack, boolean[] used, int n, int index) {
        if (stack.size() == n) count++;
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            if ((i + 1) >= index && (i + 1) % index == 0 || (i + 1) < index && index % (i + 1) == 0) {
                used[i] = true;
                stack.addLast(i + 1);
                dfs(stack, used, n, index + 1);
                stack.removeLast();
                used[i] = false;
            }
        }
    }
}
```

