class Solution {

    public boolean isSubsequence(String s, String t) {

        int n = s.length();
        int m = t.length();

        boolean[] prev = new boolean[m + 1];

        // Empty s is a subsequence of any prefix of t
        for (int j = 0; j <= m; j++) {
            prev[j] = true;
        }

        for (int i = 1; i <= n; i++) {

            boolean[] curr = new boolean[m + 1];

            // s is non-empty, so it cannot be a subsequence
            // of empty t
            curr[0] = false;

            for (int j = 1; j <= m; j++) {

                // Characters match
                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                    curr[j] = prev[j - 1];

                } 
                // Characters don't match
                else {

                    curr[j] = curr[j - 1];
                }
            }

            prev = curr;
        }

        return prev[m];
    }
}