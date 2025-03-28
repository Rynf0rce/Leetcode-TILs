import java.util.*;

class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, down, left, right

    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int k = queries.length;

        // Step 1: Flatten and sort grid cells by value
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells.add(new int[]{grid[i][j], i, j});
            }
        }
        cells.sort((a, b) -> a[0] - b[0]);

        // Step 2: Sort queries with original indices
        int[][] sortedQueries = new int[k][2];
        for (int i = 0; i < k; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> a[0] - b[0]);

        // Step 3: Initialize Union-Find
        UnionFind uf = new UnionFind(m * n);

        // Step 4: Process queries in sorted order
        int[] answer = new int[k];
        int cellIndex = 0;
        for (int[] query : sortedQueries) {
            int queryValue = query[0];
            int originalIndex = query[1];

            // Union cells with value < queryValue
            while (cellIndex < cells.size() && cells.get(cellIndex)[0] < queryValue) {
                int[] cell = cells.get(cellIndex);
                int row = cell[1];
                int col = cell[2];
                int idx = row * n + col;

                // Connect with adjacent cells if their values are also < queryValue
                for (int[] dir : DIRS) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] < queryValue) {
                        int adjIdx = nr * n + nc;
                        uf.union(idx, adjIdx);
                    }
                }
                cellIndex++;
            }

            // Compute points: size of component containing (0,0) if accessible
            if (grid[0][0] < queryValue) {
                answer[originalIndex] = uf.size[uf.find(0)];
            } else {
                answer[originalIndex] = 0;
            }
        }

        return answer;
    }

    // Union-Find class
    class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }
}