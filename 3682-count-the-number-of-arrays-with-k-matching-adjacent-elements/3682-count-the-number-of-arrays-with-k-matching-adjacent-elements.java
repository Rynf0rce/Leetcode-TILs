class Solution {
    private static final int MOD = 1000000007;
    
    public int countGoodArrays(int n, int m, int k) {
        // íí¸ 1: ì²« ë²ì§¸ ìì¹ë mê°ì ì íì§
        long result = m;
        
        // íí¸ 2: n-1ê°ì ìì¹ ì¤ kê°ë¥¼ ì ííì¬ ì´ì  ììì ê°ê² ë§ë¤ê¸°
        // ì´ë C(n-1, k) ì¡°í©
        long combination = combination(n - 1, k);
        result = (result * combination) % MOD;
        
        // íí¸ 3: ëë¨¸ì§ n-1-kê°ì ìì¹ìì ê°ê° ì´ì  ììì ë¤ë¥´ê² ë§ë¤ê¸°
        // ê° ìì¹ë§ë¤ m-1ê°ì ì íì§
        long power = power(m - 1, n - 1 - k);
        result = (result * power) % MOD;
        
        return (int) result;
    }
    
    // ì¡°í© C(n, k) ê³ì°
    private long combination(int n, int k) {
        if (k > n || k < 0) return 0;
        if (k == 0 || k == n) return 1;
        
        // C(n, k) = n! / (k! * (n-k)!)
        // ì¤ë²íë¡ì° ë°©ì§ë¥¼ ìí´ modular arithmetic ì¬ì©
        long numerator = 1;
        long denominator = 1;
        
        // C(n, k) = C(n, n-k)ì´ë¯ë¡ ë ìì ê° ì¬ì©
        k = Math.min(k, n - k);
        
        for (int i = 0; i < k; i++) {
            numerator = (numerator * (n - i)) % MOD;
            denominator = (denominator * (i + 1)) % MOD;
        }
        
        // modular inverseë¥¼ ì¬ì©íì¬ ëëì ìí
        return (numerator * modularInverse(denominator, MOD)) % MOD;
    }
    
    // ë¹ ë¥¸ ê±°ë­ì ê³± (a^b mod MOD)
    private long power(long base, long exp) {
        long result = 1;
        base %= MOD;
        
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        
        return result;
    }
    
    // ëª¨ëë¬ ì­ì ê³ì° (íë¥´ë§ì ìì ë¦¬ ì¬ì©)
    // a^(-1) â¡ a^(p-2) (mod p) when p is prime
    private long modularInverse(long a, long mod) {
        return power(a, mod - 2);
    }
}