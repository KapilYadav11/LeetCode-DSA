class Solution {
    // 1e9 overflow nahi hone dega
    private static final int INF = (int) 1e9;

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        int answer = INF;
        for (int j = 0; j < n; j++) {
            answer = Math.min(answer, solve(n - 1, j, matrix, dp));
        }

        return answer;
    }

    private int solve(int i, int j, int[][] matrix, int[][] dp) {
        // Out of boundary base case
        if (j < 0 || j >= matrix.length) {
            return INF;
        }

        // Base case: row 0
        if (i == 0) {
            return matrix[0][j];
        }

        // Memoization check
        if (dp[i][j] != INF) {
            return dp[i][j];
        }

        int up = solve(i - 1, j, matrix, dp);
        int upLeft = solve(i - 1, j - 1, matrix, dp);
        int upRight = solve(i - 1, j + 1, matrix, dp);

        return dp[i][j] = matrix[i][j] + Math.min(up, Math.min(upLeft, upRight));
    }
}