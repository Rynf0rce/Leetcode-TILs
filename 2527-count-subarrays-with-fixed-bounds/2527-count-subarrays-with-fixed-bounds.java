class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long result = 0;
        int lastOutOfBounds = -1;
        int lastMinK = -1;
        int lastMaxK = -1;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                lastOutOfBounds = i;
            }
            
            if (nums[i] == minK) {
                lastMinK = i;
            }
            if (nums[i] == maxK) {
                lastMaxK = i;
            }

            if (lastMinK > lastOutOfBounds && lastMaxK > lastOutOfBounds) {
                result += Math.min(lastMinK, lastMaxK) - lastOutOfBounds;
            }
        }
        
        return result;
    }
}