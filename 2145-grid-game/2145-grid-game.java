class Solution {
    public long gridGame(int[][] grid) {
        int n = grid[0].length;

        // 1) ì í/ìë íì prefix sum
        // prefixTop[i] = sum of grid[0][0..i], prefixBottom[i] = sum of grid[1][0..i]
        long[] prefixTop = new long[n];
        long[] prefixBottom = new long[n];

        prefixTop[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            prefixTop[i] = prefixTop[i-1] + grid[0][i];
        }
        prefixBottom[0] = grid[1][0];
        for (int i = 1; i < n; i++) {
            prefixBottom[i] = prefixBottom[i-1] + grid[1][i];
        }

        // ì ì²´ top rowì í©
        long topTotal = prefixTop[n-1];

        // 2) ì´ cì ëí´, R2ê° ì»ê² ëë ì ì = max( topRemaining, bottomRemaining )
        //    topRemaining = topTotal - prefixTop[c]  ( c+1 .. n-1 )
        //    bottomRemaining = prefixBottom[c-1]     ( 0 .. c-1 )
        //    ì²« ë¡ë´ì´ ì´ ê°ì ìµìííëë¡ c ê²°ì 
        long answer = Long.MAX_VALUE;

        for (int c = 0; c < n; c++) {
            long scoreTop = topTotal - prefixTop[c];   // ë¨ì top êµ¬ê°í©
            long scoreBottom = (c == 0 ? 0L : prefixBottom[c-1]); // ë¨ì bottom êµ¬ê°í©
            long secondRobotScore = Math.max(scoreTop, scoreBottom);

            answer = Math.min(answer, secondRobotScore);
        }

        return answer;
    }
}
