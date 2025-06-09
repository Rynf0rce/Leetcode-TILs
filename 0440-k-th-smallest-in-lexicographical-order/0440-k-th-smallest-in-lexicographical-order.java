class Solution {
    public int findKthNumber(int n, int k) {
        int current = 1;
        k--; // 1-basedìì 0-basedë¡ ë³í
        
        while (k > 0) {
            long steps = countSteps(current, n);
            if (k >= steps) {
                // íì¬ ì ëì¬ë¥¼ ê±´ëë°ê³  ë¤ì ì ëì¬ë¡
                k -= steps;
                current++;
            } else {
                // íì¬ ì ëì¬ ìì¼ë¡ ë¤ì´ê°
                k--;
                current *= 10;
            }
        }
        
        return current;
    }
    
    /**
     * prefixë¶í° ììíì¬ n ì´íì ì«ì ì¤ 
     * í´ë¹ ì ëì¬ë¥¼ ê°ì§ ì«ìì ê°ìë¥¼ ê³ì°
     */
    private long countSteps(long prefix, long n) {
        long count = 0;
        long first = prefix;
        long last = prefix;
        
        while (first <= n) {
            // íì¬ ë ë²¨ìì firstë¶í° min(n, last)ê¹ì§ì ê°ì ì¶ê°
            count += Math.min(n + 1, last + 1) - first;
            
            // ë¤ì ë ë²¨ë¡ ì´ë
            first *= 10;
            last = last * 10 + 9;
        }
        
        return count;
    }
}