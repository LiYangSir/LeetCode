/**
 * @author LiYangSir
 * @date 2021/6/15
 */
public class Solution114 {
    public int cuttingRope(int n) {
        if (n < 3) return 1;
        if (n == 3) return 2;
        long res = 1;
        while (n > 4) {
            res *= 3;
            res %= 1000000007;
            n -= 3;
        }
        return (int) (n * res % 1000000007);
    }

    public static void main(String[] args) {
        Solution114 solution114 = new Solution114();
        System.out.println(solution114.cuttingRope(1000));
    }
}
