/**
 * @author LiYangSir
 * @date 2021/6/6
 */
public class Solution1319 {
    public int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        int m = connections.length;
        if (m < n - 1) return -1;
        for (int i = 0; i < m; i++) {
            uf.unionElement(connections[i][0], connections[i][1]);
        }
        return uf.getDependent() - 1;
    }

    public static void main(String[] args) {
        Solution1319 solution1319 = new Solution1319();
        solution1319.makeConnected(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}});
    }
}
