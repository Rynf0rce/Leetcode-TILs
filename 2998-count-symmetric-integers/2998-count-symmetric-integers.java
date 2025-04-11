class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        
        // Iterate over each number in the range [low, high]
        for (int x = low; x <= high; x++) {
            // Convert number to string to process digits
            String num = String.valueOf(x);
            int len = num.length();
            
            // Skip if the number of digits is odd
            if (len % 2 != 0) continue;
            
            // Number of digits in each half
            int n = len / 2;
            
            // Compute sum of first n digits
            int firstHalfSum = 0;
            for (int i = 0; i < n; i++) {
                firstHalfSum += num.charAt(i) - '0';
            }
            
            // Compute sum of last n digits
            int secondHalfSum = 0;
            for (int i = n; i < len; i++) {
                secondHalfSum += num.charAt(i) - '0';
            }
            
            // If sums are equal, the number is symmetric
            if (firstHalfSum == secondHalfSum) {
                count++;
            }
        }
        
        return count;
    }
}