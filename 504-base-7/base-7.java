class Solution {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        boolean isNegative = num < 0;
        int n = Math.abs(num);
        
        while (n > 0) {
            sb.append(n % 7);
            n /= 7;
        }
        
        if (isNegative) {
            sb.append("-");
        }
        
        return sb.reverse().toString();
    }
}