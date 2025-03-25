import java.util.Arrays;

class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        // ìí ëë ìì§ ë°©í¥ì¼ë¡ ìë¥¼ ì ìëì§ íì¸
        // axis = 0: ìì§ ë°©í¥ (xì¶ ê¸°ì¤), axis = 1: ìí ë°©í¥ (yì¶ ê¸°ì¤)
        return canCut(rectangles, 0) || canCut(rectangles, 1);
    }

    private boolean canCut(int[][] rectangles, int axis) {
        // axisì ë°ë¼ ì ë ¬: axis = 0ì´ë©´ start_x, axis = 1ì´ë©´ start_y ê¸°ì¤ì¼ë¡ ì ë ¬
        Arrays.sort(rectangles, (a, b) -> Integer.compare(a[axis], b[axis]));

        int cuts = 0; // ë¶ë¦¬ë ê·¸ë£¹ì ì
        int previousEnd = -1; // ì´ì  ê·¸ë£¹ì ëì 

        // ëª¨ë  ì¬ê°íì ìí
        for (int[] rect : rectangles) {
            int start = rect[axis]; // íì¬ ì¬ê°íì ììì  (x ëë y)
            int end = rect[axis + 2]; // íì¬ ì¬ê°íì ëì  (end_x ëë end_y)

            // íì¬ ì¬ê°íì ììì ì´ ì´ì  ëì ë³´ë¤ í¬ê±°ë ê°ì¼ë©´ ìë¡ì´ ê·¸ë£¹ ìì
            if (start >= previousEnd) {
                cuts++;
            }
            // ì´ì  ëì ì íì¬ ì¬ê°íì ëì ê³¼ ë¹êµíì¬ ìµëê°ì¼ë¡ ê°±ì 
            previousEnd = Math.max(previousEnd, end);
            
            // ê·¸ë£¹ì´ 3ê° ì´ìì´ë©´ ë ê°ì ì ë¨ì ì¼ë¡ ì¸ ë¶ë¶ì¼ë¡ ëë ì ìì
            if (cuts >= 3) return true;
        }

        // ê·¸ë£¹ì´ 3ê° ë¯¸ë§ì´ë©´ ì¸ ë¶ë¶ì¼ë¡ ëë ì ìì
        return false;
    }
}