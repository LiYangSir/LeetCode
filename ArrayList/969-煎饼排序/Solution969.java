import java.util.ArrayList;
import java.util.List;

public class Solution969 {
    private List<Integer> res = new ArrayList<>();

    public List<Integer> pancakeSort(int[] arr) {
        pancakeSort(arr, arr.length);
        return res;
    }

    private void pancakeSort(int[] arr, int n) {
        if (n == 1) return;
        int maxIndex = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        swap(arr, 0, maxIndex);
        res.add(maxIndex + 1);
        swap(arr, 0, n - 1);
        res.add(n);
        pancakeSort(arr, n - 1);
    }

    private void swap(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
