package middle;

import java.util.Arrays;

// 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，
// 其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
// 同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
// 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
public class LC2300 {

    // 能想到的最优写法，还是超时
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            long threshold = (success + (spells[i] -1)) / spells[i];
            for (int i1 = 0; i1 < m; i1++) {
                if (potions[i1] >= threshold) {
                    cnt++;
                }
            }
            res[i] = cnt;
        }
        return res;
    }

    // 看题解，先排序。。醉了 难道排序比直接遍历要省时间？？？
    // 难点二：手写二分法，别小看它。。
    public int[] successfulPairs2(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] res = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            long threshold = (success + (spells[i] -1)) / spells[i];

            int l = 0, r = m - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (potions[mid] >= threshold) r = mid;
                else l = mid + 1;
            }
            if (potions[r] >= threshold) res[i] = m - r;
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] spells = new int[]{5,1,3};
//        int[] potions = new int[]{1,2,3,4,5};
//        int r[] = new LC2300().successfulPairs2(spells, potions, 7);
//        for (int i : r) {
//            System.out.println(i);
//        }

//        int[] a = new int[]{1,3,3,4,9,10};
//        int target = 5;
//        int l = 0, r = a.length - 1;
//        while (l < r) {
//            int mid = l + r >> 1;
//            // 条件中带等号，则最终找到的是最左侧的值
//            if (a[mid] >= target) r = mid;
//            else l = mid + 1;
//        }
//        if (a[r] == target) {
//            System.out.println("找到了最左侧，下标为" + r);
//        } else {
//            System.out.println("没找到，坐标为" + r);
//        }

//        int[] a = new int[]{1,3,3,4,9,10};
//        int target = 0;
        int[] a = new int[]{1,3};
        int target = 2;
        int l = 0, r = a.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
//            if (a[mid] <= target) l = mid;
//            else r = mid - 1;
            if (a[mid] > target) r = mid - 1;
            else l = mid;
        }
        if (a[l] == target) {
            System.out.println("找到了最右侧，下标为" + l);
        } else {
            System.out.println("没找到，下标为" + l);
        }
    }
}
