/**
 * @author LiYangSir
 * @date 2021/9/7
 */
public class Solution29 {
    public int[] spiralOrder(int[][] matrix) {
        int index = 0;
        int m = matrix.length;
        if (m == 0) return new int[0];
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int[][] position = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0;
        int y = 0;
        int p = 0;
        boolean[][] used = new boolean[m][n];
        while (index < n * m) {
            result[index++] = matrix[x][y];
            used[x][y] = true;
            p = nextPosition(matrix, used, x, y, position, p);
            x += position[p][0];
            y += position[p][1];
        }
        return result;
    }

    private int nextPosition(int[][] matrix, boolean[][] used, int x, int y, int[][] position, int p) {
        int pX = x + position[p][0];
        int pY = y + position[p][1];
        if (pX >= 0 && pX < matrix.length && pY >= 0 && pY < matrix[0].length && !used[pX][pY]) {
            return p;
        }
        for (int i = 0; i < position.length; i++) {
            pX = x + position[i][0];
            pY = y + position[i][1];
            if (pX >= 0 && pX < matrix.length && pY >= 0 && pY < matrix[0].length && !used[pX][pY]) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution29 solution29 = new Solution29();
        solution29.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}
