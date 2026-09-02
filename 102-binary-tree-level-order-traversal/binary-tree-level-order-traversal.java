/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(TreeNode node, int level, List<List<Integer>> ans) {
        if (node == null) return;

        // Agar hum pehli baar is level par aaye hain, to ek nayi list add karo
        if (level == ans.size()) {
            ans.add(new ArrayList<>());
        }

        // Current level ki list mein node ka value daalo
        ans.get(level).add(node.val);

        // Pehle left subtree, fir right subtree
        dfs(node.left, level + 1, ans);
        dfs(node.right, level + 1, ans);
    }
}