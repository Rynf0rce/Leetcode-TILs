class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int currentOr = 0; // Bitwise OR of elements in the current window
        int maxLength = 1; // Minimum length is 1 (subarrays of length 1 are nice)

        for (int right = 0; right < n; right++) {
            // Check for bit overlap with the new element
            while ((currentOr & nums[right]) != 0) {
                // Remove the leftmost element to resolve overlap
                currentOr ^= nums[left];
                left++;
            }
            // Add the new element to the window
            currentOr |= nums[right];
            // Update the maximum length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}