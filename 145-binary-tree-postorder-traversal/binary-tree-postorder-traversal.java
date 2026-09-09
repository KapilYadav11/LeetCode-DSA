class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        
        // Base case: empty tree check
        if (root == null) {
            return postorder;
        }

        // Two stacks using modern Deque / ArrayDeque
        Deque<TreeNode> traversalStack = new ArrayDeque<>();
        Deque<TreeNode> resultStack = new ArrayDeque<>();

        traversalStack.push(root);

        // Modified Pre-order traversal: Root -> Right -> Left
        while (!traversalStack.isEmpty()) {
            TreeNode current = traversalStack.pop();
            resultStack.push(current);

            // Left pehle push hoga taaki pop hone par Right pehle nikle
            if (current.left != null) {
                traversalStack.push(current.left);
            }
            if (current.right != null) {
                traversalStack.push(current.right);
            }
        }

        // Result stack ko pop karke final postorder traversal banta hai: Left -> Right -> Root
        while (!resultStack.isEmpty()) {
            postorder.add(resultStack.pop().val);
        }

        return postorder;
    }
}