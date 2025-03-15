class Solution {
    public int minCapability(int[] nums, int k) {
        int low = 1;
        int high = 0;
        
        // Find the range for binary search
        for (int num : nums) {
            high = Math.max(high, num);
        }
        
        // Binary search on capability
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // Check if we can rob at least k houses with capability <= mid
            int count = 0;
            int i = 0;
            while (i < nums.length) {
                if (nums[i] <= mid) {
                    count++;    // Rob this house
                    i += 2;     // Skip the next house
                } else {
                    i += 1;     // Skip this house
                }
            }
            
            if (count >= k) {
                ans = mid;      // This capability works, try smaller
                high = mid - 1;
            } else {
                low = mid + 1;  // Need larger capability
            }
        }
        
        return ans;
    }
}