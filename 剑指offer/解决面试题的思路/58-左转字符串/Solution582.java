public class Solution582 {
    public String reverseLeftWords(String s, int n) {
        if (s == null) return null;
        n %= s.length();
        if (n == 0) return s;
        return s.substring(n) + s.substring(0, n);
    }
}
