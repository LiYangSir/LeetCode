import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution855 {
    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        System.out.println(examRoom.seat());
        System.out.println(examRoom.seat());
        System.out.println(examRoom.seat());
        System.out.println(examRoom.seat());
        examRoom.leave(4);
        System.out.println(examRoom.seat());
    }

}

class ExamRoom {

    private Map<Integer, int[]> start = new HashMap<>();
    private Map<Integer, int[]> end = new HashMap<>();
    private TreeSet<int[]> pq;
    int n;
    public ExamRoom(int n) {
        this.n = n;
        pq = new TreeSet<>((o1, o2) -> {
            int distA = distance(o1);
            int distB = distance(o2);
            if (distA == distB)
                return o2[0] - o1[0];
            return distA - distB;
        });
        addInternal(new int[]{-1, n});
    }

    public int seat() {
        int[] last = pq.last();
        int seat;
        int x = last[0];
        int y = last[1];
        if (x == -1) seat = 0;
        else if (y == n) seat = n - 1;
        else seat = (x + y) / 2;
        int[] left = new int[]{x, seat};
        int[] right = new int[]{seat, y};
        removeInternal(last);
        addInternal(left);
        addInternal(right);
        return seat;
    }

    public void leave(int p) {
        int[] right = start.get(p);
        int[] left = end.get(p);
        removeInternal(left);
        removeInternal(right);
        addInternal(new int[]{left[0], right[1]});
    }

    private void addInternal(int[] intv) {
        pq.add(intv);
        start.put(intv[0], intv);
        end.put(intv[1], intv);
    }

    private void removeInternal(int[] intv) {
        start.remove(intv[0]);
        end.remove(intv[1]);
        pq.remove(intv);
    }

    private int distance(int[] o) {
        int x = o[0];
        int y = o[1];
        if (x == -1) return y;
        if (y == n) return n - 1 - x;

        return (y - x) / 2;
    }
}
