import java.util.*;

class Solution {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> goodDays = new ArrayList<>();
        int n = security.length;
        
        if (time == 0) {
            for (int i = 0; i < n; i++) {
                goodDays.add(i);
            }
            return goodDays;
        }
        
        int[] left = new int[n];
        int[] right = new int[n];
        
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        
        for (int i = time; i < n - time; i++) {
            if (left[i] >= time + 1 && right[i] >= time + 1) {
                goodDays.add(i);
            }
        }
        return goodDays;
    }
}
