package middle;

/**
 307. 区域和检索 - 数组可修改
 给你一个数组 nums ，请你完成两类查询。

 其中一类查询要求 更新 数组 nums 下标对应的值
 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 实现 NumArray 类：

 NumArray(int[] nums) 用整数数组 nums 初始化对象
 void update(int index, int val) 将 nums[index] 的值 更新 为 val
 int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 */

public class LC307 {

}

// 很容易想到的方法，没想到直接过了。。。这凭什么中级啊
class NumArray {
    int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public void update(int index, int val) {
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }
}

// 我要再优化一下 时间从1700ms到900ms
class NumArray2 {
    int[] nums;
    int[] increment;
    public NumArray2(int[] nums) {
        this.nums = nums;
        this.increment = new int[nums.length];
        increment[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            increment[i] = increment[i-1] + nums[i];
        }
    }

    public void update(int index, int val) {
        int gap = val - nums[index];
        nums[index] = val;
        for (int i = index; i < nums.length; i++) {
            increment[i] += gap;
        }
    }

    public int sumRange(int left, int right) {
        return increment[right] - increment[left] + nums[left];
    }
}

// 宫水三叶 数状数组
class NumArray3 {
    int[] tree;
    int lowbit(int x) {
        return x & -x;
    }
    int query(int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) ans += tree[i];
        return ans;
    }
    void add(int x, int u) {
        for (int i = x; i <= n; i += lowbit(i)) tree[i] += u;
    }

    int[] nums;
    int n;
    public NumArray3(int[] _nums) {
        nums = _nums;
        n = nums.length;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) add(i + 1, nums[i]);
    }

    public void update(int i, int val) {
        add(i + 1, val - nums[i]);
        nums[i] = val;
    }

    public int sumRange(int l, int r) {
        return query(r + 1) - query(l);
    }
}

//作者：宫水三叶
//        链接：https://leetcode.cn/problems/range-sum-query-mutable/solutions/632515/guan-yu-ge-lei-qu-jian-he-wen-ti-ru-he-x-41hv/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
