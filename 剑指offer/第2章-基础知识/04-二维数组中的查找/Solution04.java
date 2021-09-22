/**
 * @author LiYangSir
 * @date 2021/6/14
 */
public class Solution04 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        return dfs(matrix, target, 0, matrix[0].length - 1);
    }

    private boolean dfs(int[][] matrix, int target, int i, int j) {
        if (i >= matrix.length || j < 0) return false;
        if (matrix[i][j] == target) return true;
        if (matrix[i][j] > target) {
            return dfs(matrix, target, i, j - 1);
        } else {
            return dfs(matrix, target, i + 1, j);
        }
    }
}
