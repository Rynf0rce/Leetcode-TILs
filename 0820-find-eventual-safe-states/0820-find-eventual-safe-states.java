import java.util.*;

class Solution {
    // ìì ìì
    private static final int WHITE = 0; // ë¯¸ë°©ë¬¸
    private static final int GRAY = 1;  // ë°©ë¬¸ ì¤
    private static final int BLACK = 2; // ìì (ë°©ë¬¸ ìë£)

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];

        List<Integer> result = new ArrayList<>();

        // ëª¨ë  ë¸ëì ëí´ DFS
        for (int i = 0; i < n; i++) {
            if (dfsCheck(graph, i, color)) {
                // ìì  ë¸ëë©´ resultì ì¶ê°
                result.add(i);
            }
        }

        return result;
    }

    // trueë©´ safe, falseë©´ cycleë¡ ì¸í´ unsafe
    private boolean dfsCheck(int[][] graph, int node, int[] color) {
        // ì´ë¯¸ ë°©ë¬¸ ê²°ê³¼ê° ê²°ì ë ê²½ì°
        if (color[node] != WHITE) {
            // BLACKì´ë©´ safe, GRAYì´ë©´ unsafe
            return color[node] == BLACK; 
        }

        // ë°©ë¬¸ ì¤ íì
        color[node] = GRAY;

        // ì¸ì  ë¸ë íì
        for (int nei : graph[node]) {
            // ë§ì½ ì¸ì  ë¸ëê° ì´ë¯¸ ë°©ë¬¸ì¤(GRAY) -> cycle ë°ì => unsafe
            if (color[nei] == GRAY) {
                return false; 
            }
            // ì¸ì  ë¸ëê° ìì§ ë¯¸ë°©ë¬¸(WHITE)ì´ê³ , dfs ê²°ê³¼ê° unsafeíë©´ => unsafe
            if (color[nei] == WHITE && !dfsCheck(graph, nei, color)) {
                return false;
            }
        }

        // ì¸ì  ë¸ë ëª¨ë ìì  => ë³¸ì¸ë ìì 
        color[node] = BLACK;
        return true;
    }
}
