import java.util.*;

class Solution {
    List<Integer>[] graph;
    int[] parent;
    int[] bobTime;
    final int INF = 1000000000;

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        parent = new int[n];
        Arrays.fill(parent, -1);
        bfsForParent(0);
        
        bobTime = new int[n];
        Arrays.fill(bobTime, INF);
        int t = 0;
        int cur = bob;
        while (cur != -1) {
            bobTime[cur] = t;
            t++;
            cur = parent[cur];
        }
        
        return dfsAlice(0, 0, amount, new boolean[n]);
    }
    
    private void bfsForParent(int root) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);
        visited[root] = true;
        parent[root] = -1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : graph[cur]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    parent[nxt] = cur;
                    q.offer(nxt);
                }
            }
        }
    }
    
    private int dfsAlice(int node, int time, int[] amount, boolean[] visited) {
        visited[node] = true;
        int profit = 0;
        if (time < bobTime[node]) {
            profit = amount[node];
        } else if (time == bobTime[node]) {
            profit = amount[node] / 2;
        } else {
            profit = 0;
        }
        
        boolean isLeaf = true;
        int best = Integer.MIN_VALUE;
        for (int nxt : graph[node]) {
            if (!visited[nxt]) {
                isLeaf = false;
                int childProfit = dfsAlice(nxt, time + 1, amount, visited);
                best = Math.max(best, childProfit);
            }
        }
        visited[node] = false;
        
        if (isLeaf) {
            return profit;
        } else {
            return profit + best;
        }
    }
}
