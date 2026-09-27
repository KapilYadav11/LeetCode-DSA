class Solution {

    public void solve(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        // Top and bottom boundaries
        for (int col = 0; col < cols; col++) {

            if (board[0][col] == 'O') {
                dfs(0, col, board);
            }

            if (board[rows - 1][col] == 'O') {
                dfs(rows - 1, col, board);
            }
        }

        // Left and right boundaries
        for (int row = 0; row < rows; row++) {

            if (board[row][0] == 'O') {
                dfs(row, 0, board);
            }

            if (board[row][cols - 1] == 'O') {
                dfs(row, cols - 1, board);
            }
        }

        // Convert surrounded O -> X
        // Restore safe S -> O
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (board[row][col] == 'O') {
                    board[row][col] = 'X';
                }

                else if (board[row][col] == 'S') {
                    board[row][col] = 'O';
                }
            }
        }
    }

    private void dfs(int row, int col, char[][] board) {

        if (row < 0 ||
            row >= board.length ||
            col < 0 ||
            col >= board[0].length ||
            board[row][col] != 'O') {
            return;
        }

        // Mark as safe / visited
        board[row][col] = 'S';

        dfs(row - 1, col, board); // up
        dfs(row + 1, col, board); // down
        dfs(row, col - 1, board); // left
        dfs(row, col + 1, board); // right
    }
}