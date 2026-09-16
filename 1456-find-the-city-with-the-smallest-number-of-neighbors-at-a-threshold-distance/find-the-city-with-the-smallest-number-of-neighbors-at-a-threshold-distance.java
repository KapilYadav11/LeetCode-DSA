import java.util.*;

class Solution {

    // LeetCode 1334:
    // Find the city with the smallest number of cities
    // that can be reached within distanceThreshold.
    //
    // If multiple cities have the same minimum count,
    // return the city with the LARGEST index.
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        // ---------------------------------------------------------
        // STEP 1: Create distance matrix
        // ---------------------------------------------------------
        // dist[i][j] = city i se city j tak shortest distance
        //
        // Initially:
        // - Agar direct edge hai -> uska weight
        // - Agar direct edge nahi hai -> INF
        // - i se i ka distance -> 0
        // ---------------------------------------------------------

        int[][] dist = new int[n][n];

        // Initially sabhi distances ko infinity rakho
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }


        // ---------------------------------------------------------
        // STEP 2: Initialize direct edges
        // ---------------------------------------------------------
        // LeetCode mein graph UNDIRECTED hai.
        //
        // Agar:
        // 0 ----3---- 1
        //
        // Toh:
        // dist[0][1] = 3
        // dist[1][0] = 3
        // ---------------------------------------------------------

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            dist[u][v] = weight;
            dist[v][u] = weight;
        }


        // ---------------------------------------------------------
        // STEP 3: Distance from a city to itself = 0
        // ---------------------------------------------------------

        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }


        // ---------------------------------------------------------
        // STEP 4: Floyd-Warshall Algorithm
        // ---------------------------------------------------------
        //
        // Goal:
        // Har city se har doosri city tak SHORTEST distance
        // find karna.
        //
        // Formula:
        //
        // dist[i][j] =
        // min(
        //     dist[i][j],
        //     dist[i][k] + dist[k][j]
        // )
        //
        // Meaning:
        // i se j directly/old path se jaana better hai
        // YA
        // i -> k -> j ke through jaana better hai?
        //
        // Har possible intermediate city k ko try karte hain.
        // ---------------------------------------------------------

        for (int k = 0; k < n; k++) {

            // k = intermediate city

            for (int i = 0; i < n; i++) {

                // i = source city

                for (int j = 0; j < n; j++) {

                    // j = destination city

                    // Agar i -> k ya k -> j possible nahi hai,
                    // toh addition nahi kar sakte.
                    if (dist[i][k] != Integer.MAX_VALUE &&
                        dist[k][j] != Integer.MAX_VALUE) {

                        // i -> k -> j ka distance
                        int newDist = dist[i][k] + dist[k][j];

                        // Minimum distance store karo
                        dist[i][j] = Math.min(
                            dist[i][j],
                            newDist
                        );
                    }
                }
            }
        }


        // ---------------------------------------------------------
        // STEP 5: Find city having minimum reachable cities
        // ---------------------------------------------------------
        //
        // Har city ke liye count karenge:
        // Kitni cities aisi hain jahan tak distanceThreshold
        // ke andar pahunch sakte hain?
        // ---------------------------------------------------------

        int cityNo = -1;

        // Maximum possible count = n
        int cntCity = n;


        // Har city ko source maan kar check karo
        for (int city = 0; city < n; city++) {

            int cnt = 0;

            // Har doosri city check karo
            for (int adjCity = 0; adjCity < n; adjCity++) {

                // Agar city se adjCity tak shortest distance
                // threshold ke andar hai,
                // toh adjCity reachable hai.
                if (dist[city][adjCity] <= distanceThreshold) {
                    cnt++;
                }
            }


            // -----------------------------------------------------
            // IMPORTANT LEETCODE CONDITION
            // -----------------------------------------------------
            //
            // Agar current city ke reachable cities kam hain:
            //     cnt < cntCity
            //
            // OR
            //
            // Agar same number of reachable cities hain:
            //     cnt == cntCity
            //
            // Toh larger city index choose karna hai.
            //
            // Isliye <= use kar rahe hain.
            // -----------------------------------------------------

            if (cnt <= cntCity) {

                cntCity = cnt;
                cityNo = city;
            }
        }


        // Final answer
        return cityNo;
    }
}