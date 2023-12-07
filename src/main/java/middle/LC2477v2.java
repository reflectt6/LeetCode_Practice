package middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LC2477v2 {
    List<Integer>[] list;
    int[][] roads;
    int fuelCost;
    int seats;

    public long minimumFuelCost(int[][] roads, int seats) {
        // init
        list = new List[roads.length + 1];
        for (int i = 0; i < roads.length + 1; i++) {
            list[i] = new ArrayList<>();
        }
        this.roads = roads;
        fuelCost = 0;
        this.seats = seats;
        // construction
        for (int i = 0; i < roads.length; i++) {
            int f = roads[i][0];
            int s = roads[i][1];
            list[f].add(s);
            list[s].add(f);
        }

//        diffusionScan(0, null);
        dfs(0, -1, seats);
        return fuelCost;
    }


    // 这玩意栈溢出，居然是因为previousCity这东西用了Integer。。
    int diffusionScan(int currentCity, Integer previousCity) {
        int num = previousCity == null ? 0 : 1;
        if (list[currentCity].size() == 0) {
            return num;
        }
        for (Integer city : list[currentCity]) {
            if (previousCity != null && city == previousCity) continue;
            num += diffusionScan(city, currentCity);
        }
        if (previousCity == null) return num;
        fuelCost += (num + (seats - 1)) / seats;
        return num;
    }

    int diffusionScan3(int currentCity, int previousCity) {
        int num = previousCity == -1 ? 0 : 1;
        if (list[currentCity].size() == 0) {
            return num;
        }
        for (Integer city : list[currentCity]) {
            if (previousCity != -1 && city == previousCity) continue;
            num += diffusionScan(city, currentCity);
        }
        if (previousCity == -1) return num;
        fuelCost += (num + (seats - 1)) / seats;
        return num;
    }

    int diffusionScan2(int currentCity, Integer previousCity) {
        int num = previousCity == null ? 0 : 1;
        if (list[currentCity].size() == 0) {
            return num;
        }
        for (Integer city : list[currentCity]) {
            if (city != previousCity) {
                num += diffusionScan(city, currentCity);
            }
        }
        if (previousCity == null) return num;
        fuelCost += (num + (seats - 1)) / seats;
        return num;
    }

    // 这为啥不溢出啊。。
    public int dfs(int cur, int pre, int seats) {
        int peopleSum = 1;
        for (int ne : list[cur]) {
            if (ne != pre) {
                int peopleCnt = dfs(ne, cur, seats);
                peopleSum += peopleCnt;
                fuelCost += (peopleCnt + seats - 1) / seats;
            }
        }
        return peopleSum;
    }

    public static void main(String[] args) {
        LC2477v2 lc2477 = new LC2477v2();
//        int[][] roads = new int[3][];
//        roads[0] = new int[]{0, 1};
//        roads[1] = new int[]{0, 2};
//        roads[2] = new int[]{0, 3};

//        int[][] roads = new int[6][];
//        roads[0] = new int[]{3, 1};
//        roads[1] = new int[]{3, 2};
//        roads[2] = new int[]{0, 1};
//        roads[3] = new int[]{0, 4};
//        roads[4] = new int[]{0, 5};
//        roads[5] = new int[]{6, 4};
//        System.out.println(lc2477.minimumFuelCost(roads, 2));
        System.out.println(lc2477.minimumFuelCost(new int[0][], 1));

    }
}
