package hard.LC2258;

// 火灾模拟器
// 1、要计算每一秒火灾扩散情况，
// 2、要计算人到安全屋子的最短（有bug，不一定是最短）路径
// 3、逆向思维，从火焰包围安全屋向前逆推

// 1、计算火焰完全包围安全屋之前的所有grid，并存储起来
// 2、向前逆推，每推一步，检查是否

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class LC2258 {
    static Queue<int[]> queue = new ArrayDeque();
    static Queue<ArrayList<int[]>> pathQueue = new ArrayDeque();
    static ArrayList<int[][]> flames = new ArrayList();
    static int targetRow;
    static int targetCol;
    static boolean isFixed = false;

    public int maximumMinutes(int[][] grid) {
        targetRow = grid.length - 1;
        targetCol = grid[0].length - 1;
        isFixed = false;
        flames.clear();
        queue.clear();
        pathQueue.clear();

        // 先计算火焰扩散情况
        flames.add(grid);
        while (true) {
            int[][] nextFlame = flameDiffusion(flames.get(flames.size() - 1));
            if (nextFlame[targetRow][targetCol] == 1) {
                flames.add(nextFlame);
                break;
            }
            if (compareFlame(nextFlame, flames.get(flames.size() - 1))) {
                isFixed = true;
                break;
            }
            flames.add(nextFlame);
        }

        ArrayList path = new ArrayList();
        path.add(new int[]{0, 0});
        int profundity = 0;
        int res = maximumMinutesBreadthFirstSearch(0, 0, profundity, path);
        while (res != -1) {
            queue.clear();
            pathQueue.clear();
            if (profundity == flames.size() - 1) {
                if (isFixed) return 10*10*10*10*10*10*10*10*10;
                return profundity;
            }
            profundity++;
            res = maximumMinutesBreadthFirstSearch(0, 0, profundity, path);
        }
        return profundity - 1;
    }

    //
    public int maximumMinutesBreadthFirstSearch(int row, int col, int profundity, ArrayList<int[]> path) {
        // 0、递归结束
        if (row == targetRow && col == targetCol) {
            return profundity;
        }
        if (flames.size() - 1 == profundity) {
            if (isFixed) {
                profundity = profundity - 1;
            } else {
                return -1;
            }
        }

        // 1、计算下一秒火焰分布(已经提前计算)
        // 2、遍历当前移动的可能性（上下左右），可能的入队 使用队列实现广度优先搜索
        if (canMove(row - 1, col, profundity + 1, path)) {
            queue.offer(new int[]{row - 1, col, profundity + 1});
            ArrayList newPath = new ArrayList(path);
            newPath.add(new int[]{row - 1, col});
            pathQueue.offer(newPath);
        }
        if (canMove(row + 1, col, profundity + 1, path)) {
            queue.offer(new int[]{row + 1, col, profundity + 1});
            ArrayList newPath = new ArrayList(path);
            newPath.add(new int[]{row + 1, col});
            pathQueue.offer(newPath);
        }
        if (canMove(row, col - 1, profundity + 1, path)) {
            queue.offer(new int[]{row, col - 1, profundity + 1});
            ArrayList newPath = new ArrayList(path);
            newPath.add(new int[]{row, col - 1});
            pathQueue.offer(newPath);
        }
        if (canMove(row, col + 1, profundity + 1, path)) {
            queue.offer(new int[]{row, col + 1, profundity + 1});
            ArrayList newPath = new ArrayList(path);
            newPath.add(new int[]{row, col + 1});
            pathQueue.offer(newPath);
        }
        // 3、
        while (queue.size() > 0) {
            int[] cur = queue.poll();
            ArrayList<int[]> p = pathQueue.poll();
            int res = maximumMinutesBreadthFirstSearch(cur[0], cur[1], cur[2], p);
            if (res != -1) {
                return res;
            }
        }
        return -1;
    }

    public boolean canMove(int row, int col, int profundity, ArrayList<int[]> path) {
        for (int[] ints : path) {
            if (ints[0] == row && ints[1] == col) {
                return false;
            }
        }
        if (row == targetRow && col == targetCol) return true;
        if (row < 0 || col < 0 || row > targetRow || col > targetCol) {
            return false;
        }
        if (flames.get(profundity)[row][col] == 0) {
            return true;
        }
        return false;
    }

    public boolean compareFlame(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            for (int i1 = 0; i1 < a[0].length; i1++) {
                if (a[i][i1] != b[i][i1]) {
                    return false;
                }
            }
        }
        return true;
    }

    //  计算下一秒火焰扩散
    public int[][] flameDiffusion(int[][] frid) {
        int[][] newGrid = new int[targetRow + 1][targetCol + 1];
        for (int i = 0; i < frid.length; i++) {
            for (int i1 = 0; i1 < frid[i].length; i1++) {
                int n = frid[i][i1];
                newGrid[i][i1] = n;
            }
        }
        for (int i = 0; i < frid.length; i++) {
            for (int i1 = 0; i1 < frid[i].length; i1++) {
                int n = frid[i][i1];
                if (n == 1) {
                    render(newGrid, i - 1, i1);
                    render(newGrid, i + 1, i1);
                    render(newGrid, i, i1 - 1);
                    render(newGrid, i, i1 + 1);
                }
            }
        }
        return newGrid;
    }

    // 渲染火焰
    public void render(int[][] frid, int row, int col) {
        if (row < 0 || col < 0 || row > targetRow || col > targetCol) {
            return;
        }
        if (frid[row][col] != 2) frid[row][col] = 1;
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

        LC2258 lc2258 = new LC2258();
        int a = lc2258.maximumMinutes(frid);
        System.out.println(a);
    }
}
