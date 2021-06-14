## 哈希表

### 36. 有效的数独

请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
数独部分空格内已填入了数字，空白格用 '.' 表示。

注意：

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。

```java
public class Solution36 {
    public boolean isValidSudoku(char[][] board) {
        // 遍历每一行
        for (int i = 0; i < 9; i++) {
            boolean[] b = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                if (b[board[i][j] - '1']) return false;
                b[board[i][j] - '1'] = true;
            }
        }
        // 遍历每一列
        for (int i = 0; i < 9; i++) {
            boolean[] b = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') continue;
                if (b[board[j][i] - '1']) return false;
                b[board[j][i] - '1'] = true;
            }
        }
        // 遍历每一块
        for (int i = 0; i < 9; i++) {
            boolean[] b = new boolean[9];
            for (int m = 3 * (i / 3); m < 3 * (i / 3) + 3; m++) {
                for (int n = 3 * (i % 3); n < 3 * (i % 3) + 3; n++) {
                    if (board[m][n] == '.') continue;
                    if (b[board[m][n] - '1']) return false;
                    b[board[m][n] - '1'] = true;
                }
            }
        }
        return true;
    }
}
```

### 49. 字母异位词分组

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

>输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
>输出:
>[
>  ["ate","eat","tea"],
>  ["nat","tan"],
>  ["bat"]
>]

```java
public class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        Map<Long, List<String>> map = new HashMap<>();
        for (String str : strs) {
            long key = 1;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                key *= prime[c - 'a'];
            }
            if (!map.containsKey(key))
                map.put(key, new ArrayList<>());
            List<String> strings = map.get(key);
            strings.add(str);
        }
        List<List<String>> res = new ArrayList<>();
        for (Long aLong : map.keySet()) {
            res.add(map.get(aLong));
        }
        return res;
    }
}
```

### 554. 砖墙

你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和相等。

你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。

给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。

 

示例 1：

![img](https://assets.leetcode.com/uploads/2021/04/24/cutwall-grid.jpg)

输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
输出：2
示例 2：

输入：wall = [[1],[1],[1]]
输出：3

```java
public class Solution554 {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (List<Integer> list : wall) {
            int sum = 0;
            for (int i = 0; i < list.size() - 1; i++) { // 注意去除最后一个
                sum += list.get(i);
                cache.put(sum, cache.getOrDefault(sum, 0) + 1);
            }
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
            res = Math.max(res, entry.getValue());
        }
        return wall.size() - res;
    }
}

```

