package middle;

import java.util.HashSet;

public class Leetcode547 {
    public static void main(String[] args) {

    }

    static int[] p = new int[205];
    static int[] rank = new int[205];

    public static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    public static void union(int x, int y) {
        p[find(x)] = p[find(y)];
    }

    // 按秩合并优化的 union 函数
    public void union2(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                p[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                p[rootX] = rootY;
            } else {
                p[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    };

    public static int findCircleNum(int[][] isConnected) {
        int num = isConnected.length;
        for (int i = 0; i < num; i++) { // 连通性初始化
            p[i] = i;
            rank[i] = 1;
        }
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < num; i++) {
            set.add(find(i));
        }
        return set.size();
    }
}
