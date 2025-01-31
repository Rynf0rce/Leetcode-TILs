import java.util.*;

class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int[][] idGrid = new int[n][n]; // ê° ì¹¸ì´ ì´ë¤ ì¬ idì ìíëì§
        int id = 2; // 1ê³¼ êµ¬ë¶íê¸° ìí´ 2ë¶í° ìì
        Map<Integer,Integer> area = new HashMap<>(); // area[id] = ì¬ í¬ê¸°

        // 1) ê¸°ì¡´ 1ë¤ì connected componentë¥¼ DFS/BFSë¡ ì°¾ìì id ë°°ì 
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if(grid[r][c]==1 && idGrid[r][c]==0){
                    int size = bfsMark(grid, idGrid, r, c, id);
                    area.put(id, size);
                    id++;
                }
            }
        }

        // ë§ì½ ëª¨ë  ì¹¸ì´ 1ì´ë©´ => ë°ë¡ n*n ë¦¬í´ ê°ë¥
        if(area.isEmpty()) {
            // ì ë¶ 0ì¸ ê²½ì°, íë ë°ê¾¸ë©´ ì¬í¬ê¸°=1
            // ê·¼ë° ë¬¸ì ìì "ë°ê¿ ì ìë 0" -> 1 => ì¬ í¬ê¸° 1
            // í¹ì n=1ì¼ ë? => answer=1
            return 1;
        }
        // í¹ì ì´ë¯¸ ì ë¶ 1ì´ë©´? => areaì "í ê°ì id"ê° n*n ì¼ ê² => ìë ë¡ì§ìì êµ¬í´ë ë¨
        // but ê°ë¨í check:
        int maxAreaExisting = 0;
        for(int key: area.keySet()){
            maxAreaExisting = Math.max(maxAreaExisting, area.get(key));
        }
        // 2) ê° 0ì¹¸ì ëí´, ìíì¢ì°ì ìë¡ ë¤ë¥¸ ì¬ idí© +1
        int ans = maxAreaExisting; 
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if(grid[r][c]==0){
                    Set<Integer> used = new HashSet<>();
                    int sum = 1; // ë°ê¾¸ë ì¹¸ ìì²´ +1
                    for(int[] d : DIRS){
                        int nr = r+d[0], nc= c+d[1];
                        if(nr>=0 && nr<n && nc>=0 && nc<n){
                            int adjId = idGrid[nr][nc];
                            if(adjId>1 && !used.contains(adjId)){
                                sum += area.get(adjId);
                                used.add(adjId);
                            }
                        }
                    }
                    ans = Math.max(ans, sum);
                }
            }
        }
        return ans;
    }

    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};

    // BFSë¡ (sr,sc)ìì ì´ì´ì§ 1ë¤ì idë¡ íì, í¬ê¸° ë°í
    private int bfsMark(int[][] grid, int[][] idGrid, int sr, int sc, int id) {
        int n = grid.length;
        Queue<int[]> q= new LinkedList<>();
        q.offer(new int[]{sr,sc});
        idGrid[sr][sc] = id;
        int count=0;
        while(!q.isEmpty()){
            int[] cur= q.poll();
            int r=cur[0], c=cur[1];
            count++;
            for(int[] d : DIRS){
                int nr= r+d[0], nc=c+d[1];
                if(nr>=0 && nr<n && nc>=0 && nc<n){
                    if(grid[nr][nc]==1 && idGrid[nr][nc]==0){
                        idGrid[nr][nc]= id;
                        q.offer(new int[]{nr,nc});
                    }
                }
            }
        }
        return count;
    }

}
