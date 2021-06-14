import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author LiYangSir
 * @date 2021/6/10
 */
public class Solution79 {

    public boolean exist(char[][] board, String word) {
        int h = board.length;
        int w = board[0].length;
        boolean[][] used = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (dfs(board, word, used, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[][] used, int index, int i, int j) {
        if (board[i][j] != word.charAt(index)) return false;
        else if (index + 1 == word.length()) return true;

        int m = board.length;
        int n = board[0].length;
        used[i][j] = true;
        boolean flag = false;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] direction : directions) {
            int newi = i + direction[0];
            int newj = j + direction[1];
            if (newi >= 0 && newi < m && newj >= 0 && newj < n && !used[newi][newj]) {
                if (dfs(board, word, used, index + 1, newi, newj)) {
                    flag = true;
                    break;
                }
            }
        }
        used[i][j] = false;
        return flag;
    }

    public static void main(String[] args) {
        Solution79 solution79 = new Solution79();
        System.out.println(solution79.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "SEE"));
    }
}
