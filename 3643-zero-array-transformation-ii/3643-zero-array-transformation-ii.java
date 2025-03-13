class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        
        // ë°°ì´ì´ ì´ë¯¸ ëª¨ë 0ì¸ì§ íì¸
        boolean allZero = true;
        for (int num : nums) {
            if (num > 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) return 0;
        
        // ìµì kë¥¼ ì°¾ê¸° ìí ì´ì§ íì
        int left = 0;
        int right = m - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canMakeZero(nums, queries, mid)) {
                result = mid + 1; // kë 1-based
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
    
    private boolean canMakeZero(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        long[] diff = new long[n + 1];
        
        // 0ë¶í° kê¹ì§ ì¿¼ë¦¬ ì ì©
        for (int i = 0; i <= k && i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];
            diff[l] += val;
            if (r + 1 < n) {
                diff[r + 1] -= val;
            }
        }
        
        // ì ëì¬ í© ê³ì° ë° ì¡°ê±´ íì¸
        long current = 0;
        for (int i = 0; i < n; i++) {
            current += diff[i];
            if (current < nums[i]) {
                return false;
            }
        }
        return true;
    }
}