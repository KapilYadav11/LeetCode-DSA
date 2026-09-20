class Solution {

    public int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;

        int[][] dp = new int[n][n];

        // Base case: first row
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        // Fill remaining rows
        for (int i = 1; i < n; i++) {

            for (int j = 0; j < n; j++) {

                int up = dp[i - 1][j];

                int upLeft = Integer.MAX_VALUE;

                if (j > 0) {
                    upLeft = dp[i - 1][j - 1];
                }

                int upRight = Integer.MAX_VALUE;

                if (j < n - 1) {
                    upRight = dp[i - 1][j + 1];
                }

                dp[i][j] = matrix[i][j]
                         + Math.min(
                             up,
                             Math.min(upLeft, upRight)
                         );
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++) {
            answer = Math.min(answer, dp[n - 1][j]);
        }

        return answer;
    }
}