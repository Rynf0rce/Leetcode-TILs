class Solution {
    public int countLargestGroup(int n) {
        Map<Integer, Integer> groupSizes = new HashMap<>();
        
        for (int i = 1; i <= n; i++) {
            int sum = getDigitSum(i);
            groupSizes.put(sum, groupSizes.getOrDefault(sum, 0) + 1);
        }
        
        int maxSize = 0;
        for (int size : groupSizes.values()) {
            maxSize = Math.max(maxSize, size);
        }
        
        int count = 0;
        for (int size : groupSizes.values()) {
            if (size == maxSize) {
                count++;
            }
        }
        
        return count;
    }
    
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}