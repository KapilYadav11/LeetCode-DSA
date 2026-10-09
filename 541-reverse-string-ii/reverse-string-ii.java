class Solution {
    public String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        int n = a.length;

        for (int i = 0; i < n; i += 2 * k) {
            int left = i;
            // Handle remaining characters if fewer than k
            int right = Math.min(i + k - 1, n - 1);
            
            // Reverse the first k characters of the current 2k block
            while (left < right) {
                char temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left++;
                right--;
            }
        }

        return new String(a);
    }
}