class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int[] values = new int[m * n];
        

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                values[i * n + j] = grid[i][j];
            }
        }

        int remainder = values[0] % x;
        for (int val : values) {
            if (val % x != remainder) {
                return -1;
            }
        }
        
        Arrays.sort(values);
    
        int median = values[(m * n) / 2];
        int diff = (median % x) - remainder;
        if (diff < 0) diff += x;
        median -= diff;
        
        long operations = 0;
        for (int val : values) {
            operations += Math.abs(val - median) / x;
        }
        
        return (int) operations;
    }
}