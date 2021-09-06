import java.util.*;

public class SolutionPdd {
    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("1", "2", "3");
        List<String> list2 = Arrays.asList("x", "y");
        List<String> list3 = Arrays.asList("z", "c");
        List<List<String>> data = new ArrayList<>();
        data.add(list1);
        data.add(list2);
        data.add(list3);
        System.out.println(compute(data));

    }

    public static List<String> compute(List<List<String>> data){
        Deque<String> path = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        dfs(data, path, result, 0);
        return result;
    }

    /**
     * 回溯算法的核心 for循环寻找每一层的项 第二层该找谁了
     * for循环是遍历 树的每一行
     * @param data
     * @param path
     * @param result
     * @param index
     */
    private static void dfs(List<List<String>> data, Deque<String> path, List<String> result, int index) {
        if (path.size() == data.size()) {
            result.add(String.join("", path));
            return;
        }
        List<String> sub = data.get(index);
        for (int i = 0; i < sub.size(); i++) {
            path.addLast(sub.get(i));
            dfs(data, path, result, index + 1);
            path.removeLast();
        }
    }
}
