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
