class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        
        // íí¸ 1: ë¬¸ìì´ ê¸¸ì´ì kë¥¼ ì¬ì©í´ì ê·¸ë£¹ ì ê³ì°
        int numGroups = (n + k - 1) / k; // Math.ceil(n / k)ì ê°ì
        
        String[] result = new String[numGroups];
        
        // íí¸ 2: ê° ê·¸ë£¹ì ë¬¸ìì´ìì ì±ì°ê³ , ë¶ì¡±íë©´ fillë¡ ì±ì°ê¸°
        for (int i = 0; i < numGroups; i++) {
            StringBuilder group = new StringBuilder();
            
            // íì¬ ê·¸ë£¹ì ìì ì¸ë±ì¤
            int start = i * k;
            
            // kê° ë¬¸ìë¥¼ ê·¸ë£¹ì ì¶ê°
            for (int j = 0; j < k; j++) {
                int currentIndex = start + j;
                
                if (currentIndex < n) {
                    // ë¬¸ìì´ìì ë¬¸ì ê°ì ¸ì¤ê¸°
                    group.append(s.charAt(currentIndex));
                } else {
                    // ë¬¸ìì´ ëì ëë¬íì¼ë©´ fill ë¬¸ìë¡ ì±ì°ê¸°
                    group.append(fill);
                }
            }
            
            result[i] = group.toString();
        }
        
        return result;
    }
}