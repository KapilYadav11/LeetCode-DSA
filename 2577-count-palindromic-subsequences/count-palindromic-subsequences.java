class Solution {
    private int MOD = 1000000007;
    private Integer[][][][] memo;
    private char[] sArr;

    public int countPalindromes(String s) {
        sArr = s.toCharArray();
        int n = s.length();
        
        // Dimensions:
        // 1. Current index in string (0 to n)
        // 2. Length of subsequence built so far (0 to 5)
        // 3. First chosen digit d1 (0-9, 10 for unassigned)
        // 4. Second chosen digit d2 (0-9, 10 for unassigned)
        memo = new Integer[n][6][11][11];
        
        // Start recursion from index 0, length 0, with unassigned d1 and d2 (represented by 10)
        return solve(0, 0, 10, 10);
    }

    private int solve(int i, int len, int d1, int d2) {
        // Base Case 1: Successfully built a 5-length palindrome
        if (len == 5) return 1;
        
        // Base Case 2: Reached the end of the string without reaching length 5
        if (i == sArr.length) return 0;

        // Return memoized result if already computed
        if (memo[i][len][d1][d2] != null) {
            return memo[i][len][d1][d2];
        }

        // Recursive Choice 1: Skip the current character
        int ans = solve(i + 1, len, d1, d2);
        
        int c = sArr[i] - '0';

        // Recursive Choice 2: Pick the current character (if valid for our palindrome)
        if (len == 0) {
            // Picking the 1st character (determines d1)
            ans = (ans + solve(i + 1, 1, c, d2)) % MOD;
        } else if (len == 1) {
            // Picking the 2nd character (determines d2)
            ans = (ans + solve(i + 1, 2, d1, c)) % MOD;
        } else if (len == 2) {
            // Picking the 3rd character (middle character, can be anything)
            ans = (ans + solve(i + 1, 3, d1, d2)) % MOD;
        } else if (len == 3) {
            // Picking the 4th character, MUST match the 2nd character (d2)
            if (c == d2) {
                ans = (ans + solve(i + 1, 4, d1, d2)) % MOD;
            }
        } else if (len == 4) {
            // Picking the 5th character, MUST match the 1st character (d1)
            if (c == d1) {
                ans = (ans + solve(i + 1, 5, d1, d2)) % MOD;
            }
        }

        // Save result in memo and return
        return memo[i][len][d1][d2] = ans;
    }
}