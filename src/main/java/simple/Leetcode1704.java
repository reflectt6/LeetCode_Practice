package simple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode1704 {
    public static void main(String[] args) {
        System.out.println(halvesAreAlike("eA"));
    }

    public static boolean halvesAreAlike(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        int front = 0;
        int behind = 0;
        int half = s.length() / 2;
        for (int i = 0; i < half; i++) {
            if (set.contains(s.charAt(i))) {
                front++;
            }
        }
        for (int i = half; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                behind++;
            }
        }
        return front == behind;
    }
}
