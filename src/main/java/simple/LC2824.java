package simple;

import java.util.Arrays;
import java.util.List;

public class LC2824 {
    public int countPairs(List<Integer> nums, int target) {
        int[] quantity = new int[101];
        for (Integer num : nums) {
            quantity[num + 50] += 1;
        }
        for (int i = 0; i < quantity.length - 1; i++) {
            quantity[i + 1] += quantity[i];
        }
        int sum = 0;
        for (Integer num : nums) {
            int index = target - num - 1 + 50;
            int tmp = index < 0 ? 0 : index > 100 ? quantity[100] : quantity[index];
            if (num < target - num) tmp -= 1;
            sum += tmp;
        }
        return sum / 2;
    }

    public static void main(String[] args) {
        LC2824 lc2824 = new LC2824();
//        int a = lc2824.countPairs(Arrays.asList(-1, 1, 2, 3, 1), 2);
        int a = lc2824.countPairs(
                Arrays.asList(39, 8, 2, 26, 7, -30, -2, 14,
                        -17, 33, -21, -6, 20, 0, -3, 3, -32,
                        -15, -6, 34, -19, 17, 24, -50, 45, 32), 11);
        System.out.println(a);
    }
}
