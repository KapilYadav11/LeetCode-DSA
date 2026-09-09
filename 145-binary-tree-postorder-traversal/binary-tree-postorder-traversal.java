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

        // Use Deque/ArrayDeque instead of legacy, synchronized java.util.Stack
        Deque<TreeNode> traversalStack = new ArrayDeque<>();
        Deque<TreeNode> resultStack = new ArrayDeque<>();

        traversalStack.push(root);

        // Process nodes: Root -> Right -> Left into traversalStack,
        // so resultStack stores: Root -> Right -> Left
        while (!traversalStack.isEmpty()) {
            TreeNode current = traversalStack.pop();
            resultStack.push(current);

            // Push left first so right is popped first from traversalStack
            if (current.left != null) {
                traversalStack.push(current.left);
            }
            if (current.right != null) {
                traversalStack.push(current.right);
            }
        }

        // Pre-allocate ArrayList capacity to avoid resizing
        List<Integer> postorder = new ArrayList<>(resultStack.size());

        // Popping resultStack produces: Left -> Right -> Root
        while (!resultStack.isEmpty()) {
            postorder.add(resultStack.pop().val);
        }

        return postorder;
    }
}