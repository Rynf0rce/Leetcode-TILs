import java.util.*;

class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long totalPairs = (long) n * (n - 1) / 2;
        long goodPairs = 0;
        Map<Long, Long> freq = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            long diff = i - (long) nums[i];
            long count = freq.getOrDefault(diff, 0L);
            goodPairs += count;
            freq.put(diff, count + 1);
        }
        
        return totalPairs - goodPairs;
    }
}
