class Solution {
    public int maxDiff(int num) {
        String numStr = String.valueOf(num);
        
        // ìµëê° êµ¬íê¸°
        int maxVal = getMaximum(numStr);
        
        // ìµìê° êµ¬íê¸°
        int minVal = getMinimum(numStr);
        
        return maxVal - minVal;
    }
    
    private int getMaximum(String numStr) {
        // ì²« ë²ì§¸ë¡ ëì¤ë 9ê° ìë ì«ìë¥¼ 9ë¡ ë°ê¾¸ê¸°
        for (int i = 0; i < numStr.length(); i++) {
            char digit = numStr.charAt(i);
            if (digit != '9') {
                return Integer.parseInt(numStr.replace(digit, '9'));
            }
        }
        
        // ëª¨ë  ìë¦¿ìê° 9ì¸ ê²½ì° ê·¸ëë¡ ë°í
        return Integer.parseInt(numStr);
    }
    
    private int getMinimum(String numStr) {
        char firstDigit = numStr.charAt(0);
        
        // ì²« ë²ì§¸ ìë¦¿ìê° 1ì´ ìë ê²½ì°
        if (firstDigit != '1') {
            // ì²« ë²ì§¸ ìë¦¿ìë¥¼ 1ë¡ ë°ê¾¸ê¸°
            // (0ì¼ë¡ ë°ê¾¸ë©´ leading zeroê° ëì´ íì©ëì§ ìì)
            return Integer.parseInt(numStr.replace(firstDigit, '1'));
        }
        
        // ì²« ë²ì§¸ ìë¦¿ìê° 1ì¸ ê²½ì°
        // ë ë²ì§¸ ìë¦¿ìë¶í° ì²« ë²ì§¸ë¡ ëì¤ë 0ê³¼ 1ì´ ìë ì«ìë¥¼ 0ì¼ë¡ ë°ê¾¸ê¸°
        for (int i = 1; i < numStr.length(); i++) {
            char digit = numStr.charAt(i);
            if (digit != '0' && digit != '1') {
                return Integer.parseInt(numStr.replace(digit, '0'));
            }
        }
        
        // ì²« ë²ì§¸ ìë¦¿ìê° 1ì´ê³  ëë¨¸ì§ê° ëª¨ë 0 ëë 1ì¸ ê²½ì°
        // ë ì´ì ì¤ì¼ ì ìì¼ë¯ë¡ ê·¸ëë¡ ë°í
        return Integer.parseInt(numStr);
    }
}