class Solution {
    public int numEnclaves(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 || j == 0 || i == n-1 || j == m-1){ //check kara if current lies on boundary
                    if(grid[i][j] == 1 && !vis[i][j]){//agar boundary cell is land , mark visited and push to the queue
                        vis[i][j] = true;
                        q.add(new int[]{i, j});
                    }
                }
            }
        }

        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];

            for(int k = 0; k < 4; k++){
                int nrow = row + delRow[k];
                int ncol = col + delCol[k];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && !vis[nrow][ncol] && grid[nrow][ncol] == 1){
                    vis[nrow][ncol] = true;
                    q.add(new int[]{nrow, ncol});
                }
            }
        }
        
        // Count land cells that are not visited (i.e., enclaves)
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1 && !vis[i][j]){
                    cnt++;
                }
            }
        }
        return cnt++;
    }
}