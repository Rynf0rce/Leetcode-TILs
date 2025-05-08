class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        int[][][] minTime = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(minTime[i][j], Integer.MAX_VALUE);
            }
        }
        
        minTime[0][0][0] = 0; 
        pq.offer(new int[]{0, 0, 0, 1});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0];
            int row = curr[1];
            int col = curr[2];
            int moveCost = curr[3]; // 1 or 2
            
            int nextMoveCost = 3 - moveCost; 
            int nextMoveIdx = moveCost - 1;
            
            if (time > minTime[row][col][nextMoveIdx]) {
                continue;
            }
            
            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {
                    int waitTime = Math.max(0, moveTime[newRow][newCol] - time);
                    int newTime = time + waitTime + moveCost;
                    
                    if (newTime < minTime[newRow][newCol][nextMoveCost - 1]) {
                        minTime[newRow][newCol][nextMoveCost - 1] = newTime;
                        pq.offer(new int[]{newTime, newRow, newCol, nextMoveCost});
                    }
                }
            }
        }
        
        return Math.min(minTime[n-1][m-1][0], minTime[n-1][m-1][1]);
    }
}