class Solution {
    private static final int MOD = 1000000007;
    
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        
        Arrays.sort(nums);
        
        int[] powers = new int[n];
        powers[0] = 1;
        for (int i = 1; i < n; i++) {
            powers[i] = (powers[i - 1] * 2) % MOD;
        }
        
        int count = 0;
        int left = 0, right = n - 1;
        
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                count = (count + powers[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }
        
        return count;
    }
}