/**
 * @author LiYangSir
 * @date 2021/9/14
 */
public class Solution343 {
    // 递归实现
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        return sub(n);
    }

    private int sub(int n) {
        if (n <= 4) return n;
        return 3 * sub(n - 3);
    }
    // 循环实现
    public int integerBreak2(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int result = 1;
        while (n > 4){
            result *= 3;
            n -= 3;
        }
        return result * n;
    }

}
