package middle;

import java.util.Arrays;


// 这个题和专业级第二道透光玻璃一样的解法
// 通过预处理可以将O(n^3)的复杂度降低到O(n^2)
// 可以说是预处理也能说是动态规划。。。和透光玻璃一毛一样。。。
public class Leetcode764 {
    public static void main(String[] args) {
        System.out.println(orderOfLargestPlusSign(1,new int[][]{{0,0}}));
    }

    public int orderOfLargestPlusSign2(int n, int[][] mines) { //三叶和官方解法思路一致
        int[][] g = new int[n + 10][n + 10];
        for (int i = 1; i <= n; i++) Arrays.fill(g[i], 1);
        for (int[] info : mines) g[info[0] + 1][info[1] + 1] = 0;
        int[][] a = new int[n + 10][n + 10], b = new int[n + 10][n + 10], c = new int[n + 10][n + 10], d = new int[n + 10][n + 10];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (g[i][j] == 1) {
                    a[i][j] = a[i - 1][j] + 1;
                    b[i][j] = b[i][j - 1] + 1;
                }
                if (g[n + 1 - i][n + 1 - j] == 1) {
                    c[n + 1 - i][n + 1 - j] = c[n + 2 - i][n + 1 - j] + 1;
                    d[n + 1 - i][n + 1 - j] = d[n + 1 - i][n + 2 - j] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ans = Math.max(ans, Math.min(Math.min(a[i][j], b[i][j]), Math.min(c[i][j], d[i][j])));
            }
        }
        return ans;
    }

    public static int orderOfLargestPlusSign(int n, int[][] mines) { // 暴力解法，虽然通过了，但是复杂度达不到专业级要求
        int j = (n + 1) / 2; // 最大阶数
        int[][] graph = new int[n][n]; // 可以把0和1的含义交换，这样就不用特殊初始化graph了
        for (int i = 0; i < mines.length; i++) {
            graph[mines[i][0]][mines[i][1]] = 1;
        }
        int max = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int k = 0; k < graph[0].length; k++) {
                max = Math.max(max, getMaxJ(k, i, graph));
            }
        }
        return max;
    }

    public static int getMaxJ(int x, int y, int[][] graph) {
        int res = 0;
        if (graph[x][y] == 0) {
            res++;
        } else {
            return res;
        }
        int[][] c = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int[][] point = new int[][]{{x, y}, {x, y}, {x, y}, {x, y}};
        while (true) {
            for (int i = 0; i < point.length; i++) {
                point[i][0] += c[i][0];
                point[i][1] += c[i][1];
                if (point[i][0] < 0 || point[i][0] >= graph.length ||
                        point[i][1] < 0 || point[i][1] >= graph.length ||
                        graph[point[i][0]][point[i][1]] == 1) {
                    return res;
                }
            }
            res++;
        }
    }
}
