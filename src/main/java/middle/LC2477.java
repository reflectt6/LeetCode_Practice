package middle;

import java.util.HashMap;
import java.util.HashSet;

public class LC2477 {
    HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    int[][] roads;
    int fuelCost;
    int seats;

    public long minimumFuelCost(int[][] roads, int seats) {
        // init
        map = new HashMap<>();
        this.roads = roads;
        fuelCost = 0;
        this.seats = seats;
        // construction
        for (int i = 0; i < roads.length; i++) {
            int f = roads[i][0];
            int s = roads[i][1];
            if (map.containsKey(f)) {
                map.get(f).add(s);
            } else {
                HashSet<Integer> set = new HashSet<>();
                set.add(s);
                map.put(f, set);
            }
            if (map.containsKey(s)) {
                map.get(s).add(f);
            } else {
                HashSet<Integer> set = new HashSet<>();
                set.add(f);
                map.put(s, set);
            }
        }

        diffusionScan(0, null);
        return fuelCost;
    }

    // 栈溢出了。。。
    int diffusionScan(int currentCity, Integer previousCity) {
        int num = previousCity == null ? 0 : 1;
        if (!map.containsKey(currentCity)) {
            return num;
        }
        for (Integer city : map.get(currentCity)) {
            if (previousCity != null && city == previousCity) continue;
            num += diffusionScan(city, currentCity);
        }
        if (previousCity == null) return num;
        fuelCost += (num + (seats - 1)) / seats;
        return num;
    }

    int diffusionScan2(int currentCity, int previousCity) {
        int num = previousCity == -1 ? 0 : 1;
        if (!map.containsKey(currentCity)) {
            return num;
        }
        for (Integer city : map.get(currentCity)) {
            if (previousCity != -1 && city == previousCity) continue;
            num += diffusionScan(city, currentCity);
        }
        if (previousCity == -1) return num;
        fuelCost += (num + (seats - 1)) / seats;
        return num;
    }

    public static void main(String[] args) {
        LC2477 lc2477 = new LC2477();
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
