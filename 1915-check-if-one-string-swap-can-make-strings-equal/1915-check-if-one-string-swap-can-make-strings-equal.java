class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        
        int n = s1.length();
        int diffCount = 0;
        int firstDiff = -1, secondDiff = -1;
        
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCount++;
                if (diffCount == 1) {
                    firstDiff = i;
                } else if (diffCount == 2) {
                    secondDiff = i;
                } else {
                    return false;
                }
            }
        }
        
        return diffCount == 2 &&
               s1.charAt(firstDiff) == s2.charAt(secondDiff) &&
               s1.charAt(secondDiff) == s2.charAt(firstDiff);
    }
}
