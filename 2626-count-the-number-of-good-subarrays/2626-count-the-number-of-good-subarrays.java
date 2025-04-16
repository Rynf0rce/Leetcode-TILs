class Solution {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        long pairs = 0;
        long result = 0;
        
        int left = 0;
        for (int right = 0; right < n; right++) {
            int currentFreq = freqMap.getOrDefault(nums[right], 0);
            pairs += currentFreq;
            freqMap.put(nums[right], currentFreq + 1);
            
            while (left <= right && pairs >= k) {
                result += (n - right); 
                
                int leftFreq = freqMap.get(nums[left]);
                freqMap.put(nums[left], leftFreq - 1);
                pairs -= (leftFreq - 1); 
                
                left++;
            }
        }
        
        return result;
    }
}