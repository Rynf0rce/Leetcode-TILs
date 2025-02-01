class Solution {
    public boolean isArraySpecial(int[] nums) {
        // Loop through the array, stopping at the second-to-last element
        for (int i = 0; i < nums.length - 1; i++) {
            // Check if the current number and the next number have the same parity
            if ((nums[i] % 2) == (nums[i+1] % 2)) {
                return false; // Not a special array
            }
        }
        return true; // All adjacent pairs have different parity
    }
}
