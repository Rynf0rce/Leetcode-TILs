class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long count = 0;
        
        for (int i = 0; i < n; i++) {
            long minVal = (long)lower - nums[i];
            long maxVal = (long)upper - nums[i];
            
            int left = i + 1; // j must be > i
            int right = n - 1;
            
            int start = n;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= minVal) {
                    start = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            
            left = i + 1;
            right = n - 1;
            int end = i;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] <= maxVal) {
                    end = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
            if (start <= end) {
                count += (long)(end - start + 1);
            }
        }
        
        return count;
    }
}