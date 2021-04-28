public class Solution633 {
    public boolean judgeSquareSum(int c) {
        int size = (int) Math.sqrt(c);
        for (int i = 0; i <= size; i++) {
            int b = (int) Math.sqrt(c - i * i);
            if (b * b == c - i * i)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution633 solution633 = new Solution633();
        System.out.println(solution633.judgeSquareSum(2));
    }
}
