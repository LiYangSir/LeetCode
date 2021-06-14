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
