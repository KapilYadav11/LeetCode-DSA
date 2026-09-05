class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int numIslands = 0;
        int n = grid.length;
        int m = grid[0].length;
        
        // Grid traverse karein
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Agar cell '1' hai, toh yeh ek naya island hai
                if (grid[i][j] == '1') {
                    numIslands++; // Island ka count badha dein
                    // DFS se is pure island ko '0' mark kar dein taaki dobara count na ho
                    dfs(grid, i, j); 
                }
            }
        }
        
        return numIslands;
    }
    
    private void dfs(char[][] grid, int row, int col) {
        // Boundary check aur dekhein ki land ('1') hai ya nahi
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }
        
        // Current cell ko water ('0') bana dein taaki yeh visit ho jaye
        grid[row][col] = '0';
        
        // Chaaron directions mein DFS call karein
        dfs(grid, row - 1, col); // Up
        dfs(grid, row + 1, col); // Down
        dfs(grid, row, col - 1); // Left
        dfs(grid, row, col + 1); // Right
    }
}