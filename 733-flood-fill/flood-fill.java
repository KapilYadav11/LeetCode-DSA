class Solution {
    private void dfs(int row, int col, int[][] ans, int[][] image, int newColor, int[] delRow, int[] delCol, int iniColor) {
        // Current pixel ko newColor se fill karo
        ans[row][col] = newColor; 
        int n = image.length;
        int m = image[0].length; 
        
        // 4 directions me traverse karne ke liye loop: Up, Right, Down, Left
        for(int i = 0; i < 4; i++) {
            int nrow = row + delRow[i]; 
            int ncol = col + delCol[i]; 
            
            // Validity checks:
            // 1. Coordinates boundaries ke andar hone chahiye
            // 2. Original matrix me color initial color ke barabar hona chahiye
            // 3. ans matrix me wo already newColor se colored nahi hona chahiye
            if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && 
               image[nrow][ncol] == iniColor && ans[nrow][ncol] != newColor) {
                dfs(nrow, ncol, ans, image, newColor, delRow, delCol, iniColor); 
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // Starting pixel ka original color store karo
        int iniColor = image[sr][sc]; 
        
        // Original image ki copy banao taaki input data alter na ho
        int[][] ans = image; 
        
        // 4 directions ke offsets (Up, Right, Down, Left)
        int[] delRow = {-1, 0, +1, 0};
        int[] delCol = {0, +1, 0, -1}; 
        
        dfs(sr, sc, ans, image, newColor, delRow, delCol, iniColor); 
        return ans; 
    }
}