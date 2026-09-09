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
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // LinkedList allows O(1) prepend operations (addFirst)
        LinkedList<Integer> result = new LinkedList<>();
        // ArrayDeque is faster and unsynchronized compared to legacy java.util.Stack
        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);

        // Modified preorder: Root -> Right -> Left
        // Inserting at the front reverses it into Postorder: Left -> Right -> Root
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            result.addFirst(curr.val);

            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }

        return result;
    }
}