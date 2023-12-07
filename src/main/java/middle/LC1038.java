package middle;

public class LC1038 {
    int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        sum = 0;
        dfs(root);
        return root;
    }

    public void dfs(TreeNode node) {
        // right
        if (node.right != null) {
            dfs(node.right);
        }

        // middle
        sum += node.val;
        node.val = sum;

        // left
        if (node.left != null) {
            dfs(node.left);
        }
    }
}
