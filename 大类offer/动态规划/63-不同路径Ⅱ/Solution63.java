import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution63 {
    // 迭代完成  速度更快
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 初始化第一行
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1)break;
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                if (obstacleGrid[i - 1][j] != 1) {
                    dp[i][j] += dp[i - 1][j];
                }
                if (obstacleGrid[i][j - 1] != 1) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution63 solution63 = new Solution63();
        System.out.println(solution63.uniquePathsWithObstacles(new int[][]{{1}}));
    }
}
