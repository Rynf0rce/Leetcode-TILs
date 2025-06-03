class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        
        // ìí ì¶ì  ë°°ì´ë¤
        boolean[] hasBox = new boolean[n];      // ë°ì¤ë¥¼ ê°ì§ê³  ìëì§
        boolean[] hasKey = new boolean[n];      // ì´ì ë¥¼ ê°ì§ê³  ìëì§  
        boolean[] opened = new boolean[n];      // ë°ì¤ë¥¼ ì´ë¯¸ ì´ìëì§
        
        // BFSë¥¼ ìí í
        Queue<Integer> queue = new LinkedList<>();
        
        // ì´ê¸° ë°ì¤ë¤ ì¤ì 
        for (int box : initialBoxes) {
            hasBox[box] = true;
            // ë°ì¤ê° ì´ë ¤ìê±°ë ì´ì ê° ìì¼ë©´ ì¦ì ì´ ì ìì
            if (status[box] == 1 || hasKey[box]) {
                queue.offer(box);
            }
        }
        
        int totalCandies = 0;
        
        while (!queue.isEmpty()) {
            int currentBox = queue.poll();
            
            // ì´ë¯¸ ì° ë°ì¤ë ì¤íµ
            if (opened[currentBox]) continue;
            
            // ë°ì¤ ì´ê¸°
            opened[currentBox] = true;
            totalCandies += candies[currentBox];
            
            // ìë¡ì´ ì´ì ë¤ íë
            for (int key : keys[currentBox]) {
                hasKey[key] = true;
                // ì´ë¯¸ ê°ì§ê³  ìë ë°ì¤ì¸ë° ìì§ ì ì´ìê³ , ì´ì  ì´ì ê° ìê²¼ì¼ë©´ íì ì¶ê°
                if (hasBox[key] && !opened[key]) {
                    queue.offer(key);
                }
            }
            
            // ìë¡ì´ ë°ì¤ë¤ íë
            for (int newBox : containedBoxes[currentBox]) {
                hasBox[newBox] = true;
                // ìë¡ ì»ì ë°ì¤ê° ì´ë ¤ìê±°ë ì´ì ê° ìì¼ë©´ íì ì¶ê°
                if (!opened[newBox] && (status[newBox] == 1 || hasKey[newBox])) {
                    queue.offer(newBox);
                }
            }
        }
        
        return totalCandies;
    }
}