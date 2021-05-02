import java.util.*;

public class Solution554 {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (List<Integer> list : wall) {
            int sum = 0;
            for (int i = 0; i < list.size() - 1; i++) { // 注意去除最后一个
                sum += list.get(i);
                cache.put(sum, cache.getOrDefault(sum, 0) + 1);
            }
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
            res = Math.max(res, entry.getValue());
        }
        return wall.size() - res;
    }
    public static void main(String[] args) {
        Solution554 solution554 = new Solution554();
    }
}
