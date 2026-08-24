class Solution {
   public static String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();

        // Edge case: Agar s chhota hai t se
        if (n < m) return "";

        // Hash array ASCII characters ke frequency count ke liye
        int[] hash = new int[256];

        // 1. String 't' ke sare characters ki frequency map/hash me store karo
        for (int i = 0; i < m; i++) {
            hash[t.charAt(i)]++;
        }

        int l = 0, r = 0;
        int minLen = Integer.MAX_VALUE;
        int sIndex = -1;
        int count = 0; // Kitne valid characters window me match ho chuke hain

        // 2. Sliding Window (Two Pointers) 
        while (r < n) {
            char rightChar = s.charAt(r);

            // Agar element t ka hissa tha (hash > 0), to count badhao 
            if (hash[rightChar] > 0) {
                count++;
            }
            // Frequency kam karo kyunki ye window ka part ban gaya hai
            hash[rightChar]--;

            // Jab saare characters mil jaye (count == m) 
            while (count == m) {
                // Minimum length update karo agar current window choti hai
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    sIndex = l; // Substring ka starting index save karo 
                }

                // Window ko left side se shrink (chhota) karne ki koshish karo 
                char leftChar = s.charAt(l);
                hash[leftChar]++; // Window se nikal rahe hain to frequency wapis badhao

                // Agar frequency > 0 ho gayi, matlab t ka required character missing ho gaya
                if (hash[leftChar] > 0) {
                    count--;
                }
                l++; // Left pointer aage badhao
            }

            r++; // Right pointer aage badhao
        }

        // Agar koi valid window nahi mili
        if (sIndex == -1) return "";

        // Minimum Substring return karo
           return s.substring(sIndex, sIndex + minLen);
    }      
}