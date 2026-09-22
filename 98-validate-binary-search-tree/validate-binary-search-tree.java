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
public class Solution {

    public boolean isValidBST(TreeNode root) {
        // Pass initial range: [Long.MIN_VALUE, Long.MAX_VALUE] 
        // to handle integer edge cases cleanly
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        // Base case: Empty tree/leaf reached
        if (root == null) {
            return true;
        }

        // Check if current node's value falls strictly within the allowed range
        if (root.val <= minVal || root.val >= maxVal) {
            return false;
        }

        // Left child must be < root.val  --> update maxVal to root.val
        // Right child must be > root.val --> update minVal to root.val
        return isValidBST(root.left, minVal, root.val) 
            && isValidBST(root.right, root.val, maxVal);
    }
}