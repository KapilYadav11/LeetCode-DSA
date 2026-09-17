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
    // Function to count nodes in a complete binary tree
    public int countNodes(TreeNode root) {
        // If tree is empty, return 0
        if (root == null) {
            return 0;
        }
        // Get left height
        int lh = findHeightLeft(root);
        // Get right height
        int rh = findHeightRight(root);
        // If heights match, use perfect binary tree formula
        if (lh == rh) {
            return (1 << lh) - 1;
        }
        // Otherwise, recursively count left and right subtrees
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // Helper to find height from leftmost path
    public int findHeightLeft(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    // Helper to find height from rightmost path
    public int findHeightRight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}