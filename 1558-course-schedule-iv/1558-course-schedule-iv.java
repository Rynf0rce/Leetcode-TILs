import java.util.*;

class Solution {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // 1) ì¸ì íë ¬ reachable[u][v] = u -> vê° ê°ë¥íì§
        boolean[][] reachable = new boolean[numCourses][numCourses];
        
        // 2) ì´ê¸°í: ì§ì  ê°ì (u->v)
        for (int[] edge : prerequisites) {
            int u = edge[0], v = edge[1];
            reachable[u][v] = true;
        }

        // 3) Floyd-Warshall ë°©ìì¼ë¡ ëª¨ë  ì ê²½ë¡ ê³ì°
        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                if (reachable[i][k]) {
                    for (int j = 0; j < numCourses; j++) {
                        if (reachable[k][j]) {
                            reachable[i][j] = true;
                        }
                    }
                }
            }
        }

        // 4) ì¿¼ë¦¬ ì²ë¦¬: [u,v] ìì reachable[u][v] ? 
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (int[] q : queries) {
            ans.add(reachable[q[0]][q[1]]);
        }
        return ans;
    }
}
