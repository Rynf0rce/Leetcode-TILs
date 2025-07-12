class Solution {
    private Map<Integer, int[]> memo = new HashMap<>();
    private int firstPlayer, secondPlayer;
    
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        this.firstPlayer = firstPlayer - 1; // 0-based ì¸ë±ì¤ë¡ ë³í
        this.secondPlayer = secondPlayer - 1;
        
        // ì´ê¸° ìí: ëª¨ë  íë ì´ì´ê° ì°¸ì¬ (ë¹í¸ë§ì¤í¬ë¡ íí)
        int initialMask = (1 << n) - 1;
        
        return dfs(initialMask, 1);
    }
    
    private int[] dfs(int mask, int round) {
        // ë©ëª¨ì´ì ì´ì íì¸
        if (memo.containsKey(mask)) {
            int[] cached = memo.get(mask);
            return new int[]{cached[0], cached[1]};
        }
        
        // íì¬ ë¨ììë íë ì´ì´ë¤ì ë¦¬ì¤í¸ë¡ ë³í
        List<Integer> players = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            if ((mask & (1 << i)) != 0) {
                players.add(i);
            }
        }
        
        int n = players.size();
        
        // ê¸°ì  ì¡°ê±´: firstPlayerì secondPlayerë§ ë¨ì ê²½ì°
        if (n == 2 && players.contains(firstPlayer) && players.contains(secondPlayer)) {
            memo.put(mask, new int[]{round, round});
            return new int[]{round, round};
        }
        
        // firstPlayerë secondPlayer ì¤ íëë¼ë ìì¼ë©´ ë¶ê°ë¥
        if (!players.contains(firstPlayer) || !players.contains(secondPlayer)) {
            memo.put(mask, new int[]{Integer.MAX_VALUE, -1});
            return new int[]{Integer.MAX_VALUE, -1};
        }
        
        // firstPlayerì secondPlayerê° ì´ë² ë¼ì´ëìì ë§ëëì§ íì¸
        if (willMeetThisRound(players)) {
            memo.put(mask, new int[]{round, round});
            return new int[]{round, round};
        }
        
        // ë¤ì ë¼ì´ëë¡ ì§ííë ëª¨ë  ê°ë¥í ê²½ì°ë¤ì ìë®¬ë ì´ì
        List<Integer> nextRoundCandidates = simulateRound(players);
        
        int earliestRound = Integer.MAX_VALUE;
        int latestRound = -1;
        
        for (int nextMask : nextRoundCandidates) {
            int[] result = dfs(nextMask, round + 1);
            if (result[0] != Integer.MAX_VALUE) {
                earliestRound = Math.min(earliestRound, result[0]);
                latestRound = Math.max(latestRound, result[1]);
            }
        }
        
        int[] answer = new int[]{earliestRound, latestRound};
        memo.put(mask, answer);
        return answer;
    }
    
    // ì´ë² ë¼ì´ëìì firstPlayerì secondPlayerê° ë§ëëì§ íì¸
    private boolean willMeetThisRound(List<Integer> players) {
        int n = players.size();
        
        for (int i = 0; i < n / 2; i++) {
            int left = players.get(i);
            int right = players.get(n - 1 - i);
            
            if ((left == firstPlayer && right == secondPlayer) ||
                (left == secondPlayer && right == firstPlayer)) {
                return true;
            }
        }
        
        return false;
    }
    
    // í ë¼ì´ëë¥¼ ìë®¬ë ì´ìíì¬ ê°ë¥í ëª¨ë  ë¤ì ìíë¤ì ë°í
    private List<Integer> simulateRound(List<Integer> players) {
        List<Integer> results = new ArrayList<>();
        generateAllPossibleOutcomes(players, 0, new ArrayList<>(), results);
        return results;
    }
    
    // ëª¨ë  ê°ë¥í ê²½ê¸° ê²°ê³¼ë¥¼ ìì± (ë°±í¸ëí¹)
    private void generateAllPossibleOutcomes(List<Integer> players, int index, 
                                           List<Integer> winners, List<Integer> results) {
        int n = players.size();
        
        // íìì¸ ê²½ì° ê°ì´ë° íë ì´ì´ë ìë ì§ì¶
        if (n % 2 == 1 && index == n / 2) {
            winners.add(players.get(index));
            generateAllPossibleOutcomes(players, index + 1, winners, results);
            winners.remove(winners.size() - 1);
            return;
        }
        
        // ëª¨ë  ê²½ê¸°ê° ëë ê²½ì°
        if (index >= n / 2) {
            // ë¹í¸ë§ì¤í¬ë¡ ë³í
            int mask = 0;
            for (int winner : winners) {
                mask |= (1 << winner);
            }
            results.add(mask);
            return;
        }
        
        int left = players.get(index);
        int right = players.get(n - 1 - index);
        
        // firstPlayerë secondPlayerê° í¬í¨ë ê²½ê¸°ë ê²°ê³¼ê° ì í´ì§
        if (left == firstPlayer || left == secondPlayer) {
            winners.add(left);
            generateAllPossibleOutcomes(players, index + 1, winners, results);
            winners.remove(winners.size() - 1);
        } else if (right == firstPlayer || right == secondPlayer) {
            winners.add(right);
            generateAllPossibleOutcomes(players, index + 1, winners, results);
            winners.remove(winners.size() - 1);
        } else {
            // ë ë¤ ì¼ë° íë ì´ì´ì¸ ê²½ì° ë ê°ì§ ê²°ê³¼ ëª¨ë ê°ë¥
            winners.add(left);
            generateAllPossibleOutcomes(players, index + 1, winners, results);
            winners.remove(winners.size() - 1);
            
            winners.add(right);
            generateAllPossibleOutcomes(players, index + 1, winners, results);
            winners.remove(winners.size() - 1);
        }
    }
}