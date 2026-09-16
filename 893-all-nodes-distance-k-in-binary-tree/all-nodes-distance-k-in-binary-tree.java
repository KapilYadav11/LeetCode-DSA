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

    // Main method to find nodes at distance K from target node
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (root == null) return new ArrayList<>();

        // Step 1: Build parent references using BFS
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        mapParents(root, parentMap);

        // Step 2: Perform BFS from target to find all nodes at distance K
        return bfsFromTarget(target, parentMap, k);
    }

    // Helper method to build the parent map using level-order traversal
    private void mapParents(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // Record left child's parent
            if (node.left != null) {
                parentMap.put(node.left, node);
                queue.offer(node.left);
            }

            // Record right child's parent
            if (node.right != null) {
                parentMap.put(node.right, node);
                queue.offer(node.right);
            }
        }
    }

    // BFS starting from the target node to collect nodes at distance K
    private List<Integer> bfsFromTarget(TreeNode target, Map<TreeNode, TreeNode> parentMap, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);

        int currentLevel = 0;

        // Standard level-order BFS
        while (!queue.isEmpty()) {
            int size = queue.size();

            // Stop traversal once we reach distance K
            if (currentLevel++ == k) break;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Explore left child
                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.offer(node.left);
                }

                // Explore right child
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.offer(node.right);
                }

                // Explore parent from map
                if (parentMap.containsKey(node) && !visited.contains(parentMap.get(node))) {
                    visited.add(parentMap.get(node));
                    queue.offer(parentMap.get(node));
                }
            }
        }

        // All nodes left in queue are exactly K distance from target
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().val);
        }

        return result;
    }
}