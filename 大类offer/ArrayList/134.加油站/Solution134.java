/**
 * @author LiYangSir
 * @date 2021/10/1
 */
public class Solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        for (int i = 0; i < len; i++) {
            if (gas[i] < cost[i]) continue;
            int remain = 0;
            int j = 0;
            for (; j < len; j++) {
                int index = (j + i) % len;
                if (remain + gas[index] < cost[index]) break;
                remain = remain + gas[index] - cost[index];
            }
            if (j == len) return i;
        }
        return -1;
    }
}
