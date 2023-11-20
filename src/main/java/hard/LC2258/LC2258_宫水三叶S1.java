package hard.LC2258;

// 二分 + BFS

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

public class LC2258_宫水三叶S1 {
    int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
    int n, m;
    boolean ok;
    int[][] g, fg, pg;
    public int maximumMinutes(int[][] grid) {
        g = grid;
        n = g.length; m = g[0].length;
        fg = new int[n][m]; pg = new int[n][m];
        if (!check(0)) return -1;
        int l = 0, r = n * m;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        return r == m * n ? (int)1e9 : r;
    }
    boolean check(int t) {
        ok = false;
        Deque<int[]> frie = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                fg[i][j] = pg[i][j] = 0;
                if (g[i][j] == 1) {
                    fg[i][j] = 1;
                    frie.addLast(new int[]{i, j});
                }
            }
        }
        while(t-- > 0) update(frie, true, 0);  // 先执行 t 秒的火势蔓延
        if (fg[0][0] != 0) return false;
        Deque<int[]> people = new ArrayDeque<>();
        pg[0][0] = 1;
        people.addLast(new int[]{0, 0});
        while (!people.isEmpty()) {
            // 先火后人, 同步进行
            update(frie, true, 1);
            update(people, false, 1);
            if (ok) return true;
        }
        return false;
    }
    void update(Deque<int[]> deque, boolean isFire, int offset) {
        int sz = deque.size();
        while (sz-- > 0) {
            int[] info = deque.pollFirst();
            int x = info[0], y = info[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (g[nx][ny] == 2) continue;
                if (isFire) {
                    if (fg[nx][ny] != 0) continue;
                    fg[nx][ny] = fg[x][y] + offset;
                } else {
                    if (nx == n - 1 && ny == m - 1 && (fg[nx][ny] == 0 || fg[nx][ny] == pg[x][y] + offset)) ok = true;  // 火尚未到达 或 同时到达
                    if (fg[nx][ny] != 0 || pg[nx][ny] != 0) continue;
                    pg[nx][ny] = pg[x][y] + offset;
                }
                deque.addLast(new int[]{nx, ny});
            }
        }
    }

    public void printFlame(int[][] grid) {
        for (int[] ints : grid) {
            for (int anInt : ints) {
                System.out.print(anInt);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println("------------");
    }

    public static void main(String[] args) {
        int[][] frid = new int[5][];
        frid[0] = new int[]{0, 2, 0, 0, 0, 0, 0};
        frid[1] = new int[]{0, 0, 0, 2, 2, 1, 0};
        frid[2] = new int[]{0, 2, 0, 0, 1, 2, 0};
        frid[3] = new int[]{0, 0, 2, 2, 2, 0, 2};
        frid[4] = new int[]{0, 0, 0, 0, 0, 0, 0};

//        int[][] frid = new int[3][];
//        frid[0] = new int[]{0, 0, 0, 0};
//        frid[1] = new int[]{0, 1, 2, 0};
//        frid[2] = new int[]{0, 2, 0, 0};

//        int[][] frid = new int[5][];
//        frid[0] = new int[]{0,2,0,0,1};
//        frid[1] = new int[]{0,2,0,2,2};
//        frid[2] = new int[]{0,2,0,0,0};
//        frid[3] = new int[]{0,0,2,2,0};
//        frid[4] = new int[]{0,0,0,0,0};

        LC2258_宫水三叶S1 lc2258 = new LC2258_宫水三叶S1();
        int a = lc2258.maximumMinutes(frid);
        System.out.println(a);
    }
}
