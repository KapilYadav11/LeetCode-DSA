class Solution {

    // Main function of LeetCode 1976
    // Goal:
    // Source node 0 se Destination node n-1 tak
    // shortest time mein pahunchne ke kitne different ways hain
    public int countPaths(int n, int[][] roads) {

        // ---------------------------------------------------------
        // STEP 1: Create Adjacency List
        // ---------------------------------------------------------
        // Graph ko adjacency list ke form mein store karenge.
        //
        // adj.get(u) ke andar:
        // {v, time}
        //
        // iska matlab:
        // u se v tak jaane mein 'time' lagta hai.
        //
        // Example:
        // road = {0, 1, 2}
        // means:
        // 0 ----2----> 1
        // ---------------------------------------------------------

        List<List<int[]>> adj = new ArrayList<>();

        // Har node ke liye ek empty list create karo
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // ---------------------------------------------------------
        // STEP 2: Add Roads to Graph
        // ---------------------------------------------------------
        // Roads undirected hain.
        //
        // Agar road hai:
        // 0 --5-- 4
        //
        // toh:
        // 0 se 4 ja sakte hain
        // AND
        // 4 se 0 ja sakte hain
        // ---------------------------------------------------------

        for (int[] road : roads) {

            int u = road[0];       // Starting node
            int v = road[1];       // Destination node
            int time = road[2];    // Travel time

            // u -> v
            adj.get(u).add(new int[]{v, time});

            // v -> u
            adj.get(v).add(new int[]{u, time});
        }


        // ---------------------------------------------------------
        // STEP 3: Priority Queue / Min Heap
        // ---------------------------------------------------------
        // Hum Dijkstra's Algorithm use kar rahe hain.
        //
        // PriorityQueue hamesha minimum distance wale node
        // ko pehle nikalegi.
        //
        // Har element:
        // {distance, node}
        //
        // Example:
        // {5, 3}
        // means:
        // node 3 tak current shortest distance = 5
        // ---------------------------------------------------------

        PriorityQueue<long[]> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));


        // ---------------------------------------------------------
        // STEP 4: Distance Array
        // ---------------------------------------------------------
        // dist[i] = source node 0 se node i tak
        // minimum distance/time.
        //
        // Initially kisi node tak nahi pahunche hain,
        // isliye sabko infinity rakhenge.
        // ---------------------------------------------------------

        long[] dist = new long[n];

        Arrays.fill(dist, Long.MAX_VALUE);


        // ---------------------------------------------------------
        // STEP 5: Ways Array
        // ---------------------------------------------------------
        // ways[i] = source 0 se node i tak pahunchne ke
        // shortest paths ki number.
        //
        // Example:
        //
        // 0 -> 1 -> 3
        // 0 -> 2 -> 3
        //
        // Agar dono ka distance same hai,
        // toh ways[3] = 2
        // ---------------------------------------------------------

        int[] ways = new int[n];


        // ---------------------------------------------------------
        // STEP 6: Starting Node
        // ---------------------------------------------------------
        // Source = 0
        //
        // 0 se 0 tak distance = 0
        //
        // 0 se 0 pahunchne ka ek way hai:
        // khud wahi node par hona.
        // ---------------------------------------------------------

        dist[0] = 0;
        ways[0] = 1;


        // Source node ko Priority Queue mein add karo
        //
        // {0, 0}
        //  ↑  ↑
        //  |  node
        // distance
        pq.offer(new long[]{0, 0});


        // LeetCode ke according answer ko
        // 10^9 + 7 se modulo karna hai.
        int mod = 1_000_000_007;


        // ---------------------------------------------------------
        // STEP 7: Dijkstra Algorithm
        // ---------------------------------------------------------

        while (!pq.isEmpty()) {

            // Priority Queue se minimum distance wala node nikalo
            long[] current = pq.poll();

            // Current node ki distance
            long dis = current[0];

            // Current node
            int node = (int) current[1];


            // -----------------------------------------------------
            // IMPORTANT:
            // Priority Queue mein same node multiple times aa sakta
            // hai.
            //
            // Example:
            // Pehle node 3 distance 10 se mila
            // Baad mein node 3 distance 5 se mila
            //
            // Queue mein purana {10, 3} bhi ho sakta hai.
            //
            // Agar current distance actual shortest distance se
            // greater hai, toh is entry ko ignore kar do.
            // -----------------------------------------------------

            if (dis > dist[node]) {
                continue;
            }


            // -----------------------------------------------------
            // STEP 8: Explore All Neighbours
            // -----------------------------------------------------
            // Current node se jitne bhi connected nodes hain,
            // unko check karo.
            // -----------------------------------------------------

            for (int[] edge : adj.get(node)) {

                // Connected neighbour
                int adjNode = edge[0];

                // Current node se neighbour tak travel time
                int edgeWeight = edge[1];


                // -------------------------------------------------
                // Current node tak distance:
                // dis
                //
                // Current -> neighbour ka distance:
                // edgeWeight
                //
                // Total:
                // dis + edgeWeight
                // -------------------------------------------------

                long newDist = dis + edgeWeight;


                // =================================================
                // CASE 1:
                // Humein ek SHORTER path mila
                // =================================================

                if (newDist < dist[adjNode]) {

                    // New shortest distance update karo
                    dist[adjNode] = newDist;


                    // ------------------------------------------------
                    // Abhi jo shortest path node tak mila hai,
                    // uske jitne ways hain,
                    // wahi neighbour ke ways honge.
                    //
                    // Example:
                    //
                    // 0 -> 1
                    // 0 -> 2
                    //
                    // Agar node 1 tak 3 shortest ways hain
                    // aur 1 -> 3 se new shortest path mila,
                    //
                    // toh:
                    // ways[3] = ways[1]
                    // ------------------------------------------------

                    ways[adjNode] = ways[node];


                    // New distance ke saath neighbour ko
                    // Priority Queue mein add karo
                    pq.offer(new long[]{newDist, adjNode});
                }


                // =================================================
                // CASE 2:
                // Ek AUR shortest path mila
                // =================================================

                else if (newDist == dist[adjNode]) {

                    // ------------------------------------------------
                    // Current node se neighbour tak ek aur path mila
                    // jiska distance exactly shortest distance ke
                    // equal hai.
                    //
                    // Isliye current node ke ways ko neighbour
                    // ke existing ways mein add karenge.
                    //
                    // Example:
                    //
                    // Path 1:
                    // 0 -> 1 -> 3
                    //
                    // Path 2:
                    // 0 -> 2 -> 3
                    //
                    // Dono ka distance same hai.
                    //
                    // Therefore:
                    // ways[3] = ways[1] + ways[2]
                    // ------------------------------------------------

                    ways[adjNode] =
                        (ways[adjNode] + ways[node]) % mod;
                }
            }
        }


        // ---------------------------------------------------------
        // STEP 9: Return Answer
        // ---------------------------------------------------------
        // Destination node = n - 1
        //
        // ways[n - 1] mein source 0 se destination tak
        // shortest paths ki total number stored hai.
        // ---------------------------------------------------------

        return ways[n - 1];
    }
}