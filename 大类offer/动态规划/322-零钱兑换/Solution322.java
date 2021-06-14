import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution322 {
    /**
     * 确定状态方程 min(1 + dp(amount - icon))
     */
    private Map<Integer, Integer> cache = new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        if (cache.containsKey(amount)) return cache.get(amount);
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = coinChange(coins, amount - coin);
            if (sub == -1) continue;
            res = Math.min(res, 1 + sub);
        }
        cache.put(amount, res != Integer.MAX_VALUE ? res : -1);
        return res != Integer.MAX_VALUE ? res : -1;
    }

    public int coinChange3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Solution322 solution322 = new Solution322();
        System.out.println(solution322.coinChange(new int[]{1, 2, 5}, 100));
    }
}
