package middle;

public class Leetcode481 {
    public static void main(String[] args) {
//        System.out.println(magicalString(4));
//        System.out.println(magicalString(9));
//        System.out.println(magicalString(10));
        System.out.println(magicalString(14));
    }

    public static int magicalString(int n) { // 通过 但是比较慢
        StringBuilder sb = new StringBuilder("122112122");
        while (sb.length() < n) {
            StringBuilder tmp = new StringBuilder();
            boolean one = true;
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (one) {
                    if (c == '1') {
                        tmp.append(1);
                    } else {
                        tmp.append(1).append(1);
                    }
                } else {
                    if (c == '1') {
                        tmp.append(2);
                    } else {
                        tmp.append(2).append(2);
                    }
                }
                one = !one;
            }
            sb = tmp;
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == '1') cnt++;
        }
        return cnt;
    }

    public static int magicalString2(int n) {
        // 想想怎么优化,算了看题解，把StringBuilder sb = new StringBuilder("122112122");搞成静态的，可以提升一倍性能
        StringBuilder sb = new StringBuilder("122112122");
        while (sb.length() < n) {
            StringBuilder tmp = new StringBuilder();
            boolean one = true;
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (one) {
                    if (c == '1') {
                        tmp.append(1);
                    } else {
                        tmp.append(1).append(1);
                    }
                } else {
                    if (c == '1') {
                        tmp.append(2);
                    } else {
                        tmp.append(2).append(2);
                    }
                }
                one = !one;
            }
            sb = tmp;
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == '1') cnt++;
        }
        return cnt;
    }

    public int magicalString3(int n) {
        // 官方题解，双指针，一个指向原串，一个指向构造的新串
        if (n < 4) {
            return 1;
        }
        char[] s = new char[n];
        s[0] = '1';
        s[1] = '2';
        s[2] = '2';
        int res = 1;
        int i = 2;
        int j = 3;
        while (j < n) {
            int size = s[i] - '0';// 计算新串中连续几个数字一样（1或2）
            int num = 3 - (s[j - 1] - '0'); // 上一个是1，下一个就是2；上一个是2，下一个就是1；很巧妙
            while (size > 0 && j < n) {
                s[j] = (char) ('0' + num);
                if (num == 1) {
                    ++res;
                }
                ++j;
                --size;
            }
            ++i;
        }
        return res;
    }
}
