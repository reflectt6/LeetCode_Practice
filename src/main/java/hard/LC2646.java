package hard;

import utils.ArrayResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC2646 {
    List<Integer>[] relation;
    int s1;
    int s2;
    int[]p1;
    int[]p2;

    // 有一个用例过不了，但是，找不到那里错了，这种情况下这个题肯定是做不出来了。。。
    // 于是曲线救国，先看看官解题解，等通过后再来对比
    // 曲线救国成功了，我这个算法的问题找到了，halveThePrice和题意不合符

    /** 我认为halveThePrice只有两种方式，假设有四个节点线性连接0->1->2->3
     *  1、0和2减半，1和3不减半
     *  2、0和2不减半，1和3减半
     *
     *  实际上出了这两种方式，还存在第三种减法
     *  3、0和3减半，1和2不减半。。。
     *  这样我的算法就出问题了
     */

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        s1 = 0;
        s2 = 0;
        relation = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            relation[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int t1 = edges[i][0];
            int t2 = edges[i][1];
            relation[t1].add(t2);
            relation[t2].add(t1);
        }

        p1 = Arrays.copyOf(price, price.length);
        p2 = Arrays.copyOf(price, price.length);
        halveThePrice(-1, 0, true, p1);
        halveThePrice(-1, 0, false, p2);

        for (int[] trip : trips) {
            if (travel(trip[0], trip[1], -1)) {
                s1 += p1[trip[0]];
                s2 += p2[trip[0]];
            }
        }
        return Math.min(s1, s2);
    }

    public void halveThePrice(int preNode, int currNode, boolean isHalve, int[] p) {
        if (isHalve) p[currNode] = p[currNode] / 2;
        for (Integer nextNode : relation[currNode]) {
            if (nextNode != preNode) halveThePrice(currNode, nextNode, !isHalve, p);
        }
    }

    public boolean travel(int start, int end, int pre) {
        if (start == end) return true;
        for (Integer nextNode : relation[start]) {
            if (nextNode == pre) continue;
            if (travel(nextNode, end, start)) {
                s1 += p1[nextNode];
                s2 += p2[nextNode];
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[][] edges = new int[3][];
//        edges[0] = new int[] {0,1};
//        edges[1] = new int[] {1,2};
//        edges[2] = new int[] {1,3};
//        int[]price = new int[]{2,2,10,6};
//        int[][] trips = new int[3][];
//        trips[0] = new int[] {0,3};
//        trips[1] = new int[] {1,2};
//        trips[2] = new int[] {2,3};

//        int[][] edges = new int[1][];
//        edges[0] = new int[] {0,1};
//
//        int[]price = new int[]{2,2};
//        int[][] trips = new int[1][];
//        trips[0] = new int[] {0,0};

        LC2646 lc2646 = new LC2646();
        System.out.println(lc2646.minimumTotalPrice(9,
                ArrayResolver.resolveTwoDimensionalArray("[[2,5],[3,4],[4,1],[1,7],[6,7],[7,0],[0,5],[5,8]]"),
                ArrayResolver.resolveOneDimensionalArray("[4,4,6,4,2,4,2,14,8]"),
                ArrayResolver.resolveTwoDimensionalArray("[[1,5],[2,7],[4,3],[1,8],[2,8],[4,3],[1,5],[1,4],[2,1],[6,0],[0,7],[8,6],[4,0],[7,5],[7,5],[6,0],[5,1],[1,1],[7,5],[1,7],[8,7],[2,3],[4,1],[3,5],[2,5],[3,7],[0,1],[5,8],[5,3],[5,2]]")));
    }
}
