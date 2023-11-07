package hard;

import java.util.*;

public class Leetcode862 {
    public static void main(String[] args) {
//        int[] n = new int[]{1};
        int[] n = new int[]{17, 85, 93, -45, -21};
//        int[] n = new int[]{-28, 81, -20, 28, -29};
        System.out.println(shortestSubarray6(n, 150));
    }

    // 前缀和 O(n^2)
    public static int shortestSubarray(int[] nums, int k) { // 改了两个低级错误之后，发现超时了，用例通过 89/97
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        int min = -1;
        out:
        for (int i = len - 1; i >= 0; i--) {
            if (sum[i] >= k) {
                if (min < 0) {
                    min = i + 1;
                } else {
                    min = Math.min(min, i + 1);
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                int t = sum[i] - sum[j];
                if (t >= k) {
                    if (min < 0) {
                        min = i - j;
                    } else {
                        min = Math.min(min, i - j);
                    }
                    continue out;
                }
            }
        }
        return min;
    }

    public static int shortestSubarray2(int[] nums, int k) {
        // 优化了一些if判断，但是还不行，估计要吧O(n^2)优化为O(n)才行
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        int min = Integer.MAX_VALUE;
        out:
        for (int i = len - 1; i >= 0; i--) {
            if (sum[i] >= k) {
                min = Math.min(min, i + 1);
            }
            for (int j = i - 1; j >= 0; j--) {
                int t = sum[i] - sum[j];
                if (t >= k) {
                    min = Math.min(min, i - j);
                    continue out;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int shortestSubarray3(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        int min = Integer.MAX_VALUE;
        out:
        for (int i = 0; i < len; i++) {
            if (min == 1) {
                return 1;
            }
            if (sum[i] >= k) {
                min = Math.min(min, i + 1);
            }
            int target = sum[i] + k;
            for (int j = i + 1; j < len; j++) {
                if (sum[j] >= target) {
                    min = Math.min(min, j - i);
                    continue out;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int shortestSubarray4(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        int min = Integer.MAX_VALUE;
        out:
        for (int i = 0; i < len; i++) {
            if (min == 1) {
                return 1;
            }
            if (sum[i] >= k) {
                min = Math.min(min, i + 1);
            }
            int target = sum[i] + k;
            for (int j = i + 1; j < len; j++) {
                if (sum[j] >= target) {
                    min = Math.min(min, j - i);
                    continue out;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // 学习灵神解法
    public static int shortestSubarray6(int[] nums, int k) {
        int n = nums.length, ans = n + 1;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i)
            s[i + 1] = s[i] + nums[i]; // 计算前缀和
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i <= n; ++i) {
            long curS = s[i];
            while (!q.isEmpty() && curS - s[q.peekFirst()] >= k)
                ans = Math.min(ans, i - q.pollFirst()); // 优化一
            while (!q.isEmpty() && s[q.peekLast()] >= curS)
                q.pollLast(); // 优化二
            q.addLast(i);
        }
        return ans > n ? -1 : ans;
    }
//    作者：endlesscheng
//    链接：https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/solution/liang-zhang-tu-miao-dong-dan-diao-dui-li-9fvh/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    // 学习叶总解法
    static int N = 200010; // 离散化到正数需要的？
    static int[] tr = new int[N], sum = new int[N];
    static int n, m, ans;

    static int lowbit(int x) {
        return x & -x;
    }

    static void update(int val, int loc) {
        for (int i = val; i < m; i += lowbit(i)) tr[i] = Math.max(tr[i], loc);
    }

    static int query(int x) {
        int ans = -1;
        for (int i = x; i > 0; i -= lowbit(i)) ans = Math.max(ans, tr[i]);
        return ans;
    }

    static int getIdx(List<Long> list, long x) {
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (list.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return r + 1;
    }

    static public int shortestSubarray5(int[] nums, int k) {
        n = nums.length;
        m = 2 * n + 10;
        ans = n + 10;
        Arrays.fill(tr, -1);
        long[] temp = new long[m];
        List<Long> list = new ArrayList<>();
        list.add(0L);
        for (int i = 1; i <= 2 * n + 1; i++) {
            if (i <= n) temp[i] = temp[i - 1] + nums[i - 1];
            else temp[i] = temp[i - (n + 1)] + k;
            list.add(temp[i]);
        }
        Collections.sort(list);
        for (int i = 0; i <= 2 * n + 1; i++) sum[i] = getIdx(list, temp[i]);
        update(sum[n + 1], 0);
        for (int i = 1; i <= n; i++) {
            int j = query(sum[i]);
            if (j != -1) ans = Math.min(ans, i - j);
            update(sum[n + 1 + i], i);
        }
        return ans == n + 10 ? -1 : ans;
    }
}
