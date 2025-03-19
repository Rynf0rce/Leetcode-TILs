class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int operations = 0;
        int[] diff = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            if (i > 0) diff[i] += diff[i - 1];
            int flipCount = diff[i];
            
            int effectiveValue = nums[i] ^ (flipCount % 2);
            
            if (effectiveValue == 0) {
                if (i + 2 >= n) {
                    return -1;
                }
                diff[i]++;
                if (i + 3 < n) diff[i + 3]--;
                operations++;
            }
        }
        return operations;
    }
}