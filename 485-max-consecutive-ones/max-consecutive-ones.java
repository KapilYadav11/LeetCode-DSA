class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int currentCount = 0;
        int maxCount = 0;

        for (int num : nums) {
            // A 1 extends the current streak.
            if (num == 1) {
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            }
            // A zero breaks the current streak.
            else {
                currentCount = 0;
            }
        }

        return maxCount;
    }
}

