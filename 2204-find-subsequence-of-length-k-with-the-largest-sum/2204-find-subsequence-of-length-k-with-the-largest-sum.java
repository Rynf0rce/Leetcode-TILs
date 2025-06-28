class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        
        int[][] valueIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            valueIndex[i][0] = nums[i];
            valueIndex[i][1] = i;
        }
        
        Arrays.sort(valueIndex, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(b[0], a[0]); 
            }
            return Integer.compare(a[1], b[1]);
        });
        
        int[] selectedIndices = new int[k];
        for (int i = 0; i < k; i++) {
            selectedIndices[i] = valueIndex[i][1];
        }
        
        Arrays.sort(selectedIndices);
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = nums[selectedIndices[i]];
        }
        
        return result;
    }
}