import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        if (m == 0) return 0;
        int n = heightMap[0].length;
        if (n == 0) return 0;

        // ë°©ë¬¸ ì¬ë¶ë¥¼ ì²´í¬í  ë°°ì´
        boolean[][] visited = new boolean[m][n];

        // ì°ì ìì í(ëì´ ê¸°ì¤ ì¤ë¦ì°¨ì)
        // ììë {íì¬ ì¹¸ì ëì´, í, ì´} íí
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // 1) ë°ì´ëë¦¬(ê°ì¥ìë¦¬)ë¥¼ ì°ì ìì íì ì½ì
        for (int i = 0; i < m; i++) {
            // ì¼ìª½ ê°ì¥ìë¦¬
            visited[i][0] = true;
            minHeap.offer(new int[]{heightMap[i][0], i, 0});
            // ì¤ë¥¸ìª½ ê°ì¥ìë¦¬
            visited[i][n - 1] = true;
            minHeap.offer(new int[]{heightMap[i][n - 1], i, n - 1});
        }
        for (int j = 0; j < n; j++) {
            // ììª½ ê°ì¥ìë¦¬
            visited[0][j] = true;
            minHeap.offer(new int[]{heightMap[0][j], 0, j});
            // ìëìª½ ê°ì¥ìë¦¬
            visited[m - 1][j] = true;
            minHeap.offer(new int[]{heightMap[m - 1][j], m - 1, j});
        }

        // ë°©í¥ ì ì(ì, í, ì¢, ì°)
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int waterTrapped = 0;  // ëì  ë¬¼ì ì

        // 2) ì°ì ìì íë¥¼ ì´ì©í´ BFS ì§í
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int height = current[0];
            int x = current[1];
            int y = current[2];

            // ì¸ì  ì¹¸ 4ë°©í¥ íì
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                // ê²©ì ë²ì ì²´í¬
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;

                // íì¬ ì¸íë¦¬ ëì´(height)ë³´ë¤ ì¸ì  ì¹¸ì´ ë ë®ì¼ë©´ ë¬¼ì´ ì°¬ë¤
                int nextH = heightMap[nx][ny];
                if (nextH < height) {
                    waterTrapped += (height - nextH);
                    // ë¬¼ì´ ì°¼ì¼ë¯ë¡, ì¤ì  ëì´ë¥¼ heightë¡ ë§ì¶°ì£¼ì´ì¼ í¨
                    nextH = height;
                }

                // ì°ì ìì íì (ì¤ì  ê³ ë ¤í´ì¼ í  ëì´, nx, ny) ì½ì
                minHeap.offer(new int[]{nextH, nx, ny});
            }
        }

        return waterTrapped;
    }
}
