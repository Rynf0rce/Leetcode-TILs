class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long currSum = 0;
        long minPrefixSum = 0;
        long maxPrefixSum = 0;
        
        for (int diff : differences) {
            currSum += diff;
            minPrefixSum = Math.min(minPrefixSum, currSum);
            maxPrefixSum = Math.max(maxPrefixSum, currSum);
        }
        
        long maxLowerBound = lower - minPrefixSum;
        long minUpperBound = upper - maxPrefixSum;
        
        long count = minUpperBound - maxLowerBound + 1;
        
        return count > 0 ? (int)count : 0;
    }
}