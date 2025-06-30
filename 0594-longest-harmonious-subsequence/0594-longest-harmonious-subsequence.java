class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        int maxLength = 0;
        
        for (int num : count.keySet()) {
            if (count.containsKey(num + 1)) {
                int currentLength = count.get(num) + count.get(num + 1);
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        
        return maxLength;
    }
    
    public int findLHSAlternative(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        int maxLength = 0;
        
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            
            if (count.containsKey(num + 1)) {
                maxLength = Math.max(maxLength, count.get(num) + count.get(num + 1));
            }
            if (count.containsKey(num - 1)) {
                maxLength = Math.max(maxLength, count.get(num) + count.get(num - 1));
            }
        }
        
        return maxLength;
    }
}