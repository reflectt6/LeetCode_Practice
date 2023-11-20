package simple;

public class LC2609 {
    public int findTheLongestBalancedSubstring(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                if (right != 0) {
                    left = 0;
                    right = 0;
                }
                left++;
            } else if (c == '1') {
                right++;
                max = Math.max(max, Math.min(left, right));
            }
        }
        return max * 2;
    }
}
