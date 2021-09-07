public class Solution62 {
    public int lastRemaining(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int i = lastRemaining(n - 1, m);
        return (i + m) % n;
    }
}
