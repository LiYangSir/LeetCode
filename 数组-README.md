### 数组

###  双指针（前提是必须排序: 前提是有序无序不重要）
#### 11. 盛最多的水
给你 n 个非负整数 `a1，a2，...，an`，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器。

<img src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg" alt="img" style="zoom:50%;" />

示例 1：

> 输入：[1,8,6,2,5,4,8,3,7]
> 输出：49 
> 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。

示例 2：
>输入：height = [1,1]
>输出：1

示例 3：

> 输入：height = [4,3,2,1,4]
> 输出：16

示例 4：

> 输入：height = [1,2,1]
> 输出：2

```java
public class Solution11 {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (right > left) {
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, area);
            int a = height[left] > height[right] ? right-- : left++; // 核心代码，定理哪边大去哪边
        }
        return max;
    }
}
```
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
#### 16. 最接近的三数之和

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

#### 42-接雨水

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

 

示例 1：

![img](https://markdown-liyang.oss-cn-beijing.aliyuncs.com/blog/elasticsearch/rainwatertrap.png)

> 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
> 输出：6
> 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 

示例 2：

> 输入：height = [4,2,0,3,2,5]
> 输出：9

```java
import java.awt.event.MouseAdapter;

public class Solution42 {
    // 暴力解法
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        for (int i = 1; i < n - 1; i++) {
            int l_max = 0, r_max = 0;
            for (int j = i; j < n; j++) {
                r_max = Math.max(r_max, height[j]);
            }
            for (int j = i; j >= 0; j--) {
                l_max = Math.max(l_max, height[j]);
            }
            res += Math.min(l_max, r_max) - height[i];
        }
        return res;
    }
    // 动态规划
    public int trap2(int[] height) {
        int res = 0;
        int n = height.length;
        if (n == 0) return 0;
        int[] l_max = new int[n];
        int[] r_max = new int[n];
        l_max[0] = height[0];
        r_max[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            res += Math.min(l_max[i], r_max[i]) - height[i];
        }
        return res;
    }
    // 双指针
    public int trap3(int[] height) {
        int res = 0;
        int n = height.length;
        if (n == 0) return 0;
        int left = 0;
        int right = n - 1;
        int l_max = height[0];
        int r_max = height[n - 1];
        while (left <= right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);
            if (l_max < r_max) {
                res += l_max - height[left];
                left++;
            } else {
                res += r_max - height[right];
                right--;
            }
        }
        return res;
    }
}
```

### 二分搜索法

模板方法

```java
int binarySearch(int[] nums, int target) {
    int left = 0; 
    int right = nums.length; // 注意

    while(left < right) {
        int mid = left + (right - left) / 2;
        if(nums[mid] == target)
            return mid; 
        else if (nums[mid] < target)
            left = mid + 1; // 注意
        else if (nums[mid] > target)
            right = mid; // 注意
    }
    return -1;
}
```

```java
int left_bound(int[] nums, int target) {
    if (nums.length == 0) return -1;
    int left = 0;
    int right = nums.length;
    
    while (left < right) {
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            right = mid;   // 注意
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid;
        }
    }
    return left;  // 注意
}
```

```java
int right_bound(int[] nums, int target) {
    if (nums.length == 0) return -1;
    int left = 0, right = nums.length;
    
    while (left < right) {
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            left = mid + 1;    // 注意
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid;
        }
    }
    return left - 1; // 注意
}
```

#### 33. 搜索旋转排序数组

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
            if(nums[left] <= nums[mid]){  // 先判断在那个坡度上
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
#### 34. 在排序数组中查找元素的第一个和最后一个元素

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。


示例 1：

> 输入：nums = [5,7,7,8,8,10], target = 8
> 输出：[3,4]

示例 2：

> 输入：nums = [5,7,7,8,8,10], target = 6
> 输出：[-1,-1]

示例 3：

> 输入：nums = [], target = 0
> 输出：[-1,-1]

```java
public class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                int l = mid;
                int r = mid;
                while (l - 1 >= 0 && nums[l - 1] == nums[mid]){l--;};
                while (r + 1 < nums.length && nums[r + 1] == nums[mid]){r++;};
                return new int[]{l, r};
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }
}
```

#### 162. 寻找峰值
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

#### 875-爱吃香蕉的珂珂

珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。

珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  

珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。

示例 1：

> 输入: piles = [3,6,7,11], H = 8
> 输出: 4

示例 2：

> 输入: piles = [30,11,23,4,20], H = 5
> 输出: 30

示例 3：

> 输入: piles = [30,11,23,4,20], H = 6
> 输出: 23

```java
public class Solution875 {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = getMax(piles) + 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (canEating(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canEating(int[] piles, int mid, int h) {
        int times = 0;
        for (int pile : piles) {
            times += (pile / mid) + (pile % mid > 0 ? 1 : 0);
        }
        return times <= h;
    }

    private int getMax(int[] piles) {
        int max = piles[0];
        for (int i = 1; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        return max;
    }
}
```

#### 1011-在 D 天内送达包裹的能力

传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。

传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。

示例 1：

> 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
> 输出：15
> 解释：
> 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
> 第 1 天：1, 2, 3, 4, 5
> 第 2 天：6, 7
> 第 3 天：8
> 第 4 天：9
> 第 5 天：10

请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。 

```java
public class Solution1011 {
    public int shipWithinDays(int[] weights, int days) {
        int left = getMax(weights);
        int right = getSum(weights) + 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (canShip(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int getSum(int[] weights) {
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
        }
        return sum;
    }

    private int getMax(int[] weights) {
        int max = Integer.MIN_VALUE;
        for (int weight : weights) {
            max = Math.max(weight, max);
        }
        return max;
    }

    private boolean canShip(int[] weights, int mid, int days) {
        int sum = 0;
        int day = 0;
        for (int weight : weights) {
            if (sum + weight > mid) {
                day++;
                sum = 0;
                if (day >= days) return false;
            }
            sum += weight;
        }
        return true;
    }
}
```

### 深度优先搜索

#### 733 图像渲染

有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。

为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

最后返回经过上色渲染后的图像。

示例 1:

> 输入: 
> image = [[1,1,1],[1,1,0],[1,0,1]]
> sr = 1, sc = 1, newColor = 2
> 输出: [[2,2,2],[2,2,0],[2,0,1]]
> 解析: 
> 在图像的正中间，(坐标(sr,sc)=(1,1)),
> 在路径上所有符合条件的像素点的颜色都被更改成2。
> 注意，右下角的像素没有更改为2，
> 因为它不是在上下左右四个方向上与初始点相连的像素点

```java
public class Solution733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        floodFill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void floodFill(int[][] image, int sr, int sc, int oriColor, int newColor) {
        // 终止条件
        if (!inArea(image, sr, sc)) return;
        if (image[sr][sc] != oriColor) return;
        if (image[sr][sc] == -1) return;

        image[sr][sc] = -1;
        floodFill(image, sr, sc - 1, oriColor, newColor);
        floodFill(image, sr, sc + 1, oriColor, newColor);
        floodFill(image, sr - 1, sc, oriColor, newColor);
        floodFill(image, sr + 1, sc, oriColor, newColor);
        image[sr][sc] = newColor;
    }

    private boolean inArea(int[][] image, int sr, int sc) {
        return sr >= 0 && sr < image.length && sc >= 0 && sc < image[0].length;
    }
}
```



### 31. 下一个排列

实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须 原地 修改，只允许使用额外常数空间。

示例 1：

> 输入：nums = [1,2,3]
> 输出：[1,3,2]

示例 2：

> 输入：nums = [3,2,1]
> 输出：[1,2,3]

示例 3：

> 输入：nums = [1,1,5]
> 输出：[1,5,1]


示例 4：

> 输入：nums = [1]
> 输出：[1]


```java
/**
  * 核心是从后往前找到一个较小的数，也就是突然递减的数
  * 根据这个较小的数，找到一个比它大的数进行替换，保证后面的一个有序性
  * 并反转较小数后面的数，保证数据的递增
  */
public class Solution31 {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i] <= nums[i - 1]){
            i--;
        }
        i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }
        for (int m = i + 1; m < i + 1 + (nums.length - i - 1) / 2; m++) {
            swap(nums, m, nums.length - m + i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### 48. 搜索旋转图像

给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

示例 1：
>输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
>输出：[[7,4,1],[8,5,2],[9,6,3]]

示例 2：
>输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
>输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

示例 3：
>输入：matrix = [[1]]
>输出：[[1]]

示例 4：
>输入：matrix = [[1,2],[3,4]]
>输出：[[3,1],[4,2]]

```java
class Solution48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j -1];
                matrix[i][n - j -1] = temp;
            }
        }
    }
}
```

### 448-找到所有数组中消失的数字

给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:

> 输入:
> [4,3,2,7,8,2,3,1]
>
> 输出:
> [5,6]

```java
public class Solution448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) {
            nums[(num - 1) % n] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n)
                res.add(i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution448 solution448 = new Solution448();
        System.out.println(solution448.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
```

### 560-和为K的子数组(前缀和)

给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

> 输入:nums = [1,1,1], k = 2
> 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
> 说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。

```java
public class Solution560 {
    public int subarraySum(int[] nums, int k) {  // 速度更快
        int n = nums.length;
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        int res = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (cache.containsKey(sum - k)) {
                res += cache.get(sum - k);
            }
            cache.put(sum, cache.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (k == pre[j] - pre[i]) res++;
            }
        }
        return res;
    }
}
```

### 645-错误的集合

集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。

给定一个数组 nums 代表了集合 S 发生错误后的结果。

请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。

示例 1：

> 输入：nums = [1,2,2,4]
> 输出：[2,3]

示例 2：

> 输入：nums = [1,1]
> 输出：[1,2]

```java
public class Solution645 {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            nums[(num - 1) % n] += n;
        }
        List<Integer> dup = new ArrayList<>();
        List<Integer> re = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n){
                re.add(i + 1);
            } else if (nums[i] > 2 * n) {
                dup.add(i + 1);
            }
        }
        int[] res = new int[dup.size() + re.size()];
        for (int i = 0; i < dup.size(); i++) {
            res[i] = dup.get(i);
        }
        for (int i = 0; i < re.size(); i++) {
            res[i + dup.size()] = re.get(i);
        }
        return res;
    }

    public int[] findErrorNums2(int[] nums) {
        int n = nums.length;
        List<Integer> dup = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                dup.add(Math.abs(nums[i]));
            } else {
                nums[index] *= -1;
            }
        }
        List<Integer> re = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0)
                re.add(i + 1);
        }
        int[] res = new int[dup.size() + re.size()];
        for (int i = 0; i < dup.size(); i++) {
            res[i] = dup.get(i);
        }
        for (int i = 0; i < re.size(); i++) {
            res[i + dup.size()] = re.get(i);
        }
        return res;
    }
    public static void main(String[] args) {
        Solution645 solution645 = new Solution645();
        System.out.println(Arrays.toString(solution645.findErrorNums2(new int[]{2, 2})));
    }
}
```

### 855-考场就座

在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。

当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)

返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。

```java
class ExamRoom {

