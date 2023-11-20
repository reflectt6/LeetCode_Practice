package simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class LC2586 {
    public int vowelStrings(String[] words, int left, int right) {
        HashSet set = new HashSet(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int num = 0;
        for (int i = left; i <= right; i++) {
//            int len = words[i].length();
            if (set.contains(words[i].charAt(0)) && set.contains(words[i].charAt(words[i].length() - 1))) {
                num++;
            }
        }
        return num;
    }

    public int vowelStrings2(String[] words, int left, int right) {
        HashSet set = new HashSet(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int num = 0;
        for (int i = left; i <= right; i++) {
            if (!set.contains(words[i].charAt(0))) continue;
            if (words[i].length() == 1) {
                num++;
                continue;
            }
            if (set.contains(words[i].charAt(words[i].length() - 1))) {
                num++;
            }
        }
        return num;
    }

    // 宫水三叶
    // aeiou".indexOf(a) 比 HashSet性能更好
    public int vowelStrings3(String[] words, int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            String s = words[i];
            char a = s.charAt(0), b = s.charAt(s.length() - 1);
            if ("aeiou".indexOf(a) != -1 && "eaiou".indexOf(b) != -1) ans++;
        }
        return ans;
    }
}
