class Solution {
    private int[] parent;
    private int[] size;
    private int[] edgeCount;

    public int countCompleteComponents(int n, int[][] edges) {
        parent = new int[n];
        size = new int[n];
        edgeCount = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            edgeCount[i] = 0;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            int rootU = find(u);
            int rootV = find(v);
            
            if (rootU != rootV) {
                if (size[rootU] < size[rootV]) {
                    int temp = rootU;
                    rootU = rootV;
                    rootV = temp;
                }
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
                edgeCount[rootU] += edgeCount[rootV] + 1;
            } else {
                edgeCount[rootU]++;
            }
        }

        int completeCount = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int root = find(i);
            if (visited[root]) continue;
            visited[root] = true;
            
            int componentSize = size[root];
            int expectedEdges = componentSize * (componentSize - 1) / 2;
            if (edgeCount[root] == expectedEdges) {
                completeCount++;
            }
        }

        return completeCount;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}