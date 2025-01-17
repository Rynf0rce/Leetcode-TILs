class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        
        // Try assuming original[0] = 0
        if (isValidOriginal(derived, 0, n)) {
            return true;
        }
        
        // Try assuming original[0] = 1
        return isValidOriginal(derived, 1, n);
    }
    
    private boolean isValidOriginal(int[] derived, int initial, int n) {
        int[] original = new int[n];
        original[0] = initial;
        
        // Build the rest of the original array
        for (int i = 0; i < n - 1; i++) {
            original[i + 1] = derived[i] ^ original[i];
        }
        
        // Check if the final derived[n-1] matches
        return (original[n - 1] ^ original[0]) == derived[n - 1];
    }
}
