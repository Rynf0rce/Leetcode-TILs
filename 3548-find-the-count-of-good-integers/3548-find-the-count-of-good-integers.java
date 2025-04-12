import java.util.*;

class Solution {
    private Set<String> visited = new HashSet<>();
    private int result = 0;
    
    public int countGoodIntegers(int n, int k) {
        visited.clear();
        result = 0;
        
        backtrack(new ArrayList<>(), 0, n, k);
        
        return result;
    }
    
    private void backtrack(List<Integer> digits, int index, int n, int k) {
        if (index == (n + 1) / 2) {
            // Create palindrome by mirroring the first half
            List<Integer> palindrome = createPalindrome(digits, n);
            
            // Check if the palindrome is divisible by k
            long num = vectorToNumber(palindrome);
            if (num % k == 0) {
                // Count valid permutations of these digits
                int[] freq = new int[10];
                for (int digit : palindrome) {
                    freq[digit]++;
                }
                
                // Create a unique key for this frequency distribution
                String key = Arrays.toString(freq);
                if (!visited.contains(key)) {
                    visited.add(key);
                    
                    // Count total permutations
                    long totalPerms = factorial(n);
                    for (int i = 0; i < 10; i++) {
                        totalPerms /= factorial(freq[i]);
                    }
                    
                    // Exclude permutations with leading zero
                    if (freq[0] > 0) {
                        int[] freqWithoutLeadingZero = Arrays.copyOf(freq, 10);
                        freqWithoutLeadingZero[0]--;
                        
                        long permsWithLeadingZero = factorial(n - 1);
                        for (int i = 0; i < 10; i++) {
                            permsWithLeadingZero /= factorial(freqWithoutLeadingZero[i]);
                        }
                        
                        totalPerms -= permsWithLeadingZero;
                    }
                    
                    result += totalPerms;
                }
            }
            return;
        }
        
        // Try each digit for the current position
        int start = (index == 0) ? 1 : 0; // Skip leading zero at first position
        for (int digit = start; digit <= 9; digit++) {
            digits.add(digit);
            backtrack(digits, index + 1, n, k);
            digits.remove(digits.size() - 1);
        }
    }
    
    private List<Integer> createPalindrome(List<Integer> digits, int n) {
        List<Integer> palindrome = new ArrayList<>(digits);
        
        // For odd length, exclude the middle digit when mirroring
        int startIdx = (n % 2 == 0) ? digits.size() - 1 : digits.size() - 2;
        
        // Add the mirror image
        for (int i = startIdx; i >= 0; i--) {
            palindrome.add(digits.get(i));
        }
        
        return palindrome;
    }
    
    private long vectorToNumber(List<Integer> digits) {
        long num = 0;
        for (int digit : digits) {
            num = num * 10 + digit;
        }
        return num;
    }
    
    private long factorial(int n) {
        if (n <= 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}