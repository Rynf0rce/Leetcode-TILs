class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[301][301];
        int first = nums[0];
        for (int d = 0; d <= 300; d++) {
            dp[first][d] = 1;
        }
        int ans = 1;
        
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            int[] newDP = new int[301];
            for (int d = 0; d <= 300; d++) {
                newDP[d] = 1;
            }
            
            for (int v = 1; v <= 300; v++) {
                int diff = Math.abs(cur - v);
                if (dp[v][diff] > 0) {
                    newDP[diff] = Math.max(newDP[diff], dp[v][diff] + 1);
                }
            }
            for (int d = 300; d >= 1; d--) {
                newDP[d - 1] = Math.max(newDP[d - 1], newDP[d]);
            }
            
            for (int d = 0; d <= 300; d++) {
                dp[cur][d] = Math.max(dp[cur][d], newDP[d]);
                ans = Math.max(ans, dp[cur][d]);
            }
        }
        return ans;
    }
}
