import java.util.*;

public class Solution {

    public int magnificentSets(int n, int[][] edges) {
        // 1) ì¸ì  ë¦¬ì¤í¸ êµ¬ì± (1..n)
        List<List<Integer>> adj = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        // 2) ì°ê²°ìì ì°¾ê¸°
        boolean[] visited = new boolean[n+1];
        List<List<Integer>> components = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                dfsComp(i, adj, visited, comp);
                components.add(comp);
            }
        }

        // 3) ê° ì°ê²°ìì ì²ë¦¬
        int totalGroups = 0;
        for (List<Integer> comp : components) {
            // (a) check bipartite
            if (!isBipartite(comp, adj)) {
                return -1;
            }
            // (b) êµ¬í´ë³¸ë¤: í´ë¹ ì°ê²°ìììì "ê°ë¥í BFS ë ì´ì´ ì" ìµëê°
            int bestLayer = 0;
            for (int node : comp) {
                int layerCount = bfsLayerCount(node, comp, adj);
                if (layerCount > bestLayer) {
                    bestLayer = layerCount;
                }
            }
            totalGroups += bestLayer;
        }
        return totalGroups;
    }

    // ---- ì°ê²°ìì DFS
    private void dfsComp(int start, List<List<Integer>> adj, boolean[] visited, List<Integer> comp) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            comp.add(u);
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    stack.push(v);
                }
            }
        }
    }

    // ---- bipartite check (if odd cycle => false)
    // comp ë´ ë¸ëë§ color
    private boolean isBipartite(List<Integer> comp, List<List<Integer>> adj) {
        Map<Integer,Integer> color = new HashMap<>();
        for (int node : comp) {
            color.put(node, -1);
        }
        for (int start : comp) {
            if (color.get(start) == -1) {
                // BFS
                Queue<Integer> q = new LinkedList<>();
                q.offer(start);
                color.put(start, 0);

                while (!q.isEmpty()) {
                    int u = q.poll();
                    int c = color.get(u);
                    for (int v : adj.get(u)) {
                        if (!color.containsKey(v)) continue; // comp ë°
                        if (color.get(v) == -1) {
                            color.put(v, c ^ 1);
                            q.offer(v);
                        } else if (color.get(v) == c) {
                            // conflict => odd cycle
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // ---- bfsLayerCount: comp ë´ìì nodeë¥¼ ììì¼ë¡ BFSíì ë, "ë ì´ì´(ì)" =?
    // BFS ë ì´ì´ë: ë ë²¨1(ììë¸ë), ë ë²¨2(ê·¸ ë¤ì ì¸ì ), ...
    private int bfsLayerCount(int start, List<Integer> comp, List<List<Integer>> adj) {
        Set<Integer> inComp = new HashSet<>(comp);
        // dist array => -1
        Map<Integer,Integer> dist = new HashMap<>();
        for (int c : comp) {
            dist.put(c, -1);
        }
        dist.put(start, 0);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        int layerCount = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // í ë ë²¨ ì²ë¦¬
            layerCount++;
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                int d = dist.get(u);
                for (int v : adj.get(u)) {
                    if (inComp.contains(v) && dist.get(v) == -1) {
                        dist.put(v, d + 1);
                        queue.offer(v);
                    }
                }
            }
        }
        return layerCount;
    }
}
