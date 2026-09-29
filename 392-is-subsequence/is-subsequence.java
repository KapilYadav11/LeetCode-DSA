class Solution {

    public boolean isSubsequence(String s, String t) {

        int n = s.length();
        int m = t.length();

        // dp[i][j]:
        // Can s[0...i] be formed as a subsequence of t[0...j]?
        Boolean[][] dp = new Boolean[n][m];

        return solve(n - 1, m - 1, s, t, dp);
    }

    private boolean solve(int i, int j, String s, String t, Boolean[][] dp) {

        // All characters of s have been matched
        if (i < 0) {
            return true;
        }

        // t is finished but s still has characters
        if (j < 0) {
            return false;
        }

        // Already calculated
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        // Characters match
        if (s.charAt(i) == t.charAt(j)) {
            return dp[i][j] =
                    solve(i - 1, j - 1, s, t, dp);
        }

        // Characters don't match:
        // Skip current character of t
        return dp[i][j] =
                solve(i, j - 1, s, t, dp);
    }
}