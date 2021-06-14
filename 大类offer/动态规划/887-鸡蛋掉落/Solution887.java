import java.util.HashMap;
import java.util.Map;

public class Solution887 {
    private Map<Integer, Integer> cache = new HashMap<>();
    // 递归实现: 容易出现栈溢出
    public int superEggDrop(int k, int n) {
        int key = n * 100 + k;
        if (cache.containsKey(key)) return cache.get(key);
        if (n == 0) return 0;
        if (k == 1) return n;
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            res = Math.min(res, Math.max(superEggDrop(k, n - i), superEggDrop(k - 1, i - 1)) + 1);
        }
        cache.put(key, res);
        return res;
    }
    // 迭代实现
    public int superEggDrop2(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        int m = 0;
        while (dp[k][m] < n){
            m++;
            for (int i = 1; i <= k; i++) {
                dp[i][m] = dp[i][m - 1] + dp[i - 1][m - 1] + 1;
            }
        }
        return m;
    }
    public static void main(String[] args) {
        Solution887 solution887 = new Solution887();
        System.out.println(solution887.superEggDrop2(2, 6));
    }
}