    private Map<Integer, int[]> start = new HashMap<>();
    private Map<Integer, int[]> end = new HashMap<>();
    private TreeSet<int[]> pq;
    int n;
    public ExamRoom(int n) {
        this.n = n;
        pq = new TreeSet<>((o1, o2) -> {
            int distA = distance(o1);
            int distB = distance(o2);
            if (distA == distB)
                return o2[0] - o1[0];
            return distA - distB;
        });
        addInternal(new int[]{-1, n});
    }

    public int seat() {
        int[] last = pq.last();
        int seat;
        int x = last[0];
        int y = last[1];
        if (x == -1) seat = 0;
        else if (y == n) seat = n - 1;
        else seat = (x + y) / 2;
        int[] left = new int[]{x, seat};
        int[] right = new int[]{seat, y};
        removeInternal(last);
        addInternal(left);
        addInternal(right);
        return seat;
    }

    public void leave(int p) {
        int[] right = start.get(p);
        int[] left = end.get(p);
        removeInternal(left);
        removeInternal(right);
        addInternal(new int[]{left[0], right[1]});
    }

    private void addInternal(int[] intv) {
        pq.add(intv);
        start.put(intv[0], intv);
        end.put(intv[1], intv);
    }

    private void removeInternal(int[] intv) {
        start.remove(intv[0]);
        end.remove(intv[1]);
        pq.remove(intv);
    }

