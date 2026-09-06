class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int totalRows = grid.length;
        int totalCols = grid[0].length;
        
        Queue<int[]> rottenQueue = new LinkedList<>();
        int freshOranges = 0;

        // Step 1: Saare initial rotten oranges queue me daalo aur fresh oranges count karo
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalCols; col++) {
                if (grid[row][col] == 2) {
                    rottenQueue.offer(new int[]{row, col});
                } else if (grid[row][col] == 1) {
                    freshOranges++;
                }
            }
        }

        // Agar shuruat me koi fresh orange hai hi nahi
        if (freshOranges == 0) return 0;

        int timeElapsed = 0;
        
        // 4 directions: Up, Down, Left, Right
        int[] rowOffset = {-1, 1, 0, 0};
        int[] colOffset = {0, 0, -1, 1};

        // Step 2: Level-by-level BFS traversal
        while (!rottenQueue.isEmpty() && freshOranges > 0) {
            int currentLevelSize = rottenQueue.size();
            timeElapsed++;

            for (int i = 0; i < currentLevelSize; i++) {
                int[] currentOrange = rottenQueue.poll();
                int currentRow = currentOrange[0];
                int currentCol = currentOrange[1];

                for (int direction = 0; direction < 4; direction++) {
                    int neighborRow = currentRow + rowOffset[direction];
                    int neighborCol = currentCol + colOffset[direction];

                    // Boundary check aur fresh orange ka check
                    boolean isInsideGrid = neighborRow >= 0 && neighborRow < totalRows 
                                        && neighborCol >= 0 && neighborCol < totalCols;

                    if (isInsideGrid && grid[neighborRow][neighborCol] == 1) {
                        grid[neighborRow][neighborCol] = 2; // Orange ko rotten mark karo
                        freshOranges--;
                        rottenQueue.offer(new int[]{neighborRow, neighborCol});
                    }
                }
            }
        }

        // Agar saare fresh oranges rot ho gaye to time return karo, warna -1
        return freshOranges == 0 ? timeElapsed : -1;
    }
}