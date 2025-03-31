import java.util.Arrays;

class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (k == 1 || n == k) return 0; // Only one possible partition

        // Step 1: Compute pair sums
        long[] pairSum = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            pairSum[i] = weights[i] + weights[i + 1];
        }

        // Step 2: Sort pair sums
        Arrays.sort(pairSum);

        // Step 3: Compute min and max sums of k-1 pairs
        long minSum = 0, maxSum = 0;
        for (int i = 0; i < k - 1; i++) {
            minSum += pairSum[i];
            maxSum += pairSum[n - 1 - i - 1];
        }

        // Step 4: Return difference
        return maxSum - minSum;
    }
}