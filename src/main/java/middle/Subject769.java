package middle;

public class Subject769 {
    public static void main(String[] args) {
//        int[] t = new int[]{4,3,2,1,0};
//        int[] t = new int[]{0, 1};
//        int[] t = new int[]{0};
//        int[] t = new int[]{1,2,0,3};
        int[] t = new int[]{1,0,2,3,4};
        System.out.println(maxChunksToSorted(t));
    }

    public static int maxChunksToSorted(int[] arr) { // 思路有问题，结果也不对，正确思路见三叶的题解。
        // 简单模拟，思路也很重要，然后在模拟这个思路。
        int end = arr.length - 1;
        int res = 1;
        while (end >= 0) {
            int sum2 = 0;
            for (int i = end; i >= 0; i--) {
                sum2 += arr[i];
                if (arr[i] == end) {
                    if ((i + end) * (end - i + 1) / 2 != sum2 || i == 0) {
                        return res;
                    }
                    res++;
                    end = i - 1;
                    break;
                }
            }
        }
        return res;
    }
}
