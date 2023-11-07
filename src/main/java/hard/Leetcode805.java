package hard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode805 {
    public static void main(String[] args) {
//        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
//        int[] a = new int[]{4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 5};
        int[] a = new int[]{2,0,5,6,16,12,15,12,4};
//        int[] a = new int[]{6, 8,18,3,1};
//        int[] a = new int[]{1, 3};
        System.out.println(splitArraySameAverage(a));
    }

    public static boolean splitArraySameAverage(int[] nums) { // 暴力枚举 超时了。。 时间复杂度O(2^30)
        List<List<Integer>[]> boom = new ArrayList<>();
        // add first item;
        List<Integer>[] l1 = new List[]{new ArrayList(), new ArrayList()};
        List<Integer>[] l2 = new List[]{new ArrayList(), new ArrayList()};
        l1[0].add(nums[0]);
        l2[1].add(nums[0]);
        boom.add(l1);
        boom.add(l2);

        int len = nums.length;
        // memory boom.
        for (int i = 1; i < len; i++) {
            List<List<Integer>[]> boom2 = new ArrayList<>();
            for (List<Integer>[] cur : boom) {
                List<Integer> left1 = new ArrayList<>(cur[0]);
                List<Integer> left2 = new ArrayList<>();
                List<Integer> right1 = new ArrayList<>(cur[1]);
                List<Integer> right2 = new ArrayList<>();
                cur[0].stream().forEach(e -> left2.add(e));
                cur[1].stream().forEach(e -> right2.add(e));
                left1.add(nums[i]);
                right2.add(nums[i]);
                boom2.add(new List[]{left1, right1});
                boom2.add(new List[]{left2, right2});
            }
            boom = boom2;
        }
        for (List<Integer>[] cur : boom) {
            if (cur[0].size() == 0 || cur[1].size() == 0) continue;
            double l = cur[0].stream().mapToInt(e -> e).sum();
            double r = cur[1].stream().mapToInt(e -> e).sum();
            Double rl = l / cur[0].size();
            Double rr = r / cur[1].size();
            if (rl.compareTo(rr) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean splitArraySameAverage2(int[] nums) { // 折半查找
        if (nums.length == 1) {
            return false;
        }
        int n = nums.length, m = n / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * n - sum;
        }

        Set<Integer> left = new HashSet<Integer>();
        for (int i = 1; i < (1 << m); i++) {
            int tot = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    tot += nums[j];
                }
            }
            if (tot == 0) {
                return true;
            }
            left.add(tot);
        }
        int rsum = 0;
        for (int i = m; i < n; i++) {
            rsum += nums[i];
        }
        for (int i = 1; i < (1 << (n - m)); i++) {
            int tot = 0;
            for (int j = m; j < n; j++) {
                if ((i & (1 << (j - m))) != 0) {
                    tot += nums[j];
                }
            }
            if (tot == 0 || (rsum != tot && left.contains(-tot))) {
                return true;
            }
        }
        return false;
    }
}
