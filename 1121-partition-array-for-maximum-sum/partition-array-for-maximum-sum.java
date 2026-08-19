class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        
        // dp[i] store karega index 'i' se end tak ka result
        int[] dp = new int[n];
        Arrays.fill(dp, -1); // Initialize DP table with -1
        
        return solve(0, arr, k, dp);
    }

    private int solve(int ind, int[] arr, int k, int[] dp) {
        int n = arr.length;

        // Base Case: Agar index array ke bahar nikal jaye
        if (ind == n) return 0;

        // Overlapping Subproblem: Agar pehle se calculate ho chuka hai
        if (dp[ind] != -1) return dp[ind];

        int len = 0;
        int maxi = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;

        // Index 'ind' se max 'k' length tak ke partitions try karte hain
        for (int j = ind; j < Math.min(n, ind + k); j++) {
            len++;
            maxi = Math.max(maxi, arr[j]);

            // Current Partition Sum + Baaki array ka Recursion Call
            int sum = (len * maxi) + solve(j + 1, arr, k, dp);

            maxAns = Math.max(maxAns, sum);
        }

        // Return karne se pehle DP array me result store kar lete hain
        return dp[ind] = maxAns;
    }
}