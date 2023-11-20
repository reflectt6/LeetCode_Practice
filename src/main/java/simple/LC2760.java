package simple;

import java.util.Arrays;

public class LC2760 {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int max = 0;
        int tmp = 0;
        boolean isEven = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > threshold) {
                max = Math.max(max, tmp);
                tmp = 0;
                isEven = true;
            } else if (nums[i] % 2 == 0 && isEven) {
                tmp++;
                isEven = false;
            } else if (nums[i] % 2 != 0 && !isEven) {
                tmp++;
                isEven = true;
            } else {
                if (tmp != 0) i--;
                max = Math.max(max, tmp);
                tmp = 0;
                isEven = true;
            }
        }
        return Math.max(max, tmp);
    }

    public static void main(String[] args) {
        LC2760 lc2760 = new LC2760();
        int[] a = new int[]{2,5,4,3};
        int res = lc2760.longestAlternatingSubarray2(a,5);
        System.out.println(res);
    }

    // 较优解，可以减少边界条件，减少出错
    public int longestAlternatingSubarray2(int[] nums, int threshold) {
        int n = nums.length;
        int ans = 0, i = 0;
        while (i < n) {
            if (nums[i] > threshold || nums[i] % 2 != 0) {
                i++; // 直接跳过
                continue;
            }
            int start = i; // 记录这一组的开始位置
            i++; // 开始位置已经满足要求，从下一个位置开始判断
            while (i < n && nums[i] <= threshold && nums[i] % 2 != nums[i - 1] % 2) {
                i++;
            }
            // 从 start 到 i-1 是满足题目要求的子数组
            ans = Math.max(ans, i - start);
        }
        return ans;
    }
}
