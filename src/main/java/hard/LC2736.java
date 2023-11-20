package hard;

import java.util.Arrays;
import java.util.HashSet;

public class LC2736 {

    // 先整个简单的，应该是要超时的 // 果然超时了
    public int[] maximumSumQueries0(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[] sumArr = new int[n];
        for (int i = 0; i < nums1.length; i++) {
            sumArr[i] = nums1[i] + nums2[i];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] np1 = new int[n];
            int r = -1;
            for (int j = 0; j < nums1.length; j++) {
                if (nums1[j] >= queries[i][0]) {
                    np1[j] = 1;
                }
            }
            for (int i1 = 0; i1 < np1.length; i1++) {
                if (np1[i1] != 0 && nums2[i1] >= queries[i][1]) {
                    if (r == -1) r = sumArr[i1];
                    else {
                        r = Math.max(r, sumArr[i1]);
                    }
                }
            }
            res[i] = r;
        }
        return res;
    }

    // 优化一下
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[] sumArr = new int[n];
        for (int i = 0; i < nums1.length; i++) {
            sumArr[i] = nums1[i] + nums2[i];
        }

        int[][] n1plus = new int[n][2];
        for (int i = 0; i < nums1.length; i++) {
            n1plus[i][0] = nums1[i];
            n1plus[i][1] = i;
        }
        Arrays.sort(n1plus, (a, b) -> a[0] - b[0]);
//        sortPlus(n1plus);
//        quickSort(n1plus, 0, n - 1);
        int[][] n2plus = new int[n][2];
        for (int i = 0; i < nums2.length; i++) {
            n2plus[i][0] = nums2[i];
            n2plus[i][1] = i;
        }
        Arrays.sort(n2plus, (a, b) -> a[0] - b[0]);
//        quickSort(n2plus, 0, n - 1);
//        sortPlus(n2plus);

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int i1 = binarySearch(n1plus, queries[i][0]);
            int i2 = binarySearch(n2plus, queries[i][1]);
            HashSet<Integer> s1 = new HashSet();
            for (int i3 = i1; i3 < n1plus.length && i3 > -1; i3++) {
                s1.add(n1plus[i3][1]);
            }
            res[i] = -1;
            for (int i3 = i2; i3 < n2plus.length && i3 > -1; i3++) {
                if (s1.contains(n2plus[i3][1])) res[i] = Math.max(res[i], sumArr[n2plus[i3][1]]);
            }
        }
        return res;
    }

    void sortPlus(int[][] plus) {
        // 冒泡排序
        for (int i = 0; i < plus.length - 1; i++) {
            for (int i1 = 0; i1 < plus.length - 1 - i; i1++) {
                if (plus[i1][0] > plus[i1 + 1][0]) {
                    int[] tmp = plus[i1];
                    plus[i1] = plus[i1 + 1];
                    plus[i1 + 1] = tmp;
                }
            }
        }
    }

    void quickSort(int[][] plus, int low, int high) {
        if (low < 0 || high < 0 || low >= plus.length || high >= plus.length || low >= high) return;
        int[] pivot = plus[0];
        int l = low;
        int r = high;
        while (low < high) {
            while (low < high && plus[high][0] >= pivot[0]) high--;
            plus[low] = plus[high];
            while (low < high && plus[low][0] <= pivot[0]) low++;
            plus[high] = plus[low];
        }
        plus[high] = pivot;
        quickSort(plus, l, high - 1);
        quickSort(plus, high + 1, r);
    }

    int binarySearch(int[][] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid][0] >= target) r = mid;
            else l = mid + 1;
        }
        if (nums[l][0] >= target) return l;
        else return -1;
    }

    public static void main(String[] args) {
        LC2736 lc2736 = new LC2736();
        int[] nums1 = new int[]{4, 3, 1, 2};
        int[] nums2 = new int[]{2, 4, 9, 5};
        int[][] queries = new int[3][];
        queries[0] = new int[]{4, 1};
        queries[1] = new int[]{1, 3};
        queries[2] = new int[]{2, 5};
        int[] r = lc2736.maximumSumQueries(nums1, nums2, queries);
        for (int i : r) {
            System.out.println(i);
        }
    }
}
