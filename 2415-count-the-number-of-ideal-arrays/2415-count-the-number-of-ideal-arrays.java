class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int idealArrays(int n, int maxValue) {
        // Precompute combinations up to necessary limit
        long[][] comb = new long[n + 14][15]; // Max power of prime < 10^4 is 13
        for (int i = 0; i < comb.length; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= Math.min(i, 14); j++) {
                comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]) % MOD;
            }
        }
        
        long result = 0;
        
        // For each possible ending value
        for (int val = 1; val <= maxValue; val++) {
            // Find prime factorization powers
            Map<Integer, Integer> primeFactors = getPrimeFactorization(val);
            
            // Apply the formula: product of C(n-1+e_i, e_i) for each prime factor
            long ways = 1;
            for (int power : primeFactors.values()) {
                ways = (ways * comb[n-1+power][power]) % MOD;
            }
            
            result = (result + ways) % MOD;
        }
        
        return (int)result;
    }
    
    private Map<Integer, Integer> getPrimeFactorization(int num) {
        Map<Integer, Integer> factors = new HashMap<>();
        
        for (int i = 2; i * i <= num; i++) {
            int count = 0;
            while (num % i == 0) {
                count++;
                num /= i;
            }
            if (count > 0) {
                factors.put(i, count);
            }
        }
        
        if (num > 1) {
            factors.put(num, 1);
        }
        
        return factors;
    }
}