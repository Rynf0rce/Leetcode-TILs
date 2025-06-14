class Solution {
    public int minMaxDifference(int num) {
        String numStr = String.valueOf(num);
        
        // ìµëê° êµ¬íê¸°
        int maxVal = getMaximum(numStr);
        
        // ìµìê° êµ¬íê¸°  
        int minVal = getMinimum(numStr);
        
        return maxVal - minVal;
    }
    
    private int getMaximum(String numStr) {
        // íí¸ 1: ì²« ë²ì§¸ë¡ ëì¤ë 9ê° ìë ì«ìë¥¼ 9ë¡ ë°ê¾¸ê¸°
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
        // ì²« ë²ì§¸ ìë¦¿ìë¥¼ 0ì¼ë¡ ë°ê¾¸ê¸° (leading zero íì©)
        char firstDigit = numStr.charAt(0);
        return Integer.parseInt(numStr.replace(firstDigit, '0'));
    }
}