package middle;

import utils.Solution;

import java.util.*;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class Subject46 implements Solution<List<List<Integer>>,Integer[]> {
    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1,2,3,4};
        System.out.println((new Subject46().exec(nums)).toString());
    }

    @Override
    public List<List<Integer>> exec(Integer[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0){
            return res;
        }
        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] used = new boolean[len];
        dfs(nums,len,0,path,used,res);
        return res;
    }
//深度优先搜索
    private void dfs(Integer[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        //递归终止条件，也是叶子节点执行的操作
        if (depth == len){
            res.add(new ArrayList<>(path));
//            回溯操作放到这里看下可以不
//            经过测试回溯操作放在这里是不行的，因为光在叶子节点回溯是不完全的，非叶子节点也需要回溯
//            System.out.println(path.toString());
//            path.removeLast();
//            System.out.println(path.toString());
            return;
        }
        //递归主体，也是非叶子节点执行的操作
        for (int i = 0; i < used.length; i++) {
            if (!used[i]){
                path.addLast(nums[i]);
                used[i] = true;
                dfs(nums,len,depth+1,path,used,res);
                //状态回溯
                used[i] = false;
                path.removeLast();
            }
        }
    }


    //广度优先搜索
    private void bfs(Integer[] nums, int len, int depth, Deque<Integer> path,Queue<Integer>myList[], boolean[] used, List<List<Integer>> res) {
        for (int i = 0; i < used.length; i++) {
            if (!used[i]){
                myList[0].add(nums[i]);//0 下标存放数字
                myList[1].add(i);//1 下标存放used下标
                used[i] = true;
            }
        }
        path.add(myList[0].poll());
//        used[myList[1].poll()] = false;
        if (depth == len){
            res.add(new ArrayList<>(path));
            path.removeLast();
//            used[]
            return;
        }
        bfs(nums,len,depth+1,path,myList,used,res);
    }

    private int factorial(int len) {
        if (len <= 0){return -1;}
        if (len == 1) { return 1;}
        return factorial(len - 1);
    }
}
//感悟：
// 谈到树形结构，我一开始以为需要我手动去创建这个树出来，结果并没有，树形结构只是存在与我们的脑海，并没有具象成一个具体数据结构。
// 但是就是这个抽象的概念，居然可以完成深度优先搜索，好神奇！！