package middle;

import java.util.Arrays;

public class Leetcode1620 {
    public static void main(String[] args) {
        int[][] towers = new int[][]{{1,2,5},{2,1,7},{3,1,9}};
        int[] res = bestCoordinate(towers, 2);
        System.out.println(res[0] + "," + res[1]);
    }

    public static int[] bestCoordinate(int[][] towers, int radius) {
        int max = 0;
        int[] r = new int[2];
        for (int i = 0; i < towers.length; i++) {
            int x = towers[i][0];
            int y = towers[i][1];
            int q = towers[i][2];
            double res = 0;
            for (int j = 0; j < towers.length; j++) {
                int cx = towers[j][0];
                int cy = towers[j][1];
                double d = Math.sqrt(Math.pow(cx - x, 2) + Math.pow(cy - y, 2));
                if (radius > d) {
                    res += q / (1 + d);
                }
            }
            int re = (int) res;
            if (max < re) {
                r = new int[]{x, y};
                max = re;
            } else if (max == re) {
                if (x < r[0] || (x == r[0] && y < r[0])) {
                    r = new int[]{x, y};
                    max = re;
                }
            }
        }
        return r;
    }
}
