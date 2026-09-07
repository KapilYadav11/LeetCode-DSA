class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;       
        int m = mat[0].length;    

        int[][] vis = new int[n][m];
        int[][] dist = new int[n][m];

        Queue<int[]> q = new LinkedList<>();

        // Initialize queue with all cells containing 0 instead of 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[]{i, j, 0}); 
                    vis[i][j] = 1; 
                } else {
                    vis[i][j] = 0; 
                }
            }
        }

        // Directions: Up, Right, Down, Left
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        // BFS traversal
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0];
            int col = cell[1];
            int steps = cell[2];

            dist[row][col] = steps; 

            // Explore all 4 directions
            for (int i = 0; i < 4; i++) {
                int nrow = row + delRow[i];
                int ncol = col + delCol[i];

                // Check boundaries and unvisited cells
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0) {
                    vis[nrow][ncol] = 1; 
                    q.add(new int[]{nrow, ncol, steps + 1}); 
                }
            }
        }
        
        return dist;
    }
}