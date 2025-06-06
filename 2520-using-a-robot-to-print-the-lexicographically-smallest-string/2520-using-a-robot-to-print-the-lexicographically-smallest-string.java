class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        
        // ê° ìì¹ìì ê·¸ ìì¹ë¶í° ëê¹ì§ì ìµì ë¬¸ìë¥¼ ë¯¸ë¦¬ ê³ì°
        char[] minSuffix = new char[n];
        minSuffix[n - 1] = s.charAt(n - 1);
        
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = (char) Math.min(s.charAt(i), minSuffix[i + 1]);
        }
        
        StringBuilder result = new StringBuilder();
        StringBuilder stack = new StringBuilder(); // të¥¼ ì¤íì¼ë¡ ì¬ì©
        int i = 0; // sì íì¬ ìì¹
        
        while (i < n || stack.length() > 0) {
            // ì¤íì´ ë¹ì´ìì§ ìê³ , ë¤ì ì¡°ê±´ ì¤ íëê° ì°¸ì´ë©´ pop
            // 1. së¥¼ ëª¨ë ì²ë¦¬íê±°ë
            // 2. ì¤í topì´ sì ë¨ì ë¶ë¶ì ìµìê°ë³´ë¤ ìê±°ë ê°ì¼ë©´
            while (stack.length() > 0 && 
                   (i >= n || stack.charAt(stack.length() - 1) <= minSuffix[i])) {
                // ì¤íìì popíì¬ ê²°ê³¼ì ì¶ê°
                result.append(stack.charAt(stack.length() - 1));
                stack.deleteCharAt(stack.length() - 1);
            }
            
            // sìì ë¬¸ìë¥¼ ì¤íì¼ë¡ push
            if (i < n) {
                stack.append(s.charAt(i));
                i++;
            }
        }
        
        return result.toString();
    }
}