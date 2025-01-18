class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dist[i][j]: (i,j)ì ëë¬íê¸° ìí´ ë³ê²½í´ì¼ íë íì´í(ë¹ì©)ì ìµìê°
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        // ë°©í¥ ì ì: 1âì¤ë¥¸ìª½, 2âì¼ìª½, 3âìë, 4âì
        // directions[dIdx] = {di, dj}
        int[][] directions = {
            {0, 1},   // ì¤ë¥¸ìª½
            {0, -1},  // ì¼ìª½
            {1, 0},   // ìë
            {-1, 0}   // ì
        };

        // 0â1 BFSë¥¼ ìí ë±(Deque)
        java.util.Deque<int[]> dq = new java.util.ArrayDeque<>();
        dq.offer(new int[]{0, 0});

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int i = cur[0], j = cur[1];
            
            // ì´ë¯¸ ë ìì ë¹ì©ì¼ë¡ ë°©ë¬¸ë ìíë¼ë©´ ì¤íµ
            if (dist[i][j] > dist[i][j]) {
                continue;
            }
            
            // 4ë°©í¥ íì
            for (int dIdx = 0; dIdx < 4; dIdx++) {
                int x = i + directions[dIdx][0];
                int y = j + directions[dIdx][1];
                
                // ê²©ì ë²ì ê²ì¬
                if (x < 0 || x >= m || y < 0 || y >= n) {
                    continue;
                }
                
                // íì¬ ì¹¸ì íì´íì (dIdx+1)ì´ ê°ìì§ íì¸
                // (grid[i][j] == dIdx+1)ì´ë©´ ë¹ì© 0, ìëë©´ 1
                int edgeCost = (grid[i][j] == dIdx + 1) ? 0 : 1;
                int nextCost = dist[i][j] + edgeCost;
                
                // (x, y)ê¹ì§ì ë¹ì© ê°±ì 
                if (nextCost < dist[x][y]) {
                    dist[x][y] = nextCost;
                    
                    // ë¹ì©ì´ 0ì´ë©´ ë±ì ì, 1ì´ë©´ ë¤ì ë£ì´ì¤ë¤
                    if (edgeCost == 0) {
                        dq.addFirst(new int[]{x, y});
                    } else {
                        dq.addLast(new int[]{x, y});
                    }
                }
            }
        }

        return dist[m - 1][n - 1];
    }
}
