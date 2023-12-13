package middle;

import java.util.Arrays;
import java.util.Comparator;

public class LC2008 {
    int[][] rides;
    int n;

    // 要用动态规划做，还得练
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides);
        this.rides = rides;
        this.n = n;
        return 1;
    }

}