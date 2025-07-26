import java.util.*;

class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        // Editorialì ì íí êµ¬í - O(m + n) ìê°ë³µì¡ë
        int[] bMin1 = new int[n + 1];  // ê° ìì¹ìì ììíë ì ì½ì ìµìê°
        int[] bMin2 = new int[n + 1];  // ê° ìì¹ìì ììíë ì ì½ì ë ë²ì§¸ ìµìê°
        Arrays.fill(bMin1, Integer.MAX_VALUE);
        Arrays.fill(bMin2, Integer.MAX_VALUE);
        
        // Step 1: ê° ìì¹ë³ë¡ ìµìê°ê³¼ ë ë²ì§¸ ìµìê° ê³ì°
        for (int[] pair : conflictingPairs) {
            int a = Math.min(pair[0], pair[1]);  // ì ê·í: a < b
            int b = Math.max(pair[0], pair[1]);
            
            // ìì¹ aìì ììíë ì ì½ bë¥¼ ì¶ê°
            if (bMin1[a] > b) {
                bMin2[a] = bMin1[a];  // ê¸°ì¡´ ìµìê°ì´ ë ë²ì§¸ ìµìê°ì´ ë¨
                bMin1[a] = b;         // ìë¡ì´ ìµìê°
            } else if (bMin2[a] > b) {
                bMin2[a] = b;         // ë ë²ì§¸ ìµìê° ìë°ì´í¸
            }
        }
        
        long res = 0;                     // ê¸°ë³¸ subarray ê°ì
        int ib1 = n;                      // suffix minimumì ìì¹
        int b2 = Integer.MAX_VALUE;       // ë ë²ì§¸ ìµìê°ë¤ì ìµìê°
        long[] delCount = new long[n + 1]; // ê° ìì¹ì ì ì½ ì ê±° ì ì´ìµ
        
        // Step 2: ë¤ììë¶í° suffix minimumì¼ë¡ ê³ì°
        for (int i = n; i >= 1; i--) {
            // suffix minimum ìë°ì´í¸
            if (bMin1[ib1] > bMin1[i]) {
                b2 = Math.min(b2, bMin1[ib1]);  // ì´ì  ìµìê°ì ë ë²ì§¸ íë³´ì ì¶ê°
                ib1 = i;                        // ìë¡ì´ ìµìê° ìì¹
            } else {
                b2 = Math.min(b2, bMin1[i]);    // íì¬ê°ì ë ë²ì§¸ íë³´ì ì¶ê°
            }
            
            // íì¬ ìì¹ììì ê¸°ë³¸ subarray ê°ì ëì 
            res += Math.min(bMin1[ib1], n + 1) - i;
            
            // ib1 ìì¹ì ì ì½ì ì ê±°íì ëì ì´ìµ ê³ì°
            // ìë¡ì´ ìµìê° = min(b2, bMin2[ib1])
            delCount[ib1] += 
                Math.min(Math.min(b2, bMin2[ib1]), n + 1) - 
                Math.min(bMin1[ib1], n + 1);
        }
        
        // Step 3: ìµë ì´ìµ ì°¾ê¸°
        long max = 0;
        for (long val : delCount) {
            max = Math.max(max, val);
        }
        
        return res + max;
    }
}