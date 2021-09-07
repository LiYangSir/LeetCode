/**
 * @author LiYangSir
 * @date 2021/6/17
 */
public class Solution15 {
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
