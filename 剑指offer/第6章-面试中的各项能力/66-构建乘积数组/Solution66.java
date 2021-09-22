public class Solution66 {
    public int[] constructArr(int[] a) {
        int[] result = new int[a.length];
        for (int i = 0, cur = 1; i < a.length; i++) {
            result[i] = cur;
            cur *= a[i];
        }
        for (int i = a.length - 1, cur = 1; i >= 0; i--) {
            result[i] *= cur;
            cur *= a[i];
        }
        return result;
    }
}