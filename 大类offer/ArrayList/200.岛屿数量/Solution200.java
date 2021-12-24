/**
 * @author LiYangSir
 * @date 2021/9/26
 */
public class Solution200 {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        UnionFind uf = new UnionFind(m * n);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    if (i > 0 && grid[i - 1][j] == '1') {
                        uf.unionElement(i * n + j, (i - 1) * n + j);
                    }
                    if (j > 0 && grid[i][j - 1] == '1') {
                        uf.unionElement(i * n + j, i * n + (j - 1));
                    }
                } else {
                    result++;
                }
            }
        }
        return uf.getDependent() - result;
    }
    class UnionFind{
        private int[] parent;
        private int[] rank;

        public UnionFind(int capacity) {
            parent = new int[capacity];
            rank = new int[capacity];
            for (int i = 0; i < capacity; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int getParent(int p) {
            while (parent[p] != p) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean isConnect(int p, int q) {
            return getParent(p) == getParent(q);
        }

        public void unionElement(int p, int q) {
            int pRoot = getParent(p);
            int qRoot = getParent(q);
            if (pRoot == qRoot) return;
            if (rank[pRoot] > rank[qRoot]) {
                parent[qRoot] = pRoot;
            } else if (rank[pRoot] < rank[qRoot]) {
                parent[pRoot] = qRoot;
            } else {
                parent[pRoot] = qRoot;
                rank[qRoot]++;
            }
        }

        public int getDependent() {
            int result = 0;
            for (int i = 0; i < rank.length; i++) {
                if (parent[i] == i) {
                    result++;
                }
            }
            return result;
        }
    }
}
