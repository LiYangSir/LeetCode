import java.util.ArrayList;
import java.util.List;

public class Solution72 {
    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 1; i < l1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < l2 + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[l1][l2];
    }

    private int min(int i, int i1, int i2) {
        return Math.min(i, Math.min(i1, i2));
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(i);
        }
        List<Integer> strings = list.subList(1, 3);
        strings.forEach(System.out::println);
        strings.add(1);
        strings.remove(0);
        System.out.println(strings);
    }
}
