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

    // Previous node reference
    // Reverse preorder traversal mein processed node ko store karega
    TreeNode prev = null;

    // Function to flatten Binary Tree into Linked List
    public void flatten(TreeNode root) {

        // Agar current node null hai,
        // to kuch karne ki zarurat nahi hai
        if (root == null) {
            return;
        }

        // Pehle right subtree ko flatten karo
        flatten(root.right);

        // Uske baad left subtree ko flatten karo
        flatten(root.left);

        // Current node ke right mein
        // previously processed node ko connect karo
        root.right = prev;

        // Current node ka left pointer null karo
        root.left = null;

        // Ab current node previous node ban jayega
        prev = root;
    }
}
