package middle;

public class Leetcode754 {
    public static void main(String[] args) {
        System.out.println(reachNumber(9));
    }

    static int size = 100000;
    static int[] distance = new int[size];

    public static int reachNumber(int target) { // 要用数学思路做的，吃了数学不好的亏，下面的代码并没有通过
        distance[1] = 1;
        target = Math.abs(target);
        for (int i = 1; i < size; i++) {
            if (distance[i] == 0) {
                distance[i] = distance[i - 1] + i;
            }
            if (distance[i] == target) return i;
            if (distance[i] > target) {
                int right = distance[i] - target;
                int left = target - distance[i - 1];
                int r = Integer.MAX_VALUE;
                int l = Integer.MAX_VALUE;
                if (right % 2 == 0) {
                    r = i;
                } else {
                    r = i + 2;
                }
                l = i - 1 + left * 2;
                return Math.min(r, l);
            }
        }
        // never
        return -1;
    }

    // 数学解法见官方题解把。。 使用奇偶性进行计算，需要先找到最少移动的规律。。好难啊。。
}