    private int distance(int[] o) {
        int x = o[0];
        int y = o[1];
        if (x == -1) return y;
        if (y == n) return n - 1 - x;

        return (y - x) / 2;
    }
}
```

### 969-煎饼排序

给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。

一次煎饼翻转的执行过程如下：

选择一个整数 k ，1 <= k <= arr.length
反转子数组 arr[0...k-1]（下标从 0 开始）
例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。

以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。

 

示例 1：

> 输入：[3,2,4,1]
> 输出：[4,2,4,3]
> 解释：
> 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
> 初始状态 arr = [3, 2, 4, 1]
> 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
> 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
> 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
> 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。 

示例 2：

> 输入：[1,2,3]
> 输出：[]
> 解释：
> 输入已经排序，因此不需要翻转任何内容。
> 请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。

```java
public class Solution969 {
    private List<Integer> res = new ArrayList<>();

    public List<Integer> pancakeSort(int[] arr) {
        pancakeSort(arr, arr.length);
        return res;
    }

    private void pancakeSort(int[] arr, int n) {
        if (n == 1) return;
        int maxIndex = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        swap(arr, 0, maxIndex);
        res.add(maxIndex + 1);
        swap(arr, 0, n - 1);
        res.add(n);
        pancakeSort(arr, n - 1);
    }

    private void swap(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
```

