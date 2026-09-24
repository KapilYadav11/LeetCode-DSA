/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;

        while (curr != null) {
            // Both p and q lie on the right
            if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } 
            // Both p and q lie on the left
            else if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            } 
            // Split point reached, or one node is the ancestor of the other
            else {
                return curr;
            }
        }

        return null;
    }
}