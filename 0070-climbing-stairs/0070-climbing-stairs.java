class Solution {
    public int climbStairs(int n) {
        // nì´ 1 ëë 2ì¼ ê²½ì° ë°ë¡ ë°í
        if (n <= 2) return n;
        
        // ways[i] = ië²ì§¸ ê³ë¨ê¹ì§ ì¤ë¥´ë ë°©ë²ì ì
        int[] ways = new int[n+1];
        ways[1] = 1;
        ways[2] = 2;
        
        for (int i = 3; i <= n; i++) {
            ways[i] = ways[i-1] + ways[i-2];
        }
        
        return ways[n];
    }
}
