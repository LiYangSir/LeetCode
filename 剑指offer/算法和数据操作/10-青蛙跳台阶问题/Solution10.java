/**
 * @author LiYangSir
 * @date 2021/6/15
 */
public class Solution10 {
    public int numWays(int n) {
        int pre = 1;  // 区别  f(0) = 1; f(1) = 1
        int cur = 1;
        int sum = 1;
        for (int i = 2; i <= n; i++) {
            sum = pre + cur;
            pre = cur % 1000000007;
            cur = sum % 1000000007;
        }
        return sum % 1000000007;
    }

    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.numWays(2));
    }
}
