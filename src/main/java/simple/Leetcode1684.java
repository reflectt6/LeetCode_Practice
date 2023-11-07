package simple;

import java.util.HashSet;

public class Leetcode1684 {
    public static void main(String[] args) {
        System.out.println(countConsistentStrings2("ab", new String[]{"ad", "bd", "aaab", "baa", "badab"}));
    }

    public static int countConsistentStrings2(String allowed, String[] words) { // 三叶
        int hash = 0, ans = 0;
        for (char c : allowed.toCharArray()) {
            hash |= (1 << (c - 'a'));
        }
        out:
        for (String s : words) {
            for (char c : s.toCharArray()) {
                if (((hash >> (c - 'a')) & 1) == 0) continue out;
            }
            ans++;
        }
        return ans;
    }

    public static int countConsistentStrings(String allowed, String[] words) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < allowed.length(); i++) {
            set.add(allowed.charAt(i));
        }
        int cnt = 0;
        out:
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (!set.contains(words[i].charAt(j))) {
                    continue out;
                }
            }
            cnt++;
        }
        return cnt;
    }
}
