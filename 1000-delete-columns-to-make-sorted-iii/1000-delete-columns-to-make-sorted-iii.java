class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length; // í ê°ì
        int m = strs[0].length(); // ì´ ê°ì (ëª¨ë  ë¬¸ìì´ ê¸¸ì´ ëì¼)
        int[] dp = new int[m]; // dp[i]ë ì´ ië¥¼ ë§ì§ë§ì¼ë¡ í¬í¨íë ìµë ê¸¸ì´
        
        // ì´ê¸°ê°: ê° ì´ì ë¨ëì¼ë¡ ì¬ì©í  ê²½ì° ê¸¸ì´ 1
        for (int i = 0; i < m; i++) {
            dp[i] = 1;
        }
        
        // ê° ì´ jì ëí´ ì´ì  ì´ kë¥¼ íì¸
        for (int j = 1; j < m; j++) {
            for (int k = 0; k < j; k++) {
                boolean canExtend = true;
                // ëª¨ë  íì ëí´ ì¬ì ì íì¸
                for (int i = 0; i < n; i++) {
                    if (strs[i].charAt(k) > strs[i].charAt(j)) {
                        canExtend = false;
                        break;
                    }
                }
                // ì¬ì ì ë§ì¡± ì dp ê°±ì 
                if (canExtend) {
                    dp[j] = Math.max(dp[j], dp[k] + 1);
                }
            }
        }
        
        // ìµë ê¸¸ì´ ì°¾ê¸°
        int maxLength = 0;
        for (int i = 0; i < m; i++) {
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        // ì ì²´ ì´ ê¸¸ì´ìì ìµë ê¸¸ì´ë¥¼ ë¹¼ë©´ ìµì ì­ì  íì
        return m - maxLength;
    }
}