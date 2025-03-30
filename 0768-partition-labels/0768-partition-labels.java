import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        
        int start = 0;         
        int currentEnd = 0;  
        List<Integer> result = new ArrayList<>(); 
        
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            currentEnd = Math.max(currentEnd, last[idx]); 
            if (i == currentEnd) {  
                result.add(i - start + 1); 
                start = i + 1;
            }
        }
        return result;
    }
}