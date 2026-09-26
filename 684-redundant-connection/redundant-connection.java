class Solution {

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        // Dynamic Adjacency List
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Har edge ko ek-ek karke process karenge
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            boolean[] visited = new boolean[n + 1];

            // 1. Check karo ki kya 'u' se 'v' tak pehle se koi path exist karta hai
            // DFS call me initial parent = -1 pass karenge
            if (dfs(u, v, -1, adj, visited)) {
                // Agar path pehle se hai, to ye edge redundant hai (cycle banayega)
                return edge;
            }

            // 2. Agar path nahi hai, to edge ko graph me add kar do
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return new int[0];
    }

    // DFS Helper Method using (node, parent) pattern
    private boolean dfs(int node, int target, int parent, 
                        ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        
        // Target node mil gaya
        if (node == target) {
            return true;
        }

        visited[node] = true;

        for (int neighbour : adj.get(node)) {

            // Case 1: Agar neighbor visited nahi hai -> Normal DFS Call
            if (!visited[neighbour]) {
                if (dfs(neighbour, target, node, adj, visited)) {
                    return true;
                }
            } 
            // Case 2: Agar neighbor visited hai aur parent nahi hai -> Cycle / Alternate Path
            else if (neighbour != parent) {
                // Pointing to an already visited non-parent node indicates a cycle/path
            }
        }

        return false;
    }
}