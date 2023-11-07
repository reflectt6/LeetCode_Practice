package middle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Leetcode814 {
    public static void main(String[] args) {
//        List<String> r = ambiguousCoordinates("(123)");
//        List<String> r = ambiguousCoordinates("(00011)");
//        List<String> r = ambiguousCoordinates("(0123)");
        List<String> r = ambiguousCoordinates2("(0001)");
        System.out.println();
    }

    static String s;
    public static List<String> ambiguousCoordinates2(String _s) {
        s = _s.substring(1, _s.length() - 1);
        int n = s.length();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) { // 枚举逗号：在 i 的后面追加逗号
            List<String> a = search(0, i), b = search(i + 1, n - 1);
            for (String x : a) {
                for (String y : b) {
                    ans.add("(" + x + ", " + y + ")");
                }
            }
        }
        return ans;
    }
    static List<String> search(int start, int end) {
        List<String> ans = new ArrayList<>();
        if (start == end || s.charAt(start) != '0') ans.add(s.substring(start, end + 1));
        for (int i = start; i < end; i++) { // 枚举小数点：在 i 后面追加小数点
            String a = s.substring(start, i + 1), b = s.substring(i + 1, end + 1);
            if (a.length() > 1 && a.charAt(0) == '0') continue;
            if (b.charAt(b.length() - 1) == '0') continue;
            ans.add(a + "." + b);
        }
        return ans;
    }

    public static List<String> ambiguousCoordinates(String s) { // 我这解法好复杂
        StringBuilder ori = new StringBuilder(s.substring(1, s.length() - 1));
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= ori.length(); i++) {
            StringBuilder left = new StringBuilder(ori.substring(0, i));
            StringBuilder right = new StringBuilder(ori.substring(i, ori.length()));
            if (checkD(left) && checkD(right)) {
                List<StringBuilder> leftList = new ArrayList<>();
                List<StringBuilder> rightList = new ArrayList<>();
                if (checkP(left.toString())) {
                    leftList.add(left);
                }
                for (int j = 1; j < left.length(); j++) {
                    StringBuilder leftPoint = new StringBuilder(left.substring(0, j));
                    StringBuilder rightPoint = new StringBuilder(left.substring(j, left.length()));
                    if (checkP(leftPoint, rightPoint, ".")) {
                        leftList.add(leftPoint.append(".").append(rightPoint));
                    }
                }
                if (checkP(right.toString())) {
                    rightList.add(right);
                }
                for (int j = 1; j < right.length(); j++) {
                    StringBuilder leftPoint = new StringBuilder(right.substring(0, j));
                    StringBuilder rightPoint = new StringBuilder(right.substring(j, right.length()));
                    if (checkP(leftPoint, rightPoint, ".")) {
                        rightList.add(leftPoint.append(".").append(rightPoint));
                    }
                }
                shuffle(leftList, rightList, res);
            }
        }
        return  res;
    }

    public static boolean checkD(StringBuilder s) {
        if (s.length() == 1) return true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') return true;
        }
        return false;
    }

    public static boolean checkP(StringBuilder left, StringBuilder right, String symbol) {
        if (right.charAt(right.length() - 1) == '0') return false;
        if (String.valueOf(Integer.parseInt(right.toString())).equals("0")) return false;
        String combination = left + symbol + right;
        if (new BigDecimal(Double.parseDouble(combination)).equals(combination)) {
            return true;
        }
        return false;
    }

    public static boolean checkP(String s) {
        if (String.valueOf(Integer.parseInt(s)).equals(s)) {
            return true;
        }
        return false;
    }

    public static void shuffle(List<StringBuilder> leftList
            , List<StringBuilder> rightList, List<String> res) {
        for (StringBuilder left : leftList) {
            for (StringBuilder right : rightList) {
                res.add("(" + left + ", " + right + ")");
            }
        }
    }
}
