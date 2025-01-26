import java.util.*;

public class Solution {

    // ìµì¢ API: favorite[] -> ìµë ì´ë ê°ë¥ ì¸ì
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        
        // 1) 2ì¬ì´í´(ê¸¸ì´2) ì°¾ê¸° & ììì ê±°
        //    - twoCycleEdgesì (i->j, j->i) ì ì¥íì¬ ë¹ë ì ì¤íµ
        //    - out[i] = -1ë¡ ì¼ë° ì¬ì´í´ íì(ê¸¸ì´>=3) ì ì¸
        int[] out = Arrays.copyOf(favorite, n);
        List<int[]> twoCyclePairs = new ArrayList<>();
        // set of edges( (i<<32)+j ) that represent 2-cycle edges
        Set<Long> twoCycleEdges = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            int j = out[i];
            if (j > i && favorite[j] == i) {
                // (i,j)ë 2ì¬ì´í´
                twoCyclePairs.add(new int[]{i, j});
                // ê°ì  ì ê±°
                out[i] = -1;
                out[j] = -1;
                
                // 2ì¬ì´í´ ê°ì  encode
                twoCycleEdges.add( encodeEdge(i,j) );
                twoCycleEdges.add( encodeEdge(j,i) );
            }
        }

        // 2) ê¸¸ì´>=3 ì¬ì´í´ì ìµë í¬ê¸°
        int maxCycleSize = findLargestCycle(out);

        // 3) 2ì¬ì´í´ + ì²´ì¸:
        //    ì­ë°©í¥ ê·¸ëí revAdj: v->u if favorite[v]=u AND (v->u NOT in twoCycleEdges)
        //    => chainLen(u, block=??)
        List<List<Integer>> revAdj = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            revAdj.add(new ArrayList<>());
        }
        for(int v=0; v<n; v++){
            long edge = encodeEdge(v, favorite[v]);
            if (!twoCycleEdges.contains(edge)) {
                // 2-ì¬ì´í´ ê°ì ì´ ìëë©´ ì­ë°©í¥ ê·¸ëíì ì¶ê°
                revAdj.get(favorite[v]).add(v);
            }
        }

        memo = new HashMap<>();
        int sumTwoCycle = 0;
        for(int[] pair: twoCyclePairs){
            int i = pair[0];
            int j = pair[1];
            int ci = computeChainLen(i,j, revAdj);
            int cj = computeChainLen(j,i, revAdj);
            sumTwoCycle += (ci + cj + 2);
        }

        return Math.max(maxCycleSize, sumTwoCycle);
    }

    // ---------- (A) ê¸¸ì´>=3 ì¬ì´í´ ì°¾ê¸° -----------
    // out[i] = next node or -1
    // state: 0=ë¯¸ë°©ë¬¸,1=ë°©ë¬¸ì¤,2=ë°©ë¬¸ë
    // depth[i] : DFS stackì index
    private int findLargestCycle(int[] out) {
        int n= out.length;
        int[] state=new int[n];
        int[] depth=new int[n];
        Arrays.fill(depth, -1);
        int ans=0;
        for(int i=0;i<n;i++){
            if(state[i]==0){
                ans= Math.max(ans, dfsCycle(i, out, state, depth));
            }
        }
        return ans;
    }
    private int dfsCycle(int start, int[] out, int[] state, int[] depth){
        List<Integer> stack=new ArrayList<>();
        int cur=start;
        int cycLen=0;
        while(true){
            if(cur<0) break; // no edge
            if(state[cur]==1){
                int idx= depth[cur];
                cycLen= stack.size()- idx;
                break;
            }
            if(state[cur]==2){
                break;
            }
            state[cur]=1;
            depth[cur]= stack.size();
            stack.add(cur);
            cur= out[cur];
        }
        for(int x: stack){
            state[x]=2;
        }
        return cycLen;
    }

    // ---------- (B) chainLen(u, block): ì­ë°©í¥ìì block->u ê°ì  ì ì¸
    private Map<Long,Integer> memo;
    private int computeChainLen(int u, int block, List<List<Integer>> revAdj){
        long key = ((long)u<<32) ^ ((long)block & 0xffffffffL);
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        int best=0;
        for(int v: revAdj.get(u)){
            if(v==block) continue; 
            int c = computeChainLen(v, block, revAdj)+1;
            best=Math.max(best,c);
        }
        memo.put(key,best);
        return best;
    }

    // encodeEdge(i->j) => ( (long)i<<32 ) | j
    private long encodeEdge(int i, int j){
        return ((long)i<<32) | (j & 0xffffffffL);
    }
}
