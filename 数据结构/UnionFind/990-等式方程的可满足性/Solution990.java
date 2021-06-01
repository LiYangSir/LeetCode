import java.util.Arrays;

public class Solution990 {
    public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                uf.unionElements(a - 'a', b - 'a');
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                if (uf.isConnected(a - 'a', b - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
    class UF {
        private int[] rank;
        private int[] parent;

        private int count;

        public UF(int count) {
            this.count = count;
            this.rank = new int[count];
            this.parent = new int[count];
            for (int i = 0; i < count; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public int getSize(){
            return this.count;
        }

        public boolean isConnected(int p, int q){
            return findParent(p) == findParent(q);
        }

        private int findParent(int id) {
            while (parent[id] != id) {
                parent[id] = parent[parent[id]];
                id = parent[id];
            }
            return id;
        }

        public void unionElements(int p, int q) {
            int pRoot = findParent(p);
            int qRoot = findParent(q);
            if (isConnected(pRoot, qRoot)) return;
            if (rank[pRoot] < rank[qRoot])
                parent[pRoot] = qRoot;
            else if (rank[pRoot] > rank[qRoot])
                parent[qRoot] = pRoot;
            else{
                parent[pRoot] = qRoot;
                rank[qRoot] ++;
            }

        }

    }
}



