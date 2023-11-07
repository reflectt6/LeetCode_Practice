package simple;

import java.util.ArrayList;
import java.util.List;

public class Leetcode1668 {
    public static void main(String[] args) {
        System.out.println(maxRepeating3("aaaac", "aa"));
    }

    public static int maxRepeating(String sequence, String word) {
        List<Integer> l = new ArrayList<>();
        int len = word.length();
        char start = word.charAt(0);
        int res = 0;
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == start) {
                l.add(i);
            }
        }
        for (int i : l) {
            int leave = sequence.length() - i;
            int tmp = 0;
            while (leave >= len && sequence.substring(i, i + len).equals(word)) {
                tmp++;
                i += len;
                leave -= len;
            }
            if (tmp > res) {
                res = tmp;
            }
        }
        return res;
    }

    public static int maxRepeating2(String sequence, String word) { // 看了题解 使用DP试一下
        int sl = sequence.length();
        int wl = word.length();
        int[] f = new int[sl];
        int ans = 0;
        for (int i = wl - 1; i < sl; i++) {
            if (sequence.substring(i - wl + 1, i + 1).equals(word)) {
                if ((i - wl) >= 0) {
                    f[i] = f[i - wl] + 1;
                } else {
                    f[i] = 1;
                }
                ans = Math.max(ans, f[i]);
            }
        }
        return ans;
    }

    public static int maxRepeating3(String ss, String pp) {
        int n = ss.length(), m = pp.length(), ans = 0;
        int[] f = new int[n + 10];

        String s = ss + pp;
        int P = 1313131, N = s.length();
        long[] h = new long[N + 10], p = new long[N + 10];
        p[0] = 1;
        for (int i = 1; i <= N; i++) {
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }
        long phash = h[N] - h[N - m] * p[m];

        for (int i = 1; i <= n; i++) {
            if (i - m < 0) continue;
            long cur = h[i] - h[i - m] * p[m];
            if (cur == phash) f[i] = f[i - m] + 1;
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
