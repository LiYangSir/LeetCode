public class Solution36 {
    public boolean isValidSudoku(char[][] board) {
        // 遍历每一行
        for (int i = 0; i < 9; i++) {
            boolean[] b = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                if (b[board[i][j] - '1']) return false;
                b[board[i][j] - '1'] = true;
            }
        }
        // 遍历每一列
        for (int i = 0; i < 9; i++) {
            boolean[] b = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') continue;
                if (b[board[j][i] - '1']) return false;
                b[board[j][i] - '1'] = true;
            }
        }
        // 遍历每一块
        for (int i = 0; i < 9; i++) {
            boolean[] b = new boolean[9];
            for (int m = 3 * (i / 3); m < 3 * (i / 3) + 3; m++) {
                for (int n = 3 * (i % 3); n < 3 * (i % 3) + 3; n++) {
                    if (board[m][n] == '.') continue;
                    if (b[board[m][n] - '1']) return false;
                    b[board[m][n] - '1'] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution36 solution36 = new Solution36();
        solution36.isValidSudoku(new char[][]{{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}});
    }
}
