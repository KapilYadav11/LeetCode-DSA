class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // c == '*'
                minOpen--; // treat '*' as ')'
                maxOpen++; // treat '*' as '('
            }

            // Agar maxOpen 0 se chota ho gaya, matlab invalid closing bracket ')' aa gaya hai
            if (maxOpen < 0) {
                return false;
            }

            // minOpen 0 se kam nahi ho sakta (negatives ko zero pe reset karte hain)
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // Agar minOpen == 0 hai, to saare brackets validly match hue hain
        return minOpen == 0;
    }
}