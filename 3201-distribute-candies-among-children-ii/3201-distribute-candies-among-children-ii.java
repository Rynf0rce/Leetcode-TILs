class Solution {
    public long distributeCandies(int n, int limit) {
        // í¬í¨-ë°°ì  ìë¦¬ ì¬ì©
        // x + y + z = nì´ê³  0 <= x, y, z <= limitì¸ ìì´ ìë ì ìí´ì ê°ì
        
        long total = 0;
        
        // ì ì²´ ê²½ì°ì ì: ì ì½ ì¡°ê±´ ìì´ x + y + z = nì¸ ìì´ ìë ì ìí´
        total += combinations(n + 2, 2);
        
        // íëì ë³ìê° limitë¥¼ ì´ê³¼íë ê²½ì°ë¤ì ì ì¸
        // x > limitì¸ ê²½ì°: x' = x - (limit+1)ë¡ ì¹ííë©´ x' + y + z = n - (limit+1)
        total -= 3 * combinations(n - limit + 1, 2);
        
        // ë ê°ì ë³ìê° ëìì limitë¥¼ ì´ê³¼íë ê²½ì°ë¤ì ë¤ì í¬í¨
        // x > limitì´ê³  y > limitì¸ ê²½ì°: x' + y' + z = n - 2*(limit+1)
        total += 3 * combinations(n - 2 * limit, 2);
        
        // ì¸ ê°ì ë³ìê° ëª¨ë limitë¥¼ ì´ê³¼íë ê²½ì°ë¥¼ ì ì¸
        // x, y, z ëª¨ë > limitì¸ ê²½ì°: x' + y' + z' = n - 3*(limit+1)
        total -= combinations(n - 3 * limit - 1, 2);
        
        return total;
    }
    
    /**
     * ì¡°í© C(n, k) ê³ì°
     * n < kì´ê±°ë n < 0ì´ë©´ 0 ë°í
     */
    private long combinations(int n, int k) {
        if (n < 0 || n < k) {
            return 0;
        }
        
        if (k == 0 || k == n) {
            return 1;
        }
        
        if (k == 2) {
            return (long) n * (n - 1) / 2;
        }
        
        // ì¼ë°ì ì¸ ê²½ì° (ì´ ë¬¸ì ììë k=2ë§ ì¬ì©)
        long result = 1;
        for (int i = 0; i < k; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }
}