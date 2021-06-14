## 数据结构

## 146-LRU缓存机制
```java
class LRUCache{
    private int capacity;
    private LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        return makeRecently(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeRecently(key);
        } else {
            if (cache.size() == capacity){
                cache.remove(cache.keySet().iterator().next());
            }
            cache.put(key, value);
        }
    }

    private int makeRecently(int key){
        Integer value = cache.get(key);
        cache.remove(key);
        cache.put(key, value);
        return value;
    }
}
```
## 225. 用队列实现栈

```java
import java.util.ArrayDeque;
import java.util.Deque;

class MyStack {
    private Deque<Integer> queue = new ArrayDeque<>();
    int top_element = 0;

    public void push(int x) {
        queue.offer(x);
        top_element = x;
    }

    public int pop() {
        int size = queue.size();
        while (size - 2 > 0) {
            queue.offer(queue.poll());
            size--;
        }
        top_element = queue.poll();
        queue.offer(top_element);
        return queue.poll();
    }

    public int top() {
        return top_element;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
```

## 232. 用栈实现队列

```java
import java.util.ArrayDeque;
import java.util.Deque;

class MyStack {
    private Deque<Integer> queue = new ArrayDeque<>();
    int top_element = 0;

    public void push(int x) {
        queue.offer(x);
        top_element = x;
    }

    public int pop() {
        int size = queue.size();
        while (size - 2 > 0) {
            queue.offer(queue.poll());
            size--;
        }
        top_element = queue.poll();
        queue.offer(top_element);
        return queue.poll();
    }

    public int top() {
        return top_element;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
```

## 355-设计推特

```java
class Twitter {
    private int timestamp = 0;
    class User{
        private int id;
        private Set<Integer> followed = new HashSet<>();  // 关注者
        private Tweet head;  // 发表的文章

        public User(int id) {
            this.id = id;
            followed.add(id);
        }
        public void follow(int id) {
            followed.add(id);
        } // 需要保证User的存在
        public void unfollow(int id) {  // 无需保证User的存在
            if (this.id != id) {
                followed.remove(id);
            }
        }
        public void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId, timestamp++);
            tweet.next = head;
            head = tweet;
        }
    }
    class Tweet{
        private int context;
        private int time;
        private Tweet next;

        public Tweet(int context, int time) {
            this.context = context;
            this.time = time;
        }
    }

    private Map<Integer, User> map = new HashMap<>();
    public void postTweet(int userId, int tweetId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
        }
        User user = map.get(userId);
        user.post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
            return res;
        }
        // 或许最新的自己和关注着的最新消息
        Set<Integer> users = map.get(userId).followed;
        PriorityQueue<Tweet> queue = new PriorityQueue<>((o1, o2) -> o2.time - o1.time);
        for (Integer user : users) {
            Tweet head = map.get(user).head;
            if (head == null) continue;
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            if (res.size() == 10) break;
            Tweet tweet = queue.poll();
            res.add(tweet.context);
            if (tweet.next != null) queue.add(tweet.next);
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        addOrNothing(followerId);
        addOrNothing(followeeId);
        User user = map.get(followerId);
        user.follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (map.containsKey(followerId)) {
            User user = map.get(followerId);
            user.unfollow(followeeId);
        }
    }

    private void addOrNothing(int userId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
        }
    }
}
```



## UnionFind

### 130-被围绕的区域

给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例 1：

