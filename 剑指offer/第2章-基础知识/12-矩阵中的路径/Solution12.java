/**
 * @author LiYangSir
 * @date 2021/6/15
 */
public class Solution12 {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, used, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[][] used, int index, int i, int j) {
        if (word.charAt(index) != board[i][j]) return false;
        if (word.length() == index + 1) return true;
        used[i][j] = true;
        boolean flag = false;
        int[][] position = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        for (int m = 0; m < 4; m++) {
            int newi = i + position[m][0];
            int newj = j + position[m][1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length && !used[newi][newj]) {
                if (dfs(board, word, used, index + 1, newi, newj)) {
                    flag = true;
                    break;
                }
            }
        }
        used[i][j] = false;
        return flag;
    }
}
