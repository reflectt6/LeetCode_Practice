package middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class LC2661 {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < mat.length; i++) {
            for (int i1 = 0; i1 < mat[i].length; i1++) {
                mat[i][i1] = map.get(mat[i][i1]);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < mat.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int i1 = 0; i1 < mat[i].length; i1++) {
                max = Math.max(mat[i][i1], max);
            }
            min = Math.min(min, max);
        }
        for (int i = 0; i < mat[0].length; i++) {
            int max = Integer.MIN_VALUE;
            for (int i1 = 0; i1 < mat.length; i1++) {
                max = Math.max(mat[i1][i], max);
            }
            min = Math.min(min, max);
        }
        return min;
    }

    public int firstCompleteIndex2(int[] arr, int[][] mat) { // 尝试提升效率
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        int min = Integer.MAX_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < mat.length; i++) {
            int max = Integer.MIN_VALUE;
            int y = 0;
            for (int i1 = 0; i1 < mat[i].length; i1++) {
                mat[i][i1] = map.get(mat[i][i1]);
                if (mat[i][i1] > max) {
                    max = mat[i][i1];
                    y = i1;
                }
            }
            min = Math.min(min, max);
            set.add(y);
        }

        for (int i = 0; i < mat[0].length; i++) {
            if (set.contains(i)) continue;
            int max = Integer.MIN_VALUE;
            for (int i1 = 0; i1 < mat.length; i1++) {
                max = Math.max(mat[i1][i], max);
            }
            min = Math.min(min, max);
        }
        return min;
    }

    public int firstCompleteIndex3(int[] arr, int[][] mat) { // 尝试提升效率
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        int[] m = new int[mat.length];
        int[] n = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int i1 = 0; i1 < mat[i].length; i1++) {
                int tmp = map.get(mat[i][i1]);
                m[i] = Math.max(m[i], tmp);
                n[i1] = Math.max(n[i1], tmp);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i : m) {
            min = Math.min(i , min);
        }
        for (int i : n) {
            min = Math.min(i , min);
        }
        return min;
    }

    public int firstCompleteIndex4(int[] arr, int[][] mat) { // 尝试提升效率
        int[] map = new int[mat.length * mat[0].length + 1];
        for (int i = 0; i < arr.length; i++) {
            map[arr[i]] = i;
        }
        int[] m = new int[mat.length];
        int[] n = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int i1 = 0; i1 < mat[i].length; i1++) {
                int tmp = map[mat[i][i1]];
                m[i] = Math.max(m[i], tmp);
                n[i1] = Math.max(n[i1], tmp);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i : m) {
            min = Math.min(i , min);
        }
        for (int i : n) {
            min = Math.min(i , min);
        }
        return min;
    }

    public static void main(String[] args) {
        LC2661 lc2661 = new LC2661();
        int [][] a  = new int[3][];
        a[0] = new int[]{3,2,5};
        a[1] = new int[]{1,4,6};
        a[2] = new int[]{8,7,9};
        int i = lc2661.firstCompleteIndex4(new int[]{2, 8, 7, 4, 1, 3, 5, 6, 9}, a);
        System.out.println(i);
    }
}
