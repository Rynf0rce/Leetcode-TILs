class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int total = n * n;
        int[] freq = new int[total + 1]; // 1-indexed frequency array
        
        // Count the frequency of each number in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                freq[grid[i][j]]++;
            }
        }
        
        int repeated = -1, missing = -1;
        for (int num = 1; num <= total; num++) {
            if (freq[num] == 0) {
                missing = num;
            } else if (freq[num] == 2) {
                repeated = num;
            }
        }
        
        return new int[]{repeated, missing};
    }
}