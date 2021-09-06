/**
 * @author LiYangSir
 * @date 2021/6/17
 */
public class Solution17 {
    public int[] printNumbers(int n) {
        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp = temp * 10 + 9;
        }
        int[] res = new int[temp];
        for (int i = 0; i < temp; i++) {
            res[i] = i + 1;
        }
        return res;
    }
}
