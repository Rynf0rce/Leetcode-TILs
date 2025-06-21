class Solution {
    public int minimumDeletions(String word, int k) {
        // íí¸ 1: ê° ë¬¸ìì ë¹ë ê³ì°
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        int minDeletions = Integer.MAX_VALUE;
        int maxFreq = 0;
        
        // ìµë ë¹ë ì°¾ê¸°
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }
        
        // íí¸ 3: ëª¨ë  ê°ë¥í ìµì ë¹ëë¥¼ ìë
        // 0ë¶í° ìµë ë¹ëê¹ì§ ê°ê°ì ìµì ë¹ëë¡ ê°ì 
        for (int minFreq = 0; minFreq <= maxFreq; minFreq++) {
            int deletions = 0;
            
            // íí¸ 4: ê° ë¬¸ìì ë¹ëì ë°ë¼ ì­ì  ê°ì ê²°ì 
            for (int f : freq) {
                if (f == 0) continue; // ë¹ëê° 0ì¸ ë¬¸ìë ë¬´ì
                
                if (f < minFreq) {
                    // 1. ë¹ëê° minFreqë³´ë¤ ìì¼ë©´ ëª¨ë ì­ì 
                    deletions += f;
                } else if (f > minFreq + k) {
                    // 2. ë¹ëê° minFreq + kë³´ë¤ í¬ë©´ ì´ê³¼ë¶ ì­ì 
                    deletions += f - (minFreq + k);
                }
                // 3. minFreq <= f <= minFreq + kì¸ ê²½ì°ë ì­ì íì§ ìì
            }
            
            minDeletions = Math.min(minDeletions, deletions);
        }
        
        return minDeletions;
    }
}