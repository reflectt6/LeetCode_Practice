package middle;

public class LC53 {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0];
        int max = f[0];
        for (int i = 1; i < nums.length; i++) {
            if (f[i - 1] > 0) {f[i] = f[i - 1] + nums[i];}
            else f[i] = nums[i];
            max = Math.max(max, f[i]);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        int pre = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (pre > 0) {pre = pre + nums[i];}
            else pre = nums[i];
            max = Math.max(max, pre);
        }
        return max;
    }
}
