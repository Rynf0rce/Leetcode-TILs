class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int left = 0;
        int result = 0;
        Map<Character, Integer> countMap = new HashMap<>();
        
        // Iterate over the string with the right pointer
        for (int right = 0; right < n; right++) {
            // Add the current character to the window
            char rightChar = s.charAt(right);
            countMap.put(rightChar, countMap.getOrDefault(rightChar, 0) + 1);
            
            // Check if the window contains all three characters
            while (countMap.size() == 3 && left <= right) {
                // Count all substrings from left to any position >= right
                result += (n - right);
                // Shrink the window by removing the leftmost character
                char leftChar = s.charAt(left);
                countMap.put(leftChar, countMap.get(leftChar) - 1);
                if (countMap.get(leftChar) == 0) {
                    countMap.remove(leftChar);
                }
                left++;
            }
        }
        
        return result;
    }
}