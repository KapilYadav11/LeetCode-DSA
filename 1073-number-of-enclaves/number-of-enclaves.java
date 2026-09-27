class Solution {

    public int numEnclaves(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        // Top and bottom boundaries
        for (int col = 0; col < cols; col++) {

            if (grid[0][col] == 1) {
                dfs(0, col, grid);
            }

            if (grid[rows - 1][col] == 1) {
                dfs(rows - 1, col, grid);
            }
        }

        // Left and right boundaries
        for (int row = 0; row < rows; row++) {

            if (grid[row][0] == 1) {
                dfs(row, 0, grid);
            }

            if (grid[row][cols - 1] == 1) {
                dfs(row, cols - 1, grid);
            }
        }

        // Count remaining land cells
        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (grid[row][col] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int row, int col, int[][] grid) {

        if (row < 0 ||
            row >= grid.length ||
            col < 0 ||
            col >= grid[0].length ||
            grid[row][col] == 0) {
            return;
        }

        // Mark as visited by converting 1 -> 0
        grid[row][col] = 0;

        dfs(row - 1, col, grid);
        dfs(row + 1, col, grid);
        dfs(row, col - 1, grid);
        dfs(row, col + 1, grid);
    }
}