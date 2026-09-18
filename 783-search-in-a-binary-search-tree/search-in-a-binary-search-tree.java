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
    public TreeNode searchBST(TreeNode root, int val) {

        // Traverse the tree while node is not null and value not matched
        while (root != null && root.val != val) {

            // If val is less than node's value, go to left subtree
            if (val < root.val) {
                root = root.left;
            }

            // Else go to right subtree
            else {
                root = root.right;
            }
        }

        // Return the node if found, otherwise null
        return root;
    }
}
