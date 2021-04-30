import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution62 {
    // 递归完成
    private Map<Integer, Integer> cache = new HashMap<>();
    public int uniquePaths(int m, int n) {
        return uniquePaths(1, 1, m, n);
    }
    public int uniquePaths(int m, int n, int row, int ver) {
        int key = n * 100 + m;
        if (cache.containsKey(key)) return cache.get(key);
        if (m == row) return 1;
        if (n == ver) return 1;
        int res = uniquePaths(m + 1, n, row, ver) + uniquePaths(m, n + 1, row, ver);
        cache.put(key, res);
        return res;
    }

    // 迭代完成  速度更快
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution62 solution62 = new Solution62();
        System.out.println(solution62.uniquePaths2(3, 3));
    }
}
