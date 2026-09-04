class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] vis = new boolean[n];
        int provinces = 0;

        // Har city par check karo
        for (int i = 0; i < n; i++) {
            // Agar city pehle kisi group me visit nahi hui
            if (!vis[i]) {
                provinces++; // Naya province mila
                dfs(i, isConnected, vis); // Uske saare direct/indirect dosto ko mark karo
            }
        }

        return provinces;
    }

    private void dfs(int node, int[][] isConnected, boolean[] vis) {
        vis[node] = true;

        // Current city (node) ke saare padosi check karo
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            // Connection hai (1) aur abhi tak visit nahi hua
            if (isConnected[node][neighbor] == 1 && !vis[neighbor]) {
                dfs(neighbor, isConnected, vis); // Depth me jao
            }
        }
    }
}