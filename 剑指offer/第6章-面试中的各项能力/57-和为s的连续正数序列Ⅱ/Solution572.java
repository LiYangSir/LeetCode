import java.util.ArrayList;
import java.util.List;

public class Solution572 {
    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        for (int i = target; i >= 2; i--) {
            int chu = target / i;
            int yu = target % i;
            if ((yu != 0 || i % 2 == 0) && (yu * 2 != i || i % 2 == 1)) {
                continue;
            }
            int start = chu - (i - 1) / 2;
            if (start < 1) continue;
            int[] subRes = new int[i];
            for (int i1 = 0; i1 < i; i1++) {
                subRes[i1] = start + i1;
            }
            res.add(subRes);
        }
        int[][] result = new int[res.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution572 solution572 = new Solution572();
        solution572.findContinuousSequence(10);
    }
}
