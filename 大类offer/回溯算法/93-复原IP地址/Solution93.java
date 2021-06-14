import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author LiYangSir
 * @date 2021/6/7
 */
public class Solution93 {
    public List<String> restoreIpAddresses(String s) {
        Deque<String> stack = new ArrayDeque<>();
        List<String> res = new ArrayList<>();

        dfs(s, res, stack, 0);
        return res;
    }

    private void dfs(String s, List<String> res, Deque<String> stack, int index) {
        if (stack.size() == 4) {
            if (index < s.length()) return;
            // 拼接stack
            List<String> list = new ArrayList<>(stack);
            StringBuilder sb = new StringBuilder();
            for (String l : list) {
                sb.append(l + '.');
            }
            sb.delete(sb.length() - 1, sb.length());
            res.add(sb.toString());
            return;
        }
        List<Integer> list = listActive(s, index);
        if (list.size() == 0) return;
        for (int i = 0; i < list.size(); i++) {
            stack.addLast(s.substring(index, list.get(i)));
            dfs(s, res, stack, list.get(i));
            stack.removeLast();
        }
    }

    private List<Integer> listActive(String s, int index) {
        List<Integer> res = new ArrayList<>();
        int len = s.length();
        if (index == len) return res;
        char c = s.charAt(index);
        if (c == '0') {
            res.add(index + 1);
        } else if (c == '1') {
            res.add(index + 1);
            if (index + 2 <= len) res.add(index + 2);
            if (index + 3 <= len) res.add(index + 3);
        } else if (c == '2') {
            res.add(index + 1);
            if (index + 2 <= len) res.add(index + 2);
            if (index + 3 <= len && Integer.parseInt(s.substring(index, index + 3)) < 256) res.add(index + 3);
        } else {
            res.add(index + 1);
            if (index + 2 <= len) res.add(index + 2);
        }
        return res;
    }

    public static void main(String[] args) {
        // ["17.216.25.41","17.216.254.1","172.16.25.41","172.16.254.1","172.162.5.41","172.162.54.1"]
        // [                              "172.16.25.41","172.16.254.1","172.162.5.41","172.162.54.1"]
        Solution93 solution93 = new Solution93();
        System.out.println(solution93.restoreIpAddresses("172162541")); // "172.162.5.41","172.162.54.1"]
    }
}
