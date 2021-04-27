## 数组

### 双指针
#### 15. 三数之和
给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。

示例 1：
>输入：nums = [-1,0,1,2,-1,-4]
>输出：[[-1,-1,2],[-1,0,1]]

示例 2：
>输入：nums = []
>输出：[]

示例 3：
>输入：nums = [0]
>输出：[]

```java
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < length - 2; i++) {
            int l = i + 1;
            int r = length - 1;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    while (l < r && nums[r] == nums[--r]);
                    while (l < r && nums[l] == nums[++l]);
                } else if (sum > 0) {
                    while (l < r && nums[r] == nums[--r]);
                } else {
                    while (l < r && nums[l] == nums[++l]);
                }

            }
        }
        return res;
    }
}
```
#### 15. 最接近的三数之和

给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。

示例：
> 输入：nums = [-1,2,1,-4], target = 1
> 输出：2
> 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。

```java
public class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;
        int best = 10000;
        for (int i = 0; i < length - 2; i++) {
            int l = i + 1;
            int r = length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(target - sum) < Math.abs(target - best)) {
                    best = sum;
                }
                if (sum > target) {
                    while (l < r && nums[r] == nums[--r]);
                } else {
                    while (l < r && nums[l] == nums[++l]);
                }
            }
        }
        return best;
    }
}

```

### 33. 搜索旋转排序数组
整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

示例 1：

> 输入：nums = [4,5,6,7,0,1,2], target = 0
> 输出：4

示例 2：

> 输入：nums = [4,5,6,7,0,1,2], target = 3
> 输出：-1

示例 3：

> 输入：nums = [1], target = 0
> 输出：-1

```java
public class Solution33 {
    public int search(int[] nums, int target) {
        if(nums.length == 1)
            return nums[0] == target? 0: -1;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target)
                return mid;
            if(nums[left] <= nums[mid]){
                if(nums[left] <= target && nums[mid] > target){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                if(nums[mid] < target && nums[right] >= target){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

### 162. 寻找峰值
峰值元素是指其值大于左右相邻值的元素。
给你一个输入数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。


示例 1：

>输入：nums = [1,2,3,1]
>输出：2
>解释：3 是峰值元素，你的函数应该返回其索引 2。

示例2：

>输入：nums = [1,2,1,3,5,6,4]
>输出：1 或 5 
>解释：你的函数可以返回索引 1，其峰值元素为 2；
    或者返回索引 5， 其峰值元素为 6。
>

```java
public class Solution162 {
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
```

### 300. 最长递增子序列

给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

示例 1：
>输入：nums = [10,9,2,5,3,7,101,18]
>输出：4
>解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

示例 2：
>输入：nums = [0,1,0,3,2,3]
>输出：4

示例 3：
>输入：nums = [7,7,7,7,7,7,7]
>输出：1
>
```java
public class Solution300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1)
            return 1;

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

```