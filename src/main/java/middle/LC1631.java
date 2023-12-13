package middle;

import utils.ArrayResolver;

public class LC1631 {
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] dp = new int[row][col];
        for (int i = 1; i < heights[0].length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], Math.abs(heights[0][i - 1] - heights[0][i]));
        }
        for (int i = 1; i < heights.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.abs(heights[i][0] - heights[i - 1][0]));
        }
        for (int i = 1; i < heights.length; i++) {
            for (int i1 = 1; i1 < heights[0].length; i1++) {
                int tmp = Math.max(Math.abs(heights[i][i1 - 1] - heights[i][i1]), dp[i][i1 - 1]);
                int tmp2 = Math.max(Math.abs(heights[i - 1][i1] - heights[i][i1]), dp[i - 1][i1]);
                dp[i][i1] = Math.min(tmp, tmp2);
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        LC1631 lc1631 = new LC1631();
        int i = lc1631.minimumEffortPath(ArrayResolver.resolveTwoDimensionalArray("[[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]"));
        System.out.println(i);
    }
}
