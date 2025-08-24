class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        
        // left[i] = iìì ì¼ìª½ì¼ë¡ ì°ìë 1ì ê°ì
        int[] left = new int[n];
        // right[i] = iìì ì¤ë¥¸ìª½ì¼ë¡ ì°ìë 1ì ê°ì
        int[] right = new int[n];
        
        // ì¼ìª½ìì ì¤ë¥¸ìª½ì¼ë¡ ì¤ìº
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                left[i] = (i > 0 ? left[i - 1] : 0) + 1;
            }
        }
        
        // ì¤ë¥¸ìª½ìì ì¼ìª½ì¼ë¡ ì¤ìº
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == 1) {
                right[i] = (i < n - 1 ? right[i + 1] : 0) + 1;
            }
        }
        
        int maxLength = 0;
        
        // ê° ìì¹ë¥¼ ì­ì íì ëì ìµë ê¸¸ì´ ê³ì°
        for (int i = 0; i < n; i++) {
            int leftOnes = i > 0 ? left[i - 1] : 0;
            int rightOnes = i < n - 1 ? right[i + 1] : 0;
            maxLength = Math.max(maxLength, leftOnes + rightOnes);
        }
        
        return maxLength;
    }
}