package middle;

public class LC2304 {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[] minPath = new int[n];
        for (int i = 0; i < grid[m - 1].length; i++) {
            minPath[i] = grid[m - 1][i];
        }
        for (int i = m - 2; i >= 0; i--) {
            int[] tmp = new int[n];
            for (int i1 = 0; i1 < n; i1++) {
                int value = grid[i][i1];
                int min = Integer.MAX_VALUE;
                for (int i2 = 0; i2 < n; i2++) {
                    min = Math.min(moveCost[value][i2] + minPath[i2], min);
                }
                tmp[i1] = min + value;
            }
            minPath = tmp;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(minPath[i], min);
        }
        return min;
    }

    public static void main(String[] args) {
        LC2304 lc2304 = new LC2304();
        int[][] grid = new int[3][];
        grid[0] = new int[]{5, 3};
        grid[1] = new int[]{4, 0};
        grid[2] = new int[]{2, 1};
        int[][] moveCost = new int[6][];
        moveCost[0] = new int[]{9, 8};
        moveCost[1] = new int[]{1, 5};
        moveCost[2] = new int[]{10, 12};
        moveCost[3] = new int[]{18, 6};
        moveCost[4] = new int[]{2, 4};
        moveCost[5] = new int[]{14, 3};
        int r = lc2304.minPathCost(grid, moveCost);
        System.out.println(r);
    }
}
