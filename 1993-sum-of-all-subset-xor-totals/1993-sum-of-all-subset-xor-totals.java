class Solution {
    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int orValue = 0;
        for (int num : nums) {
            orValue |= num;
        }
        
        int sum = 0;
        for (int bit = 0; bit < 20; bit++) {
            if ((orValue & (1 << bit)) != 0) {
                sum += (1 << bit) * (1 << (n - 1));
            }
        }
        
        return sum;
    }
}