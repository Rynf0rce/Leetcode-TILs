class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;
        
        // ê·¸ëí êµ¬ì±
        List<List<Integer>> tree1 = buildGraph(edges1, n);
        List<List<Integer>> tree2 = buildGraph(edges2, m);
        
        int[] result = new int[n];
        
        // k=0ì¸ ê²½ì° í¹ë³ ì²ë¦¬
        if (k == 0) {
            Arrays.fill(result, 1); // ìê¸° ìì ë§ ì¹´ì´í¸
            return result;
        }
        
        // ì²« ë²ì§¸ í¸ë¦¬ìì ê° ë¸ëë¡ë¶í° ê±°ë¦¬ k ì´íì ë¸ë ì
        int[] tree1Counts = new int[n];
        for (int i = 0; i < n; i++) {
            tree1Counts[i] = countNodesWithinDistance(tree1, i, k);
        }
        
        // ë ë²ì§¸ í¸ë¦¬ìì ê° ê±°ë¦¬ì ëí ìµë ë¸ë ì ë¯¸ë¦¬ ê³ì°
        int maxFromTree2 = 0;
        if (k >= 1) {
            for (int v = 0; v < m; v++) {
                int count = countNodesWithinDistance(tree2, v, k - 1);
                maxFromTree2 = Math.max(maxFromTree2, count);
            }
        }
        
        // ê° ë¸ëì ëí´ ê²°ê³¼ ê³ì°
        for (int i = 0; i < n; i++) {
            result[i] = tree1Counts[i] + maxFromTree2;
        }
        
        return result;
    }
    
    private List<List<Integer>> buildGraph(int[][] edges, int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
    
    private int countNodesWithinDistance(List<List<Integer>> graph, int start, int maxDist) {
        if (maxDist < 0) return 0;
        
        int n = graph.size();
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        visited[start] = true;
        int count = 0;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int dist = current[1];
            
            count++;
            
            if (dist < maxDist) {
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(new int[]{neighbor, dist + 1});
                    }
                }
            }
        }
        
        return count;
    }
}