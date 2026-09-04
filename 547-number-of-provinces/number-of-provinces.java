class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] vis = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            // Agar city pehle visit nahi hui, naya province mila
            if (!vis[i]) {
                provinces++;
                bfs(i, isConnected, vis);
            }
        }
        return provinces;
    }

    private void bfs(int start, int[][] isConnected, boolean[] vis) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();

            // Direct matrix row traverse karke padosi dhoondho
            for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
                if (isConnected[node][neighbor] == 1 && !vis[neighbor]) {
                    vis[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }
}