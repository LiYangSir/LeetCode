import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution509 {
    private Map<Integer, Integer> cache = new HashMap<>();

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public int fib2(int n) {
        if (cache.containsKey(n)) return cache.get(n);
        if (n == 0) return 0;
        if (n == 1) return 1;
        int sum = fib(n - 1) + fib(n - 2);
        cache.put(n, sum);
        return sum;
    }

    public int fib3(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int pre = 0;
        int cur = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return sum;
    }


}
