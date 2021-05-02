import java.util.Arrays;
import java.util.Comparator;

public class Solution452 {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int res = 1;
        int end = points[0][1];
        for (int[] point : points) {
            if (point[0] > end) {
                res++;
                end = point[1];
            }
        }
        return res;
    }
}
