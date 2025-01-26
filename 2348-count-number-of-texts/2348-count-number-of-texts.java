class Solution {
    private static final int MOD = 1000000007;
    
    public int countTexts(String pressedKeys) {
        int n = pressedKeys.length();
        // digitë³ ë¬¸ìì ê°ì
        // '7'ê³¼ '9'ë 4ê¸ì, ê·¸ì¸ë 3ê¸ì
        // ì¸ë±ì¤ë¡ digit-'0'
        int[] letterCount = new int[10];
        letterCount[2] = 3; letterCount[3] = 3; letterCount[4] = 3; letterCount[5] = 3;
        letterCount[6] = 3; letterCount[8] = 3;
        letterCount[7] = 4; letterCount[9] = 4;
        
        long[] dp = new long[n+1];
        dp[0] = 1; // ì´ê¸°ê°: ìë¬´ê²ë í´ì ì íì ë ê²½ì°ì ì=1

        for (int i=1; i<=n; i++) {
            char c = pressedKeys.charAt(i-1);
            int digit = c - '0';
            int maxLen = letterCount[digit]; // ex: 3 or 4
            // ë¤ë¡ ìµë maxLenë§í¼ ê°ì digitì¸ì§ íì¸
            int sameCount = 0;
            // jë i-1ìì ë¤ë¡
            for (int j=i-1; j>=0 && (i-1 - j) < maxLen; j--) {
                if (pressedKeys.charAt(j) == c) {
                    sameCount++;
                    dp[i] = (dp[i] + dp[j]) % MOD;
                } else {
                    break; // ë¤ë¥¸ digit ëì¤ë©´ stop
                }
            }
        }
        return (int)dp[n];
    }
}
