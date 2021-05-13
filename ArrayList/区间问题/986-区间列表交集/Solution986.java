import java.util.ArrayList;
import java.util.List;

public class Solution986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int first = 0, second = 0;
        List<int[]> res = new ArrayList<>();
        while (first < firstList.length && second < secondList.length) {
            int intersect = intersect(firstList[first], secondList[second]);
            if (intersect == -1) {
                first++;
            } else if (intersect == 0) {
                second++;
            } else {
                int end = Math.min(firstList[first][1], secondList[second][1]);
                int start = Math.max(firstList[first][0], secondList[second][0]);
                res.add(new int[]{start, end});
                if (secondList[second][1] == end) second++;
                if (firstList[first][1] == end) first++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    private int intersect(int[] first, int[] second) {
        if (first[1] < second[0]) return -1;
        if (first[0] > second[1]) return 0;
        return 1;
    }

    public static void main(String[] args) {
        Solution986 solution986 = new Solution986();
        System.out.println(solution986.intervalIntersection(new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}}, new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}}));
    }
}
