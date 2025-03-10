class Solution {
    public long countOfSubstrings(String word, int k) {
        // 2D ë°°ì´: [0]ì ëª¨ì ì¬ë¶, [1]ì ìëì° ë´ ë¹ëì
        int[][] frequencies = new int[2][128];
        // ëª¨ì ì´ê¸°í
        frequencies[0]['a'] = frequencies[0]['e'] = frequencies[0]['i'] = 
        frequencies[0]['o'] = frequencies[0]['u'] = 1;

        long response = 0; // ê²°ê³¼ê°
        int currentK = 0;  // íì¬ ìì ê°ì
        int vowels = 0;    // íì¬ ëª¨ì ì¢ë¥ ì
        int extraLeft = 0; // ì¶ê°ì ì¸ ì¼ìª½ ì´ë
        int left = 0;      // ìëì° ì¼ìª½ í¬ì¸í°

        // ì¤ë¥¸ìª½ í¬ì¸í°ë¡ ìëì° íì¥
        for (int right = 0; right < word.length(); right++) {
            char rightChar = word.charAt(right);

            // ëª¨ìì¸ ê²½ì°
            if (frequencies[0][rightChar] == 1) {
                if (++frequencies[1][rightChar] == 1) {
                    vowels++; // ìë¡ì´ ëª¨ì ì¶ê°
                }
            } else {
                currentK++; // ìì ê°ì ì¦ê°
            }

            // ìì ê°ìê° kë¥¼ ì´ê³¼íë©´ ìëì° ì¶ì
            while (currentK > k) {
                char leftChar = word.charAt(left);
                left++;
                if (frequencies[0][leftChar] == 1) {
                    if (--frequencies[1][leftChar] == 0) {
                        vowels--; // ëª¨ì ì¢ë¥ ê°ì
                    }
                } else {
                    currentK--; // ìì ê°ì ê°ì
                }
                extraLeft = 0; // ì¶ê° ì´ë ì´ê¸°í
            }

            // ëª¨ë  ëª¨ìì´ í¬í¨ëê³  ììì´ kê°ì¼ ë, ì¤ë³µ ëª¨ì ì²ë¦¬
            while (vowels == 5 && currentK == k && left < right && 
                   frequencies[0][word.charAt(left)] == 1 && 
                   frequencies[1][word.charAt(left)] > 1) {
                extraLeft++;
                frequencies[1][word.charAt(left)]--;
                left++;
            }

            // ì¡°ê±´ ë§ì¡± ì ê²°ê³¼ì ì¶ê°
            if (currentK == k && vowels == 5) {
                response += (1 + extraLeft);
            }
        }

        return response;
    }
}