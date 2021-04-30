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