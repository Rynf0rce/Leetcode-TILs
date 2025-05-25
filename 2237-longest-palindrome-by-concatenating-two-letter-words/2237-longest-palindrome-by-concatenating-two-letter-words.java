class Solution {
    public int longestPalindrome(String[] words) {
        // ê° ë¨ì´ì ë¹ëìë¥¼ ì ì¥íë ë§µ
        Map<String, Integer> count = new HashMap<>();
        
        // ëª¨ë  ë¨ì´ì ë¹ëì ê³ì°
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        
        int length = 0;
        boolean centerUsed = false; // ì¤ìì íë¬¸ ë¨ì´ë¥¼ ì¬ì©íëì§ ì²´í¬
        Set<String> processed = new HashSet<>(); // ì´ë¯¸ ì²ë¦¬í ë¨ì´ë¤ì ì¶ì 
        
        for (String word : count.keySet()) {
            if (processed.contains(word)) continue;
            
            int freq = count.get(word);
            
            if (word.charAt(0) == word.charAt(1)) {
                // ê°ì ê¸ìë¡ ì´ë£¨ì´ì§ íë¬¸ ë¨ì´ (ì: "aa", "bb")
                length += (freq / 2) * 4; // ìì¼ë¡ ììª½ì ë°°ì¹
                
                // íì ê°ê° ìê³  ìì§ ì¤ìì ë¨ì´ë¥¼ ì¬ì©íì§ ììë¤ë©´
                if (freq % 2 == 1 && !centerUsed) {
                    length += 2; // íëë¥¼ ì¤ìì ë°°ì¹
                    centerUsed = true;
                }
                processed.add(word);
            } else {
                String reverse = "" + word.charAt(1) + word.charAt(0); // "ba"
                
                if (count.containsKey(reverse)) {
                    int pairs = Math.min(freq, count.get(reverse));
                    length += pairs * 4;
                    
                    processed.add(word);
                    processed.add(reverse);
                }
            }
        }
        
        return length;
    }
}