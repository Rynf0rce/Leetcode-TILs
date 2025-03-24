import java.util.Arrays;

class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        
        int coveredDays = 0;
        int currentStart = meetings[0][0];
        int currentEnd = meetings[0][1];
        
        for (int i = 1; i < meetings.length; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];
            
            if (start > currentEnd) {
                coveredDays += currentEnd - currentStart + 1;
                currentStart = start;
                currentEnd = end;
            } else {
                currentEnd = Math.max(currentEnd, end);
            }
        }
        coveredDays += currentEnd - currentStart + 1;
        return days - coveredDays;
    }
}