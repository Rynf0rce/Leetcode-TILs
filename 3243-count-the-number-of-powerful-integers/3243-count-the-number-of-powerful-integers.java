class Solution {
    private String s;
    private int limit;
    private Long[][][] dp;
    
    // Convert number to string and compute count of powerful integers <= x
    private long count(long x) {
        if (x < 0) return 0;
        String num = String.valueOf(x);
        int len = num.length();
        
        // Initialize DP array: position, tight, nonZero
        dp = new Long[len][2][2];
        return dpHelper(0, 1, 0, num);
    }
    
    // Digit DP function
    private long dpHelper(int pos, int tight, int nonZero, String num) {
        if (pos == num.length()) {
            // Check if the number is valid: must have at least s.length() digits and be non-zero
            return nonZero == 1 && pos >= s.length() ? 1 : 0;
        }
        
        // Check if already computed
        if (dp[pos][tight][nonZero] != null) {
            return dp[pos][tight][nonZero];
        }
        
        long ans = 0;
        // Determine the upper bound for the current digit
        int maxDigit = tight == 1 ? (num.charAt(pos) - '0') : 9;
        
        // Check if we're in the suffix part
        int sIndex = s.length() - (num.length() - pos); // Position in s to match
        if (sIndex >= 0) {
            // We must match the digit of s at this position
            int sDigit = s.charAt(sIndex) - '0';
            if (sDigit <= maxDigit) {
                int newTight = tight == 1 && sDigit == maxDigit ? 1 : 0;
                int newNonZero = nonZero == 1 || sDigit > 0 ? 1 : 0;
                ans += dpHelper(pos + 1, newTight, newNonZero, num);
            }
        } else {
            // We're in the prefix part, digits must be <= limit
            for (int digit = 0; digit <= Math.min(maxDigit, limit); digit++) {
                int newTight = tight == 1 && digit == maxDigit ? 1 : 0;
                int newNonZero = nonZero == 1 || digit > 0 ? 1 : 0;
                ans += dpHelper(pos + 1, newTight, newNonZero, num);
            }
        }
        
        dp[pos][tight][nonZero] = ans;
        return ans;
    }
    
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        this.s = s;
        this.limit = limit;
        
        // Check if s itself is valid (all digits <= limit)
        for (char c : s.toCharArray()) {
            if (c - '0' > limit) return 0;
        }
        
        // Answer = count(finish) - count(start - 1)
        return count(finish) - count(start - 1);
    }
}