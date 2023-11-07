package hard;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Leetcode864 {
    public static void main(String[] args) {
//        System.out.println(shortestPathAllKeys(new String[]{"@.a.#", "###.#", "b.A.B"}));
//        System.out.println(shortestPathAllKeys(new String[]{"@..aA","..B#.","....b"}));
//        long l = System.currentTimeMillis();
//        System.out.println(shortestPathAllKeys(new String[]{"..#.#.#.##",".#.#....#.","..#.......",".......#..","......#...","#..###a...","..##....#A",".....#.#.#",".#......##","#..@.##.#."}));
//        System.out.println(System.currentTimeMillis() - l);
//        System.out.println(shortestPathAllKeys(new String[]{"@Aa"}));

        System.out.println(0x3f3f3f3f);
    }

    static int row;
    static int column;
    static int cnt;

    public static int shortestPathAllKeys(String[] g) {
        // dfs。。我比较熟悉
        // 超时了。。。这种图的搜索还是bfs更好。。
        // 但是我很奇怪bfs 要怎么搞。。
        row = g.length; // 行数
        column = g[0].length(); // 列数
        cnt = 0; // number of keys
        char[][] gCopy = new char[row][];
        int x = -1;
        int y = -1;
        for (int i = 0; i < row; i++) { // init
            gCopy[i] = g[i].toCharArray();
            for (int j = 0; j < gCopy[i].length; j++) {
                if (gCopy[i][j] <= 'f' && gCopy[i][j] >= 'a') {
                    cnt++;
                } else if (gCopy[i][j] == '@') {
                    y = i;
                    x = j;
                    gCopy[i][j] = '.';
                }
            }
        }
        return dfs(x, y, new HashSet<>(), gCopy, new HashSet(), -1);
    }

    public static int dfs(int x, int y, Set<String> path, char[][] grid, Set<Character> key, int step) {
        // check
        if (x < 0 || x >= column || y < 0 || y >= row || path.contains(x + "," + y)) { // unreachable
            return -1;
        }
        char cur = grid[y][x];
        char[][] gridCopy = new char[row][];
        for (int i = 0; i < row; i++) {
            gridCopy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        Set<String> pathCopy = new HashSet<>(path);
        Set<Character> keyCopy = new HashSet<>(key);
        if (cur <= 'f' && cur >= 'a') { // key
            step++;
            if (keyCopy.size() == cnt - 1) return step;
            keyCopy.add(cur); // pick up
            gridCopy[y][x] = '.'; // cover
            pathCopy.clear(); // clean history path.
            pathCopy.add(x + "," + y);
        } else if (cur <= 'F' && cur >= 'A') { // lock
            if (keyCopy.contains((char) (cur + 32))) { // lock success
                step++;
                gridCopy[y][x] = '.';
                pathCopy.add(x + "," + y);
            } else { // lock failed, return -1;
                return -1;
            }
        } else if (cur == '#') { // wall
            return -1;
        } else if (cur == '.') { // empty room
            step++;
            pathCopy.add(x + "," + y);
        }
        int[][] direction = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int min = Integer.MAX_VALUE;
        boolean hasRes = false;
        for (int i = 0; i < 4; i++) {
            int res = dfs(x + direction[i][0], y + direction[i][1], pathCopy, gridCopy, keyCopy, step);
            if (res != -1) {
                hasRes = true;
                min = Math.min(res, min);
            }
        }
        return hasRes ? min : -1;
    }


    // 状态压缩（使用位运算化简计算）和bfs 三叶题解。。
    static int N = 35, K = 10, INF = 0x3f3f3f3f;
    static int[][][] dist = new int[N][N][1 << K]; // (x,y,状态压缩) 类似三元组
    static int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}}; // 方向
    public int shortestPathAllKeys2(String[] g) {
        int n = g.length, m = g[0].length(), cnt = 0;
        Deque<int[]> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) { // init
            for (int j = 0; j < m; j++) {
                Arrays.fill(dist[i][j], INF); // 初始化状态
                char c = g[i].charAt(j);
                if (c == '@') {
                    d.addLast(new int[]{i, j, 0}); // 找到起点
                    dist[i][j][0] = 0;
                } else if (c >= 'a' && c <= 'z') cnt++; // 记录钥匙数量
            }
        }
        while (!d.isEmpty()) {
            int[] info = d.pollFirst();
            int x = info[0], y = info[1], cur = info[2], step = dist[x][y][cur];
            for (int[] di : dirs) {
                int nx = x + di[0], ny = y + di[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                char c = g[nx].charAt(ny);
                if (c == '#') continue;
                if ((c >= 'A' && c <= 'Z') && (cur >> (c - 'A') & 1) == 0) continue;
                int ncur = cur;
                if (c >= 'a' && c <= 'z') ncur |= 1 << (c - 'a');
                if (ncur == (1 << cnt) - 1) return step + 1;
                if (step + 1 >= dist[nx][ny][ncur]) continue;
                dist[nx][ny][ncur] = step + 1;
                d.addLast(new int[]{nx, ny, ncur});
            }
        }
        return -1;
    }
}
