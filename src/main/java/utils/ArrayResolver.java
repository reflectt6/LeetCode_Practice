package utils;

public class ArrayResolver {
    // 用于将Leetcode题目中的字符串解析为对应的数组

    public static int[][] resolveTwoDimensionalArray(String s) {
        String[] split = s.split("],");
        int[][] r = new int[split.length][];
        for (int i = 0; i < split.length; i++) {
            String tmp = split[i].replaceAll("\\[", "")
                    .replaceAll("]", "");
            String[] split1 = tmp.split(",");
            r[i] = new int[split1.length];
            for (int i1 = 0; i1 < split1.length; i1++) {
                r[i][i1] = Integer.parseInt(split1[i1]);
            }
        }
        return r;
    }

    public static int[] resolveOneDimensionalArray(String s) {
        String[] s1 = s.replaceAll("\\[", "")
                .replaceAll("]", "")
                .split(",");
        int[] r = new int[s1.length];
        for (int i = 0; i < s1.length; i++) {
            r[i] = Integer.parseInt(s1[i]);
        }
        return r;
    }
}
