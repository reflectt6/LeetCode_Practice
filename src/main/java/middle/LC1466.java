package middle;

import utils.ArrayResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1466 {
    List<int[]>[] list;
    int sum;
    public int minReorder(int n, int[][] connections) {
        list = new List[n + 1];
        sum = 0;
//        Arrays.setAll(list, e -> new ArrayList<>());
        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < connections.length; i++) {
            int n1 = connections[i][0];
            int n2 = connections[i][1];
            list[n1].add(new int[]{n2, 1});// 1表示可以连通
            list[n2].add(new int[]{n1, 0});// 0表示不可以连通
        }
        dfs(0, -1);
        return sum;
    }

    public void dfs(int cur, int pre) {
        for (int[] ints : list[cur]) {
            if (ints[0] == pre) continue;
            if (ints[1] == 1) sum++;
            dfs(ints[0], cur);
        }
    }

    public static void main(String[] args) {
        LC1466 lc1466 = new LC1466();
        int a = lc1466.minReorder(6,
                ArrayResolver.resolveTwoDimensionalArray("[[0,1],[1,3],[2,3],[4,0],[4,5]]"));
        System.out.println(a);
    }
}
