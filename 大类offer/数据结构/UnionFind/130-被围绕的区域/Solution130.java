/**
 * @author LiYangSir
 * @date 2021/6/6
 */
public class Solution130 {
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        UnionFind uf = new UnionFind(m * n + 1);
        int dummy = m * n;
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O')
                uf.unionElement(i, dummy);
            if (board[m - 1][i] == 'O')
                uf.unionElement(i + n * (m - 1), dummy);
        }
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                uf.unionElement(i * n, dummy);
            if (board[i][n - 1] == 'O')
                uf.unionElement(i * n + n - 1, dummy);
        }
        int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if (board[x][y] == 'O') {
                            uf.unionElement(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.isConnect(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution130 solution130 = new Solution130();
        solution130.solve(new char[][]{{'X','O','X','X'},{'O','X','O','X'},{'X','O','X','O'},{'O','X','O','X'}});
    }
}
