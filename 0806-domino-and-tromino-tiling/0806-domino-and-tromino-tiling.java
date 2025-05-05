class Solution {
    public int numTilings(int n) {
        final int MOD = 1_000_000_007;

        if (n <= 1) {
            return n;
        }

        long dp_prevprev = 1;
        long dp_prev = 1;
        long tPartial_prev = 0;
        
        for (int i = 2; i <= n; i++) {
            long tPartial_curr = (dp_prevprev + tPartial_prev) % MOD;
            
            long dp_curr = (dp_prev + dp_prevprev + 2 * tPartial_prev) % MOD;
            
            dp_prevprev = dp_prev;
            dp_prev = dp_curr;
            tPartial_prev = tPartial_curr;
        }
        
        return (int) dp_prev;
    }
}