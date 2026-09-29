class Solution {

    public boolean isSubsequence(String s, String t) {

        int n = s.length();
        int m = t.length();

        boolean[][] dp = new boolean[n + 1][m + 1];

        // Empty s is a subsequence of any t
        for (int j = 0; j <= m; j++) {
            dp[0][j] = true;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                // Characters match
                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } 
                // Characters don't match
                else {

                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[n][m];
    }
}