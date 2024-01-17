package simple;

public class LC2744 {
    public static int maximumNumberOfStringPairs(String[] words) {
        int num = 0;
        byte[] b = new byte[50];
        for (int i = 0; i < words.length - 1; i++) {
            if (b[i] == 1) continue;
            for (int j = i + 1; j < words.length; j++) {
                if (b[j] == 1) continue;
                if (words[i].length() == words[j].length()) {
                    boolean f = true;
                    for (int i1 = 0; i1 < words[i].length(); i1++) {
                        if (i1 !=  (words[i].length() -i1 - 1) && words[i].charAt(i1) != words[j].charAt(words[i].length() -i1 - 1)) {
                            f = false;
                            break;
                        }
                    }
                    if (f) {
                        num++;
                        b[i] = 1;
                        b[j] = 1;
                    }
                }
            }
        }
        return num;
    }

    static int t(String[] words) {
        int n = words.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (words[i].charAt(0) == words[j].charAt(1) && words[i].charAt(1) == words[j].charAt(0)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int a = maximumNumberOfStringPairs(new String[]{"ab","ba","cc"});
        int a = t(new String[]{"cd","ac","dc","ca","zz"});
        System.out.println(a);
    }
}
