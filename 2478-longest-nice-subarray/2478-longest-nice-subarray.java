class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int currentOr = 0; 
        int maxLength = 1;

        for (int right = 0; right < n; right++) {
            while ((currentOr & nums[right]) != 0) {
                currentOr ^= nums[left];
                left++;
            }
            currentOr |= nums[right];
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}