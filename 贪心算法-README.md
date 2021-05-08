## 贪心算法
### 45. 跳跃游戏

```java
public class Solution45 {
     // 反向传播
    public int jump(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (nums[i] + i >= position) {
                    position = i;
                    steps++;
                    break;   // 及时break
                }
            }
        }
        return steps;
    }
    //时间复杂度更低，正向传播： 	Good
    public int jump2(int[] nums) {
        int steps = 0;
        int maxPosition = 0;
        int end = 0;
        for (int i = 0; i < nums.length - 1; i++) {  // 注意这里不需要跳到最后一个位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (end == i) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
```
### 435. 无重叠区间

给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
注意:
可以认为区间的终点总是大于它的起点。
区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
示例 1:

>输入: [ [1,2], [2,3], [3,4], [1,3] ]
>输出: 1
>解释: 移除 [1,3] 后，剩下的区间没有重叠。

```java
public class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int res = 1;
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            if (interval[0] >       end) {
                res++;
                end = interval[1];
            }
        }
        return intervals.length - res;
    }
}
```

### 452. 用最少数量的箭引爆气球
在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。

示例 1：
>输入：points = [[10,16],[2,8],[1,6],[7,12]]
>输出：2
>解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球

示例 2：
>输入：points = [[1,2],[3,4],[5,6],[7,8]]
>输出：4

```java
public class Solution452 {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int res = 1;
        int end = points[0][1];
        for (int[] point : points) {
            if (point[0] > end) {  // 仅修改这里
                res++;
                end = point[1];
            }
        }
        return res;
    }
}
```