class Solution {
    private void dfs(int row, int col, int[][] vis, char[][] mat, int[] dr, int[] dc){
        vis[row][col] = 1;
        int n = mat.length;
        int m = mat[0].length;

        for(int k = 0; k < 4; k++){
            //next cell compute kara
            int nr = row + dr[k];
            int nc = col + dc[k];

            if(nr >= 0 && nr < n && nc >= 0 && nc < m &&
               vis[nr][nc] == 0 && mat[nr][nc] == 'O'){
                dfs(nr, nc, vis, mat, dr, dc);
               }
        }
    }
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0){
            return;
        }
        int n = board.length;
        int m = board[0].length;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int[][] vis = new int[n][m];

        for(int j = 0; j < m; j++){// isme traverse kr rahe hai first and last row ko
            // DFS from top boundary 'O'
            if(vis[0][j] == 0 && board[0][j] == 'O'){
                dfs(0, j, vis, board, dr, dc);
            }

            //DFS from bottom boundary 'O'
            if(vis[n-1][j] == 0 && board[n-1][j] == 'O'){
                dfs(n-1, j, vis, board, dr, dc);
            }
        }

        for(int i = 0; i < n; i++){// isme hum first and last column ko traverse kr rahe hain
            
            // DFS from the left boundary 'O'
            if(vis[i][0] == 0 && board[i][0] == 'O'){
                dfs(i, 0, vis, board, dr, dc);
            }

            //DFS from right boundary 'O'
            if(vis[i][m-1] == 0 && board[i][m-1] == 'O'){
                dfs(i, m-1, vis, board, dr, dc);
            }
        }

        //ab isme flip kr sabhi unvisited 'O' to 'X'
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){

                //convert enclosed 'O' to 'X'
                if(vis[i][j] == 0 && board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
}