package middle;

public class Leetcode775 {
    public static void main(String[] args) {
//        int[] n = new int[]{1,0,2};
        int[] n = new int[]{1,2,0};
        System.out.println(isIdealPermutation2(n));
    }

    public static boolean isIdealPermutation(int[] nums) { // 预处理（有点动规的影子但不多，因为太简单）
        int[] min = new int[nums.length];
        min[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                min[i] = nums[i];
            } else {
                min[i] = min[i + 1];
            }
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > min[i + 2]) {
                return false;
            }
        }
        return true;
    }

    // 三叶 树状数组
    static int n; // 数组长度
    static int[] tr;
    static int lowbit(int x) {
        return x & -x;
    }
    static void add(int x) {
        for (int i = x; i <= n; i += lowbit(i)) tr[i]++;
    }
    static int query(int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) ans += tr[i];
        return ans;
    }
    public static boolean isIdealPermutation2(int[] nums) {
        n = nums.length;
        tr = new int[n + 10];
        add(nums[0] + 1);
        int a = 0, b = 0;
        for (int i = 1; i < n; i++) {
            a += query(n) - query(nums[i] + 1);
            b += nums[i] < nums[i - 1] ? 1 : 0;
            add(nums[i] + 1);
        }
        return a == b;
    }

}
