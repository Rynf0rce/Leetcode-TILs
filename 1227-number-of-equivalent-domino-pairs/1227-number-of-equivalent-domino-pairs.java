class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] counts = new int[100];
        int result = 0;
        
        for (int[] domino : dominoes) {
            int a = Math.min(domino[0], domino[1]);
            int b = Math.max(domino[0], domino[1]);
            int key = a * 10 + b;
            
            result += counts[key];
            
            counts[key]++;
        }
        
        return result;
    }
}