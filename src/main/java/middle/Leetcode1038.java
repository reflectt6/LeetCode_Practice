package middle;


import java.util.ArrayList;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Leetcode1038 {
    public static TreeNode bstToGst(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    public static int dfs(TreeNode root, int addition) {
        if (root == null) return 0;
        int dfsR = dfs(root.right, addition);
        if (dfsR == 0) root.val = root.val + addition;
        else root.val = root.val + dfsR;
        int dfsL = dfs(root.left, root.val);
        if (dfsL == 0) return root.val;
        else return dfsL;
    }

    public static void main(String[] args) {
        TreeNode tn = new TreeNode(4);
        tn.left = new TreeNode(1);
        tn.right = new TreeNode(6);
        tn.left.left = new TreeNode(0);
        tn.left.right = new TreeNode(2);
        tn.left.right.right = new TreeNode(3);
        tn.right.left = new TreeNode(5);
        tn.right.right = new TreeNode(7);
        tn.right.right.right = new TreeNode(8);

        TreeNode node = bstToGst(tn);
        System.out.println();

    }

//    public TreeNode generator(String[] items) {
//        String str = "[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]"
//        String[] items = str
//                .replaceAll("\\[", "")
//                .replaceAll("]", "").split(",");
//
//        TreeNode node = new TreeNode(Integer.parseInt(items[0]));
//
//        for (int i = 1; i < items.length; i++) {
//            if (items.equals("null")) {
//
//            } else {
//
//            }
//        }
//    }
}