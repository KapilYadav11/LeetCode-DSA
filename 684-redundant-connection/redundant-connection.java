class Solution {

    // Aapka Pair class (node aur parent tracks karne ke liye)
    static class Pair {
        int node;
        int parent;

        Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        // Dynamic adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Har edge ko ek-ek karke insert karenge
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // 1. Pehle check karo ki kya graph me abhi tak koi cycle hai agar hum 'u' se BFS start karein
            // Aur check karein ki kya 'v' tak pouch sakte hain
            if (hasPathBFS(u, v, adj, n)) {
                // Agar u se v pehle se connected hain, to ye edge redundant (cycle builder) hai
                return edge;
            }

            // 2. Agar path nahi hai, to graph me edge add kar do
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return new int[0];
    }

    // Bilkul aapke standard BFS cycle detection pattern par written code
    private boolean hasPathBFS(int start, int target, ArrayList<ArrayList<Integer>> adj, int n) {
        
        // Agar start node ki adjacency list hi empty hai, toh aage BFS ki zarurat nahi
        if (adj.get(start).isEmpty()) {
            return false;
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Pair> queue = new ArrayDeque<>();

        // BFS start point
        queue.add(new Pair(start, -1));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            int node = current.node;
            int parent = current.parent;

            // Agar target mil gaya, matlab path exist karta hai
            if (node == target) {
                return true;
            }

            for (int neighbour : adj.get(node)) {

                if (!visited[neighbour]) {

                    visited[neighbour] = true;
                    queue.add(new Pair(neighbour, node));

                } else if (neighbour != parent) {
                    // Agar already visited hai aur parent nahi hai -> Path/Cycle exists!
                    // Par Target reachability fast check ke liye upper check (node == target) kafi hai
                }
            }
        }

        return false;
    }
}