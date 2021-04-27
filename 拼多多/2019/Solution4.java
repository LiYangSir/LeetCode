public class Solution4 {
    public static void main(String[] args) {
        print(new int[][]{{1, 2, 3, 4},{5, 6, 7 ,8},{9, 10, 11, 12}, {13, 14, 15, 16}});
    }

    public static void print(int[][] data) {
        int left = 0, top = 0;
        int bottom = data.length - 1;
        int right = data[0].length - 1;
        int flag = 0;
        int x = 0, y = 0;
        while (left != right || bottom != top) {
            System.out.println(data[y][x]);
            if (flag == 0) {
                if (x == right) {
                    top++;
                    flag = 1;
                    y++;
                }else{
                    x++;
                }
            } else if (flag == 1) {
                if (y == bottom) {
                    right--;
                    flag = 2;
                    x--;
                }else{
                    y++;
                }
            } else if (flag == 2) {
                if (x == left) {
                    bottom--;
                    flag = 3;
                    y--;
                }else{
                    x--;
                }
            } else if (flag == 3) {
                if (y == top) {
                    left++;
                    flag = 0;
                    x++;
                }else{
                    y--;
                }
            }

        }
        System.out.println(data[bottom][left]);
    }
}
