package middle;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Leetcode907 {
    public static void main(String[] args) {
//        int[] arr = new int[]{3,1,2,4};
        int[] arr = new int[]{11, 81, 94, 43, 3};
        System.out.println(sumSubarrayMins4(arr));
    }

    public static int sumSubarrayMins(int[] arr) { // 用了动态规划的思想，从后向前递推 时间复杂度 O(n^2) 这还能超时。。
        // 倒叙遍历，前面最小值可根据后面最小值推断出来
        int m = (int) (1e9 + 7);
        int[] tmp = new int[40005];
        Arrays.fill(tmp, 40001);
        int sum = 0;
        for (int i = arr.length - 1; i >= 0; i--) { // 以谁开头
            for (int j = arr.length; j >= i + 1; j--) { // 确定以谁开头的个数
                tmp[j] = Math.min(arr[i], tmp[j]);
                sum = (sum + tmp[j]) % m;
            }
        }
        return sum;
    }

    public static int sumSubarrayMins2(int[] arr) {
        // 看了一个题解，用if判断是否需要取模，可以再进一步，说明取模运算还是很费时间的
        // 倒叙遍历，前面最小值可根据后面最小值推断出来
        int m = (int) (1e9 + 7);
        int[] tmp = new int[40005];
        Arrays.fill(tmp, 40001);
        int sum = 0;
        for (int i = arr.length - 1; i >= 0; i--) { // 以谁开头
            for (int j = arr.length; j >= i + 1; j--) { // 确定以谁开头的个数
                tmp[j] = Math.min(arr[i], tmp[j]);
                sum = (sum + tmp[j]);
                if (sum > m) {
                    sum = sum % m;
                }
            }
        }
        return sum;
    }

    public static int sumSubarrayMins3(int[] arr) {
        // 其实上面这种解法我已经山穷水尽了，看了题解可以用单调栈再进一步
        int n = arr.length;
        Deque<Integer> monoStack = new ArrayDeque<Integer>();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            while (!monoStack.isEmpty() && arr[i] <= arr[monoStack.peek()]) {
                monoStack.pop();
            }
            left[i] = i - (monoStack.isEmpty() ? -1 : monoStack.peek());
            monoStack.push(i);
        }
        monoStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!monoStack.isEmpty() && arr[i] < arr[monoStack.peek()]) {
                monoStack.pop();
            }
            right[i] = (monoStack.isEmpty() ? n : monoStack.peek()) - i;
            monoStack.push(i);
        }
        long ans = 0;
        final int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) left[i] * right[i] * arr[i]) % MOD;
        }
        return (int) ans;
    }

    // 题解还有一种动态规划结合单调栈的解决
    public static int sumSubarrayMins4(int[] arr) {
        int n = arr.length;
        long ans = 0;
        final int MOD = 1000000007;
        Deque<Integer> monoStack = new ArrayDeque<Integer>();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            while (!monoStack.isEmpty() && arr[monoStack.peek()] > arr[i]) {
                monoStack.pop();
            }
            int k = monoStack.isEmpty() ? (i + 1) : (i - monoStack.peek());
            dp[i] = k * arr[i] + (monoStack.isEmpty() ? 0 : dp[i - k]);
            ans = (ans + dp[i]) % MOD;
            monoStack.push(i);
        }
        return (int) ans;
    }
}
