package hard;

import utils.ArrayResolver;

import java.util.*;

public class LC2454 {

    // 超时。。
    public int[] secondGreaterElement(int[] nums) {
        int[] answer = new int[nums.length];
        List<int[]> list = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int position = binarySearch(list, nums[i]);
            list.add(position, new int[]{nums[i], -2, i});

            int position2 = binarySearch(list2, nums[i]);
            list2.add(position2, new int[]{nums[i], position});
            for (int i1 = position2 + 1; i1 < list2.size(); i1++) {
                list2.get(i1)[1] += 1;
            }

            for (int i1 = position2 - 1; i1 >= 0; i1--) {
                int[] ints = list.get(list2.get(i1)[1]);
                if (ints[1] == -2) ints[1] = -1;
                else if (ints[1] == -1) {
                    ints[1] = nums[i];
                    list2.remove(i1);
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[1] < 0) answer[list.get(i)[2]] = -1;
            else answer[list.get(i)[2]] = list.get(i)[1];
        }
        return answer;
    }

    public int binarySearch(List<int[]> list, int target) {
        int l = 0;
        int r = list.size() - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (list.get(mid)[0] >= target) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        LC2454 lc2454 = new LC2454();
//        int[] a = lc2454.secondGreaterElement(ArrayResolver.resolveOneDimensionalArray("[2,4,0,9,6]"));
//        int[] a = lc2454.secondGreaterElement(ArrayResolver.resolveOneDimensionalArray("[3,3]"));
        int[] a = lc2454.secondGreaterElement3(ArrayResolver.resolveOneDimensionalArray("[1,17,18,0,18,10,20,0]"));
        System.out.println();
    }

    public int[] secondGreaterElement2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {nums[i], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        TreeSet<Integer> ts = new TreeSet<>();
        for (int[] pair : arr) {
            int i = pair[1];
            Integer j = ts.higher(i);
            if (j != null && ts.higher(j) != null) {
                ans[i] = nums[ts.higher(j)];
            }
            ts.add(i);
        }
        return ans;
    }

    public int[] secondGreaterElement3(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<Integer>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < nums.length; ++i) {
            while (!pq.isEmpty() && pq.peek()[0] < nums[i]) {
                res[pq.poll()[1]] = nums[i];
            }
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                pq.offer(new int[]{nums[stack.peek()], stack.peek()});
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }


}
