class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int initialColor = image[sr][sc];
        
        // If the starting pixel already has the target color, no change is needed
        if (initialColor == color) {
            return image;
        }
        
        dfs(image, sr, sc, initialColor, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int initialColor, int newColor) {
        // Boundary and color matching check
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != initialColor) {
            return;
        }

        // Color the pixel in-place
        image[r][c] = newColor;

        // Traverse 4 adjacent neighbors: Up, Down, Left, Right
        dfs(image, r - 1, c, initialColor, newColor);
        dfs(image, r + 1, c, initialColor, newColor);
        dfs(image, r, c - 1, initialColor, newColor);
        dfs(image, r, c + 1, initialColor, newColor);
    }
}