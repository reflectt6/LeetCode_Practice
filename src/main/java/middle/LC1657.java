package middle;

import jdk.internal.util.xml.impl.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class LC1657 {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int[] n1 = new int[26];
        int[] n2 = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            n1[c1-'a'] += 1;
            n2[c2-'a'] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if ((n1[i] > 0 && n2[i] == 0)
                    || (n2[i] > 0 && n1[i] == 0)) {
                return false;
            }
        }
        Arrays.sort(n1);
        Arrays.sort(n2);
        for (int i = 0; i < 26; i++) {
            if (n1[i] != n2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC1657 lc1657 = new LC1657();
        System.out.println(lc1657.closeStrings("cabbba", "abbccc"));

    }
}
