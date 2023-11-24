package middle;

public class LC2216 {
    public int minDeletion(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        int delCnt = 0;
        for (int i = 0; i < n - 1; i += 2) {
            if (nums[i] == nums[i + 1]) {
                delCnt++;
                i--;
            }
            if (i == (n - 3)) {
                delCnt++;
            }
        }
        return delCnt;
    }
    public int minDeletion2(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n; i++) {
            if ((i - cnt) % 2 == 0 && i + 1 < n && nums[i] == nums[i + 1]) cnt++;
        }
        return (n - cnt) % 2 != 0 ? cnt + 1 : cnt;
    }

    public static void main(String[] args) {
        LC2216 l = new LC2216();
        l.minDeletion2(new int[]{1,2,3,3,4,5});
    }
}
