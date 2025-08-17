class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n >= k + maxPts - 1) {
            return 1.0;
        }
        

        double[] dp = new double[k + maxPts];
        double windowSum = 0.0;
        
        for (int i = k; i <= n && i < k + maxPts; i++) {
            dp[i] = 1.0;
            windowSum += dp[i];
        }
        
        for (int i = k - 1; i >= 0; i--) {
            dp[i] = windowSum / maxPts;
            
            windowSum += dp[i];
            
            if (i + maxPts < k + maxPts) {
                windowSum -= dp[i + maxPts];
            }
        }
        
        return dp[0];
    }
}