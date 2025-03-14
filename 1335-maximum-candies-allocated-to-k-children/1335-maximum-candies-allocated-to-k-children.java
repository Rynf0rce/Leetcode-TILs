class Solution {
    public int maximumCandies(int[] candies, long k) {
        // Calculate total candies to check if allocation is possible
        long totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }
        if (totalCandies < k) {
            return 0;
        }
        
        // Find maximum pile size for binary search upper bound
        int maxPile = 0;
        for (int candy : candies) {
            maxPile = Math.max(maxPile, candy);
        }
        
        // Binary search
        int low = 1;
        int high = maxPile;
        int ans = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // Calculate total sub-piles possible with size mid
            long subPiles = 0;
            for (int candy : candies) {
                subPiles += (long) candy / mid;
            }
            
            if (subPiles >= k) {
                ans = mid;  // This is a valid answer, try for larger
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
}