class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) return 0;

        // Sort intervals based on their end times in ascending order
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int count = 1; // Count of non-overlapping intervals kept
        int lastEndTime = intervals[0][1];

        // Iterate through intervals and count non-overlapping ones
        for (int i = 1; i < n; i++) {
            // Note: If start time >= last end time, there is NO overlap
            if (intervals[i][0] >= lastEndTime) {
                count++;
                lastEndTime = intervals[i][1];
            }
        }

        // Minimum removals = Total intervals - Maximum non-overlapping intervals
        return n - count;
    }
}