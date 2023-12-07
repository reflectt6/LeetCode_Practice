package middle;

public class LC1457 {
    public int pseudoPalindromicPaths (TreeNode root) {
        return dfs(root, (short) 0, 0);
    }

    int dfs (TreeNode treeNode, short vector, int length) {
        if (treeNode.val == 0) return 0;
        vector ^= 1 << (treeNode.val-1);
        length++;

        if (treeNode.right == null && treeNode.left == null) {
            if ((length & 1) == 1) {
                for (int i = 0; i < 9; i++) {
                    if (vector == (1 << i)) {
                        return 1;
                    }
                }
            } else {
                if (vector == 0) return 1;
            }
            return 0;
        }

        int cnt = 0;
        if (treeNode.right != null) {
            cnt += dfs(treeNode.right, vector, length);
        }
        if (treeNode.left != null) {
            cnt += dfs(treeNode.left, vector, length);
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