![img](https://markdown-liyang.oss-cn-beijing.aliyuncs.com/blog/elasticsearch/xogrid.jpg)

> 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
> 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
> 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

示例 2：

> 输入：board = [["X"]]
> 输出：[["X"]]

```java
public class Solution130 {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        UnionFind uf = new UnionFind(m * n + 1);
        int dummy = m * n;
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O')
                uf.unionElement(i, dummy);
            if (board[m - 1][i] == 'O')
                uf.unionElement(i + n * (m - 1), dummy);
        }
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                uf.unionElement(i * n, dummy);
            if (board[i][n - 1] == 'O')
                uf.unionElement(i * n + n - 1, dummy);
        }
        int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if (board[x][y] == 'O') {
                            uf.unionElement(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.isConnect(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
```



### Solution721

```java

```



### 990-等式方程的可满足性

给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

示例 1：

> 输入：["a==b","b!=a"]
> 输出：false
> 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。

示例 2：

> 输入：["b==a","a==b"]
> 输出：true
> 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。

```java
public class Solution990 {
    public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                uf.unionElements(a - 'a', b - 'a');
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                if (uf.isConnected(a - 'a', b - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
    class UF {
        private int[] rank;
        private int[] parent;

        private int count;

        public UF(int count) {
            this.count = count;
            this.rank = new int[count];
            this.parent = new int[count];
            for (int i = 0; i < count; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public int getSize(){
            return this.count;
        }

        public boolean isConnected(int p, int q){
            return findParent(p) == findParent(q);
        }

        private int findParent(int id) {
            while (parent[id] != id) {
                parent[id] = parent[parent[id]];
                id = parent[id];
            }
            return id;
        }

        public void unionElements(int p, int q) {
            int pRoot = findParent(p);
            int qRoot = findParent(q);
            if (isConnected(pRoot, qRoot)) return;
            if (rank[pRoot] < rank[qRoot])
                parent[pRoot] = qRoot;
            else if (rank[pRoot] > rank[qRoot])
                parent[qRoot] = pRoot;
            else{
                parent[pRoot] = qRoot;
                rank[qRoot] ++;
            }

        }

    }
}
```



### 1319-连通网络的操作次数

用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。

网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。

给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1

示例 1：

> 输入：n = 4, connections = [[0,1],[0,2],[1,2]]
> 输出：1
> 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。

```java
public class Solution1319 {
    public int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        int m = connections.length;
        if (m < n - 1) return -1;
        for (int i = 0; i < m; i++) {
            uf.unionElement(connections[i][0], connections[i][1]);
        }
        return uf.getDependent() - 1;
    }
}
```



## 单调栈

### 496-下一个更大元素 I
给你两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。
请你找出 nums1中每个元素在nums2中的下一个比其大的值。
nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。如果不存在，对应位置输出 -1 。

示例 1:
>输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。

示例 2:
>输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
   对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。


```java
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

```
### 503-下一个更大元素 II
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
示例 1:
>输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数； 
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
```java
public class Solution503 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]){
                stack.pop();
            }
            res[i % n] = stack.isEmpty()? -1: stack.peek();
            stack.push(nums[i % n]);
        }
        return res;
    }
}
```
### 739-每日温度
请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
```java
public class Solution739 {
    public int[] dailyTemperatures(int[] T) { // 保存索引
        Deque<Integer> stack = new ArrayDeque<>();
        int n = T.length;
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty()? 0: stack.peek() - i;
            stack.push(i);
        }
        return res;
    }
}
```
## 单调队列
### 239-滑动窗口最大值
给你一个整数数组 nums，有一个大小为`k`的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回滑动窗口中的最大值。

示例 1：
>输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]

```java
public class Solution239 {
    class MonitorQueue {
        private Deque<Integer> stack = new ArrayDeque<>();

        public void pop(int key) {
            if (!stack.isEmpty() && stack.getFirst() == key) {
                stack.removeFirst();
            }
        }

        public int max() {
            return stack.getFirst();
        }

        public void push(int key) {
            while (!stack.isEmpty() && stack.getLast() < key) {  // 必须是小于号,等于号并不会移除
                stack.removeLast();
            }
            stack.addLast(key);
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonitorQueue monitorQueue = new MonitorQueue();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            if (i < k - 1) {
                monitorQueue.push(nums[i]);
            } else {
                monitorQueue.push(nums[i]);
                res[i - k + 1] = monitorQueue.max();
                monitorQueue.pop(nums[i - k + 1]);
            }
        }
        return res;
    }
}
```
