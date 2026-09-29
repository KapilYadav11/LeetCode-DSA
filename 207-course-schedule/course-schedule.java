class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // [a, b] means b -> a
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];

            adj.get(prerequisite).add(course);
        }

        // 0 = not visited
        // 1 = currently visiting
        // 2 = completely visited
        int[] state = new int[numCourses];

        // Check every course
        for (int i = 0; i < numCourses; i++) {

            if (state[i] == 0) {
                if (hasCycle(i, adj, state)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasCycle(
        int current,
        List<List<Integer>> adj,
        int[] state
    ) {

        // Current node is already in current DFS path
        if (state[current] == 1) {
            return true;
        }

        // Already completely processed
        if (state[current] == 2) {
            return false;
        }

        // Mark as currently visiting
        state[current] = 1;

        // Visit all neighbours
        for (int next : adj.get(current)) {

            if (hasCycle(next, adj, state)) {
                return true;
            }
        }

        // DFS for this node is completely finished
        state[current] = 2;

        return false;
    }
}