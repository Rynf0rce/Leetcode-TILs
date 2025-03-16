class Solution {
    public long repairCars(int[] ranks, int cars) {
        long low = 1;
        long high = 0;
        
        for (int rank : ranks) {
            high = Math.max(high, (long) rank * cars * cars);
        }
        
        long ans = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            long totalCars = 0;
            for (int rank : ranks) {
                totalCars += (long) Math.sqrt((double) mid / rank);
            }
            
            if (totalCars >= cars) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
}