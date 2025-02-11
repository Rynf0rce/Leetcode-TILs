class Solution {
    public String removeOccurrences(String s, String part) {
        int m = part.length();
        int n = s.length();
        int[] lps = computeLPS(part);
        
        StringBuilder sb = new StringBuilder();
        int[] match = new int[n];
        int currentMatch = 0;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            sb.append(c);
            
            while (currentMatch > 0 && c != part.charAt(currentMatch)) {
                currentMatch = lps[currentMatch - 1];
            }
            if (c == part.charAt(currentMatch)) {
                currentMatch++;
            }
            
            match[sb.length() - 1] = currentMatch;
            
            if (currentMatch == m) {
                sb.delete(sb.length() - m, sb.length());
                if (sb.length() > 0) {
                    currentMatch = match[sb.length() - 1];
                } else {
                    currentMatch = 0;
                }
            }
        }
        return sb.toString();
    }
        private int[] computeLPS(String pat) {
        int m = pat.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        
        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
