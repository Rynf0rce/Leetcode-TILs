class Solution {
    public int findMaxFish(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int maxFish = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0 && !visited[r][c]) {
                    // ë¬¼ ì¹¸ì´ê³  ìì§ ë°©ë¬¸ ì íë¤ë©´
                    int fishCount = bfsFishSum(grid, r, c, visited);
                    maxFish = Math.max(maxFish, fishCount);
                }
            }
        }

        return maxFish;
    }

    // BFSë¡ í´ë¹ ì»´í¬ëí¸ ì ì²´ ë¬¼ê³ ê¸° ì í©ì°
    private int bfsFishSum(int[][] grid, int sr, int sc, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        int sum = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr,sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            sum += grid[r][c]; // í´ë¹ ì¹¸ì ë¬¼ê³ ê¸° ì ë¶ íë

            // 4ë°©í¥ íì
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >=0 && nr<m && nc>=0 && nc<n) {
                    if (grid[nr][nc] > 0 && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr,nc});
                    }
                }
            }
        }
        return sum;
    }
}
