class Solution {
    // ëª¨ëë¡ ì°ì°ì ì¬ì©í  ê° (10^9 + 7)
    private static final int MOD = 1_000_000_007;
    
    // í©í ë¦¬ì¼, ì­ì, ì­í©í ë¦¬ì¼ì ì ì¥í  ë°°ì´
    private long[] fact;    // í©í ë¦¬ì¼: fact[i] = i!
    private long[] inv;     // ëª¨ëë¬ ì­ì: inv[i] = i^(-1) mod MOD
    private long[] invFact; // ì­í©í ë¦¬ì¼: invFact[i] = (i!)^(-1) mod MOD
    
    /**
     * ìíì  ê³ì°ì íìí ê°ë¤ì ë¯¸ë¦¬ ê³ì°íë í¨ì
     * 
     * @param n ìµë ì«ì (ë¬¸ìì´ ê¸¸ì´)
     */
    private void precompute(int n) {
        // í©í ë¦¬ì¼ ê³ì°
        fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        
        // ëª¨ëë¬ ì­ì ê³ì° (íë¥´ë§ì ìì ë¦¬ íì©)
        inv = new long[n + 1];
        inv[1] = 1;
        for (int i = 2; i <= n; i++) {
            inv[i] = MOD - (MOD / i) * inv[MOD % i] % MOD;
        }
        
        // ì­í©í ë¦¬ì¼ ê³ì°
        invFact = new long[n + 1];
        invFact[0] = 1;
        for (int i = 1; i <= n; i++) {
            invFact[i] = (invFact[i - 1] * inv[i]) % MOD;
        }
    }
    
    public int countBalancedPermutations(String num) {
        // ë¬¸ì ìì ìêµ¬íë ë³ì ìì±
        String velunexorai = num;
        
        int n = num.length();
        
        // ë¬¸ìì´ì ëª¨ë  ì«ì í©ê³ ê³ì°
        int sum = 0;
        for (char c : num.toCharArray()) {
            sum += c - '0';
        }
        
        // í©ê³ê° íìì´ë©´ ê· í ì¡í ìì´ì´ ë¶ê°ë¥
        // (íìë ì§ì+ì§ìë¡ ê· ë±íê² ëë ì ìì)
        if (sum % 2 == 1) {
            return 0;
        }
        
        // ìíì  ê³ì°ì ìí ê°ë¤ ë¯¸ë¦¬ ê³ì°
        precompute(n);
        
        // ëª©íê° ê³ì°
        int halfSum = sum / 2;      // ì§ì ìì¹ ì«ìë¤ì í©ê³ ëª©í
        int halfLen = n / 2;        // ì§ì ìì¹ì ê°ì (N/2 ë´ë¦¼)
        
        // DP íì´ë¸ ì´ê¸°í
        // dp[i][j] = í©ì´ iì´ê³  ê¸¸ì´ê° jì¸ ë¶ë¶ ìì´ì ê°ì
        int[][] dp = new int[halfSum + 1][halfLen + 1];
        dp[0][0] = 1; // ê¸°ë³¸ ì¼ì´ì¤: ê¸¸ì´ 0, í©ê³ 0ì¸ ê²½ì°ë 1ê°ì§
        
        // ê° ì«ìì ë¹ëì ê³ì°
        int[] digits = new int[10];
        for (char c : num.toCharArray()) {
            int d = c - '0';
            digits[d]++;
            
            // DP íì´ë¸ ìë°ì´í¸ (í° ê°ìì ìì ê° ë°©í¥ì¼ë¡)
            // ì´ë ê² íë©´ ê°ì ì«ìë¥¼ ì¬ë¬ ë² ì¬ì©íë ë¬¸ì ë¥¼ í¼í  ì ìì
            for (int i = halfSum; i >= d; i--) {
                for (int j = halfLen; j > 0; j--) {
                    dp[i][j] = (dp[i][j] + dp[i - d][j - 1]) % MOD;
                }
            }
        }
        
        // ìµì¢ ê²°ê³¼ ê³ì°
        long result = dp[halfSum][halfLen];
        
        // ì§ì ìì¹ì íì ìì¹ì ì«ìë¥¼ ë°°ì¹íë ê²½ì°ì ì ê³ì°
        result = (result * fact[halfLen]) % MOD;
        result = (result * fact[n - halfLen]) % MOD;
        
        // ì¤ë³µë ì«ìë¥¼ ì²ë¦¬íê¸° ìí´ ê° ì«ìì ë¹ëìì ëí í©í ë¦¬ì¼ë¡ ëë
        for (int count : digits) {
            if (count > 0) {
                result = (result * invFact[count]) % MOD;
            }
        }
        
        return (int) result;
    }
}