import java.util.HashMap;
import java.util.Map;

/**
 * @author LiYangSir
 * @date 2021/6/17
 */
public class Solution16 {
    private boolean invalidInput = false;
    public double pow(double x, long n) {
        if (x == 0) return 0;
        if (x == 1 || n == 0) return 1;
        if (n == 1) return x;
        double res = pow(x, n >> 1);
        res *= res;
        if ((n & 0x1) == 1) res *= x;
        return res;
    }

    public double myPow(double x, int n) {
        if (x == 0 && n <= 0) {
            invalidInput = true;
            return 0;
        }
        long abs = Math.abs((long)n);
        return n < 0? 1 / pow(x, abs) : pow(x, abs);
    }

    public static void main(String[] args) {
        Solution16 solution16 = new Solution16();
        System.out.println(solution16.myPow(2.00000, -2));
    }
}
