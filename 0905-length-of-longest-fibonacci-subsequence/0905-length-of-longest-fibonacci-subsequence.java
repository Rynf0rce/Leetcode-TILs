import java.util.*;

class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(arr[i], i);
        }
        
        int[][] dp = new int[n][n];
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 2);
        }
        
        for (int j = 0; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                int needed = arr[j] - arr[i];
                if (needed < arr[i] && index.containsKey(needed)) {
                    int k = index.get(needed);
                    dp[i][j] = dp[k][i] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        
        return ans >= 3 ? ans : 0;
    }
}
