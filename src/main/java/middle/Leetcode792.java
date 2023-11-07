package middle;

import java.util.*;

public class Leetcode792 {
    public static void main(String[] args) {
//        String s = "abcde";
//        String[] w = new String[]{"a", "bb", "acd", "ace"};
//        String s = "dsahjpjauf";
//        String[] w = new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"};

        String s = "iwdlcxpyagegrcnrcylxolxlnhhwnxyzltiscrjztiivnpnzlubzpueihinsqdfvypdteztiodbhaqhxskupwulvkzhczdyoouym";
        String[] w = new String[]{"hhwnxyzltiscrjztiivnpnzlubzpueihinsqdfvyp",
                "vnpnzlubzpueihinsqdfvypdteztiodbha",
                "rcnrcylxolxlnhhwnxyzltiscrjztiivnpnzlubzpueihi",
                "dfvypdteztiodbhaqhxskupwulvk",
                "zltiscrjztii",
                "wdmbatbcewwittubryrqwwrvfkrmniomofygybeqfzusrgeart",
                "myzfexqmzxnbmmnhmpbddqhrwrobqzjiwdzzpyzodejysuuquc",
                "wxvrcbihbasohfvuwuxleesqeujxvjfvgwnhltenbspdgzsdrs",
                "nztyysfhfbfcihyeaqdarqxfpjunevabzafvbmpbtenarvyizv",
                "nivufheyodfjuggrbndyojeahrzgptikjfqufhwyhzyyjteahx"};
        System.out.println(numMatchingSubseq3(s, w));
    }

    // 算是暴力解法吧，在超时和不超时的边缘徘徊，一次过了，一次没过
    // 最大复杂度小于 25万 * 5万 （125亿）
    public static int numMatchingSubseq(String s, String[] words) {
        int res = 0;
        out:
        for (String w : words) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < w.length(); i++) {
                int i1 = sb.indexOf(w.charAt(i) + "");
                if (i1 == -1) continue out;
                sb.delete(0, i1 + 1);
            }
            res++;
        }
        return res;
    }

    public static int numMatchingSubseq2(String s, String[] words) { // 预处理 使得时间复杂度最大为25万
        int len = s.length();
        // reverse list.
        ArrayList<int[]> tmp = new ArrayList<>();
        // init reverse list.
        int[] zero = new int[26];
        Arrays.fill(zero, -1);
        zero[s.charAt(len - 1) - 'a'] = len - 1;
        tmp.add(zero);
        for (int i = 1; i < len; i++) {
            int[] cur = Arrays.copyOf(tmp.get(i - 1), 26);
            cur[s.charAt(len - 1 - i) - 'a'] = len - 1 - i;
            tmp.add(cur);
        }
        Collections.reverse(tmp);
        int res = 0;
        out:
        for (String w : words) {
            int start = 0;
            for (int i = 0; i < w.length(); i++) {
                int next = tmp.get(start)[w.charAt(i) - 'a'];
                if (next == -1) {
                    continue out;
                }
                start = next + 1;
                if (start >= len && i + 1 < w.length()) {
                    continue out;
                }
            }
            res++;
        }
        return res;
    }

    public static int numMatchingSubseq3(String s, String[] words) {
        // 官方超牛题解 多指针并行 使用队列实现
        // 理解这个方法需要先理解官方题解一，预处理加二分查找。
        // 复杂度是小于25万的
        Queue<int[]>[] p = new Queue[26];
        for (int i = 0; i < 26; ++i) {
            p[i] = new ArrayDeque<int[]>();
        }
        for (int i = 0; i < words.length; ++i) {
            p[words[i].charAt(0) - 'a'].offer(new int[]{i, 0});
        }
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            int len = p[c - 'a'].size();
            while (len > 0) {
                int[] t = p[c - 'a'].poll();
                if (t[1] == words[t[0]].length() - 1) {
                    ++res;
                } else {
                    ++t[1];
                    p[words[t[0]].charAt(t[1]) - 'a'].offer(t);
                }
                --len;
            }
        }
        return res;
    }
}
