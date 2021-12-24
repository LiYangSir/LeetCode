import java.util.*;

/**
 * @author LiYangSir
 * @date 2021/12/15
 */
public class Solution333 {
    static int C = 0;
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int N = reader.nextInt();
        C = reader.nextInt();
        int K1 = reader.nextInt();
        int K2 = reader.nextInt();
        int startPoint = reader.nextInt();
        int endPoint = reader.nextInt();
        int m = reader.nextInt();
        Map<Integer, List<int[]>> data = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int key1 = reader.nextInt();
            int key2 = reader.nextInt();
            int dis = reader.nextInt();
            data.putIfAbsent(key1, new ArrayList<>());
            data.putIfAbsent(key2, new ArrayList<>());
            List<int[]> list1 = data.get(key1);
            List<int[]> list2 = data.get(key2);
            list1.add(new int[]{key2, dis});
            list2.add(new int[]{key1, dis});
        }
        int numCharge = reader.nextInt();
        List<Integer> charge = new ArrayList<>();
        for (int i = 0; i < numCharge; i++) {
            charge.add(reader.nextInt());
        }
        Deque<Integer> path = new ArrayDeque<>();
        path.addLast(startPoint);
        List<Integer> time = new ArrayList<>();
        Map<Integer, Integer> chargePoints = new HashMap<>();
        boolean[] used = new boolean[N];
        compute(C, K1, K2, startPoint, endPoint, data, charge, time, used, path, 0, chargePoints);
        System.out.println(time.stream().mapToInt(value -> value).min().getAsInt());
        reader.close();
    }

    private static void compute(int c, int k1, int k2, int curPoint, int endPoint, Map<Integer, List<int[]>> data, List<Integer> charge, List<Integer> time, boolean[] used, Deque<Integer> path, int i, Map<Integer, Integer> chargePoints) {
        if (!path.isEmpty() && path.peekLast() == endPoint) {
            time.add(i);
            return;
        }
        List<int[]> ints = data.get(curPoint);
        if (ints.isEmpty()) return;
        for (int[] point : ints) {
            if (used[point[0]]) continue;
            if (charge.contains(curPoint)) {
                chargePoints.put(curPoint, c);
            }
            if (c < point[1] * k1){
                if (chargePoints.isEmpty()) break;
                Integer integer = chargePoints.get(1);
                i += (C - integer) * k2;
                c += C - integer;
                chargePoints.remove(1);
            }
            path.addLast(point[0]);
            used[curPoint] = true;
            compute(c - point[1] * k1, k1, k2, point[0], endPoint, data, charge, time, used, path, i + point[1], chargePoints);
            used[curPoint] = false;
            path.removeLast();
        }
    }
}
/*
3 5 2 2 0 2
3 0 1 2 0 2 3 1 2 2
1 1
 */