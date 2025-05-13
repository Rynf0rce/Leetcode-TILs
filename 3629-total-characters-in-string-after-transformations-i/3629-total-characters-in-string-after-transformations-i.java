class Solution {
    public int lengthAfterTransformations(String s, int t) {
        final int MOD = 1_000_000_007;
        
        long[] count = new long[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        for (int i = 0; i < t; i++) {
            long[] nextCount = new long[26];
            
            for (int j = 0; j < 26; j++) {
                if (j == 25) {
                    nextCount[0] = (nextCount[0] + count[j]) % MOD; // 'a' ì¦ê°
                    nextCount[1] = (nextCount[1] + count[j]) % MOD; // 'b' ì¦ê°
                } else {
                    nextCount[j + 1] = (nextCount[j + 1] + count[j]) % MOD;
                }
            }
            
            count = nextCount;
        }
        
        long totalLength = 0;
        for (long charCount : count) {
            totalLength = (totalLength + charCount) % MOD;
        }
        
        return (int)totalLength;
    }
}