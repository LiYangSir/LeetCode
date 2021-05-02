import java.util.Arrays;
import java.util.Comparator;

public class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int res = 1;
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            if (interval[0] > end) {
                res++;
                end = interval[1];
            }
        }
        return intervals.length - res;
    }
    public static void main(String[] args) {

    }
}
