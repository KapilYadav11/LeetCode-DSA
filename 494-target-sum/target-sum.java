import java.util.Arrays;

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Edge case checks
        if (totalSum < Math.abs(target) || (totalSum - target) % 2 != 0) {
            return 0;
        }

        int subsetSum = (totalSum - target) / 2;
        int n = nums.length;

        // dp[i][j] stores the number of subsets using elements from index 0 to i with sum equal to j
        int[][] dp = new int[n][subsetSum + 1];

        // Base case initialization for index 0
        if (nums[0] == 0) {
            dp[0][0] = 2; // Two choices for '0': pick (+0) or not pick (-0)
        } else {
            dp[0][0] = 1; // Picking empty set gives sum 0
            if (nums[0] <= subsetSum) {
                dp[0][nums[0]] = 1; // Picking nums[0]
            }
        }

        // Fill the DP table
        for (int idx = 1; idx < n; idx++) {
            for (int sum = 0; sum <= subsetSum; sum++) {
                int notPick = dp[idx - 1][sum];
                int pick = 0;
                if (nums[idx] <= sum) {
                    pick = dp[idx - 1][sum - nums[idx]];
                }

                dp[idx][sum] = pick + notPick;
            }
        }

        return dp[n - 1][subsetSum];
    }
}