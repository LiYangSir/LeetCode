public class Solution65 {
    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a = a ^ b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        Solution65 solution65 = new Solution65();
        System.out.println(solution65.add(1, 255));
    }
}
