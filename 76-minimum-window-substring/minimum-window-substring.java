class Solution {
    public static String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();

        if (n < m) return "";

        int[] hash = new int[128]; // ASCII up to 127 covers standard letters/symbols

        for (char c : t.toCharArray()) {
            hash[c]++;
        }

        int l = 0, r = 0;
        int minLen = Integer.MAX_VALUE;
        int sIndex = -1;
        int count = 0;

        while (r < n) {
            char rightChar = s.charAt(r);

            // If the character is required, increment matching count
            if (hash[rightChar] > 0) {
                count++;
            }
            hash[rightChar]--; // Include rightChar in window

            // When all required characters are matched, shrink from left
            while (count == m) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    sIndex = l;
                }

                char leftChar = s.charAt(l);
                hash[leftChar]++;

                // If count becomes positive, a critical character from 't' was removed
                if (hash[leftChar] > 0) {
                    count--;
                }
                l++;
            }
            r++;
        }

        return sIndex == -1 ? "" : s.substring(sIndex, sIndex + minLen);
    }
}