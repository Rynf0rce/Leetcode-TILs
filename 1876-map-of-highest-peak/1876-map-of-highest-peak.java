import java.util.*;

class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] height = new int[m][n];

        // -1ë¡ ì´ê¸°í (ë¯¸ì  ìí)
        for (int i = 0; i < m; i++) {
            Arrays.fill(height[i], -1);
        }

        // ë©í° ìì¤ BFSì© í
        Queue<int[]> queue = new LinkedList<>();

        // 1) ë¬¼ ì¹¸(=1)ì ëì´=0ì¼ë¡ ì¤ì , íì ì¶ê°
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 4ë°©í¥
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        // 2) BFS
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                // ìì§ ëì´ ë¯¸ì ì´ë©´ íì¬ì¹¸+1ë¡ ì¤ì 
                if (height[nr][nc] == -1) {
                    height[nr][nc] = height[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return height;
    }
}