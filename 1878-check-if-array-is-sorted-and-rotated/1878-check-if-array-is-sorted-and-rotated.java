class Solution {
    public boolean check(int[] nums) {
        int count = 0;
        int n = nums.length;
        // Loop through each index and compare with the next element (using modulo for the last element)
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
        }
        // The array is valid if there is at most one "drop"
        return count <= 1;
    }
}
