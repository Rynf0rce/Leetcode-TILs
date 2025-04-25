class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        // Map to store prefix remainder frequencies
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1); // Initialize with 0 remainder count = 1 (empty prefix)
        
        long result = 0;
        int prefix = 0; // Running count mod modulo
        
        for (int num : nums) {
            // Convert condition check to 1 or 0
            prefix = (prefix + (num % modulo == k ? 1 : 0)) % modulo;
            
            // Calculate the remainder we need to find
            // We want (current_prefix - previous_prefix) % modulo = k
            // This means previous_prefix % modulo = (current_prefix - k + modulo) % modulo
            int target = (prefix - k + modulo) % modulo;
            
            // Add occurrences of target to result
            result += prefixMap.getOrDefault(target, 0);
            
            // Update frequency of current prefix remainder
            prefixMap.put(prefix, prefixMap.getOrDefault(prefix, 0) + 1);
        }
        
        return result;
    }
}