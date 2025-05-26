class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        
        // ì¸ì  ë¦¬ì¤í¸ì ì§ìì°¨ì ë°°ì´ ìì±
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // ê·¸ëí êµ¬ì±
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }
        
        // ìì ì ë ¬ì ìí í ì´ê¸°í
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // DP ë°°ì´: dp[node][color] = nodeìì ëëë ê²½ë¡ìì colorì ìµë ê°ì
        int[][] dp = new int[n][26];
        int processedNodes = 0;
        int maxColorValue = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            processedNodes++;
            
            // íì¬ ë¸ëì ììì ì¦ê°ìí´
            int currentColor = colors.charAt(current) - 'a';
            dp[current][currentColor]++;
            
            // íì¬ ë¸ëììì ìµë ìì ê° ê³ì°
            for (int color = 0; color < 26; color++) {
                maxColorValue = Math.max(maxColorValue, dp[current][color]);
            }
            
            // ì¸ì í ë¸ëë¤ ì²ë¦¬
            for (int neighbor : graph.get(current)) {
                // íì¬ ë¸ëì ëª¨ë  ìì ê°ì neighborë¡ ì í
                for (int color = 0; color < 26; color++) {
                    dp[neighbor][color] = Math.max(dp[neighbor][color], dp[current][color]);
                }
                
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // ì¬ì´í´ ê²ì¶: ëª¨ë  ë¸ëê° ì²ë¦¬ëì§ ììë¤ë©´ ì¬ì´í´ì´ ì¡´ì¬
        if (processedNodes != n) {
            return -1;
        }
        
        return maxColorValue;
    }
}