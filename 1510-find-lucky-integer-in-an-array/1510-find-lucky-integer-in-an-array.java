class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        
        int maxLucky = -1;
        
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int value = entry.getKey();
            int freq = entry.getValue();
            
            if (value == freq) {
                maxLucky = Math.max(maxLucky, value);
            }
        }
        
        return maxLucky;
    }
}