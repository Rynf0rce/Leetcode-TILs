class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;
        
        // BFSë¥¼ ìí íì ë°©ë¬¸ ì²´í¬ ë°°ì´
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[target + 1];
        
        // ììì : 1ë² ì¹¸, ì£¼ì¬ì êµ´ë¦° íì 0
        queue.offer(new int[]{1, 0});
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int moves = current[1];
            
            // ëª©í ì§ì ì ëë¬í ê²½ì°
            if (pos == target) {
                return moves;
            }
            
            // ì£¼ì¬ì êµ´ë¦¬ê¸° (1ë¶í° 6ê¹ì§)
            for (int dice = 1; dice <= 6; dice++) {
                int nextPos = pos + dice;
                
                // ë³´ë ë²ìë¥¼ ë²ì´ëë ê²½ì° ì¤íµ
                if (nextPos > target) break;
                
                // ë±ì´ë ì¬ë¤ë¦¬ê° ìëì§ íì¸
                int[] coords = getCoordinates(nextPos, n);
                int row = coords[0];
                int col = coords[1];
                
                // ë±ì´ë ì¬ë¤ë¦¬ê° ìì¼ë©´ ëª©ì ì§ë¡ ì´ë
                if (board[row][col] != -1) {
                    nextPos = board[row][col];
                }
                
                // ì´ë¯¸ ë°©ë¬¸í ìì¹ê° ìëë©´ íì ì¶ê°
                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.offer(new int[]{nextPos, moves + 1});
                }
            }
        }
        
        // ëª©í ì§ì ì ëë¬í  ì ìë ê²½ì°
        return -1;
    }
    
    /**
     * ë³´ë ë²í¸ë¥¼ (row, col) ì¢íë¡ ë³í
     * Boustrophedon ì¤íì¼: ìëìì ìë¡, íë§ë¤ ë°©í¥ êµë
     */
    private int[] getCoordinates(int num, int n) {
        // 1-basedë¥¼ 0-basedë¡ ë³í
        int index = num - 1;
        
        // í ê³ì° (ìëììë¶í°)
        int row = n - 1 - index / n;
        
        // ì´ ê³ì° (íë§ë¤ ë°©í¥ì´ ë°ë)
        int col;
        if ((n - 1 - row) % 2 == 0) {
            // ì¼ìª½ìì ì¤ë¥¸ìª½ì¼ë¡ ê°ë í
            col = index % n;
        } else {
            // ì¤ë¥¸ìª½ìì ì¼ìª½ì¼ë¡ ê°ë í
            col = n - 1 - index % n;
        }
        
        return new int[]{row, col};
    }
}