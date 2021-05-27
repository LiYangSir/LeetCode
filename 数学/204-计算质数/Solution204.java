import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution204 {
    public int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);

        for (int i = 2; i * i < n; i++) { // 截取一半
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) { // 成倍数的增加
                    isPrim[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) count++;
        }
        return count;
    }


    public static void main(String[] args) {
        Solution204 solution204 = new Solution204();
        System.out.println(solution204.countPrimes(499979));
    }
}
