class Solution {
    private int[] parent;
    private int[] cost; // Cost for each component (AND of edge weights)

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        // Initialize Union-Find data structures
        parent = new int[n];
        cost = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            cost[i] = -1; // Initialize cost as all 1s (identity for AND)
        }

        // Process edges to build components and compute costs
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            // Find roots of u and v
            int rootU = find(u);
            int rootV = find(v);
            
            // Union the components
            if (rootU != rootV) {
                parent[rootV] = rootU;
                // Combine costs: AND of current costs and new edge weight
                cost[rootU] = (cost[rootU] == -1 ? w : (cost[rootU] & w));
                if (cost[rootV] != -1) {
                    cost[rootU] &= cost[rootV];
                }
            } else {
                // If already in the same component, update the cost
                cost[rootU] = (cost[rootU] == -1 ? w : (cost[rootU] & w));
            }
        }

        // Process queries
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int s = query[i][0];
            int t = query[i][1];
            
            // Find roots of s and t
            int rootS = find(s);
            int rootT = find(t);
            
            // If in the same component, return the component's cost
            if (rootS == rootT) {
                answer[i] = cost[rootS];
            } else {
                answer[i] = -1; // No walk exists
            }
        }
        
        return answer;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }
}