class Solution {
    public boolean lemonadeChange(int[] bills) {
        int count5 = 0;  // $5 ke bills ka count kara ya pr 
        int count10 = 0; // $10 ke bills kara ya pr count
        // 20 count hum ne nhi baanya kyunki 20 ka change dene me kabhi kaam nhi aata

        for (int bill : bills) {
            // Case 1: Customer ne $5 ka bill diya
            if (bill == 5) {
                count5++;
            } 
            // Case 2: Customer ne $10 ka bill diya 
            else if (bill == 10) {
                if (count5 > 0) { // $5 ka change dena zaroori hai
                    count5--;
                    count10++;
                } else {
                    return false; // Change nahi de sakte
                }
            } 
            // Case 3: Customer ne $20 ka bill diya
            else { 
                // Greedy Step: Pehle $10 + $5 dene ki koshish karo
                if (count10 > 0 && count5 > 0) {
                    count10--;
                    count5--;
                } 
                // Backup: Agar $10 nahi hai, toh 3 x $5 do
                else if (count5 >= 3) {
                    count5 -= 3;
                } 
                // Agar dono tarike fail ho gaye
                else {
                    return false;
                }
            }
        }

        return true; // Sabhi customers ko successfully change de diya
    }
}