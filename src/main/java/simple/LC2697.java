package simple;

public class LC2697 {
    public String makeSmallestPalindrome(String s) {
        int length = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length / 2; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(length - 1 - i);
            if (c1 >= c2) {
                sb.insert(i, c2);
                sb.insert(i, c2);
            } else {
                sb.insert(i, c1);
                sb.insert(i, c1);
            }
        }
        if (length % 2 == 1) {
            sb.insert(length / 2, s.charAt(length / 2));
        }
        return sb.toString();
    }


}
