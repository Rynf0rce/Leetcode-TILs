class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        
        dp[n] = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            int points = questions[i][0];
            int brainpower = questions[i][1];
            int nextIndex = Math.min(n, i + brainpower + 1);
            dp[i] = Math.max(points + dp[nextIndex], dp[i + 1]);
        }
        
        return dp[0];
    }
}