import java.util.List;

/**
 * @author LiYangSir
 * @date 2021/6/6
 */

public class Solution721 {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        return null;
    }
    public static void main(String[] args) {

    }
}

class UnionFind{

    private int[] parent;
    private int[] rank;
    private int count = 0;

    public UnionFind(int capacity) {
        parent = new int[capacity];
        rank = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int getSize() {
        return count;
    }

    public int findParent(int p){
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean isConnect(int p, int q){
        return findParent(p) == findParent(q);
    }

    public void unionElement(int p, int q){
        int pRoot = findParent(p);
        int qRoot = findParent(q);
        if (pRoot == qRoot) return;
        count++;
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot]++;
        }
    }
    public int getDependent(){
        int res = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i) {
                res++;
            }
        }
        return res;
    }
}
