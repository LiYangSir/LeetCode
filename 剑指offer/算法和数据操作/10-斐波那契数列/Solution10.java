package s10_斐波那契数列;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiYangSir
 * @date 2021/6/14
 */
public class Solution10 {

    private int[] cache;

    public int fib2(int n) {
        if (n < 2) return n;
        cache = new int[n + 1];
        cache[1] = 1;
        return compute(n) % 1000000007;
    }

    private int compute(int n) {
        if (n < 2 || cache[n] != 0) return cache[n];
        int res = compute(n - 1) % 1000000007 + compute(n - 2) % 1000000007;
        cache[n] = res;
        return res;
    }

    public int fib3(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;
        int pre = 0;
        int cur = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = pre + cur;
            pre = cur % 1000000007;
            cur = sum % 1000000007;
        }
        return sum % 1000000007;
    }

    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.fib3(48));
    }
}
