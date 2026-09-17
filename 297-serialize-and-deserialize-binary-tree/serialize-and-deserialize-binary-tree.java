/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 public class Codec {

    // SERIALIZE
    // Binary Tree -> String

    public String serialize(TreeNode root) {

        // Agar tree empty hai, to empty string return karo
        if (root == null) {
            return "";
        }

        // Serialized tree ko store karne ke liye StringBuilder
        StringBuilder s = new StringBuilder();

        // BFS / Level Order Traversal ke liye Queue
        Queue<TreeNode> q = new LinkedList<>();

        // Sabse pehle root ko queue mein add karo
        q.offer(root);

        // Jab tak queue empty nahi hoti, traversal continue karo
        while (!q.isEmpty()) {

            // Queue ke front se current node nikalo
            TreeNode curNode = q.poll();

            // Agar current node null hai
            if (curNode == null) {

                // Null ko "#" ke form mein store karo
                s.append("#,");
            }

            // Agar current node null nahi hai
            else {

                // Current node ki value ko StringBuilder mein add karo
                s.append(curNode.val).append(",");

                // Current node ka left child queue mein add karo
                q.offer(curNode.left);

                // Current node ka right child queue mein add karo
                q.offer(curNode.right);
            }
        }

        // Complete serialized string return karo
        return s.toString();
    }

    // DESERIALIZE
    // String -> Binary Tree
    public TreeNode deserialize(String data) {

        // Agar data empty ya null hai, to tree bhi empty hai
        if (data == null || data.isEmpty()) {
            return null;
        }

        // String ko comma ke basis par split karke array banao
        String[] values = data.split(",");

        // Array ka first element root node ki value hogi
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));

        // Tree ko level-order mein reconstruct karne ke liye Queue
        Queue<TreeNode> q = new LinkedList<>();

        // Root ko queue mein add karo
        q.offer(root);

        // i batayega ki values array mein hum kis position par hain
        int i = 1;

        // Jab tak queue mein nodes hain aur array mein values hain
        while (!q.isEmpty() && i < values.length) {

            // Queue se ek parent node nikalo
            TreeNode node = q.poll();

            // LEFT CHILD
            // Check karo ki current value null node "#" nahi hai
            if (!values[i].equals("#")) {

                // String value ko integer mein convert karke new node banao
                TreeNode leftNode =
                    new TreeNode(Integer.parseInt(values[i]));

                // New node ko parent ka left child banao
                node.left = leftNode;

                // Left child ko queue mein add karo
                // Kyunki future mein iska bhi child create karna hai
                q.offer(leftNode);
            }

            // Ab next value par move karo
            i++;

            // RIGHT CHILD
            // Check karo ki array ke andar value available hai
            // aur value "#" nahi hai
            if (i < values.length && !values[i].equals("#")) {

                // String value ko integer mein convert karke new node banao
                TreeNode rightNode =
                    new TreeNode(Integer.parseInt(values[i]));

                // New node ko parent ka right child banao
                node.right = rightNode;

                // Right child ko queue mein add karo
                q.offer(rightNode);
            }

            // Next value par move karo
            i++;
        }

        // Reconstructed tree ka root return karo
        return root;
    }
}






// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));