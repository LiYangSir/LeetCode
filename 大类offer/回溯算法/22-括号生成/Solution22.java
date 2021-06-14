import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 将括号看成["(", ")"]
 */
public class Solution22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        dfs(res, builder,0, 0, n);
        return res;
    }

    private void dfs(List<String> res, StringBuilder builder, int open, int close, int max) {
        if (open + close == max * 2) {
            res.add(builder.toString());
        }
        if (open < max) {
            builder.append("(");
            dfs(res, builder, open + 1, close, max);
            builder.delete(builder.length() - 1, builder.length());
        }
        if (close < open) {
            builder.append(")");
            dfs(res, builder, open, close + 1, max);
            builder.delete(builder.length() - 1, builder.length());
        }
    }


    public static void main(String[] args) {
        Solution22 solution22 = new Solution22();
        System.out.println(solution22.generateParenthesis(3));
    }
}
