import java.util.*;

class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;
        int[] result = new int[n];
        
        // Map to track the current color of each ball.
        Map<Integer, Integer> ballColor = new HashMap<>();
        // Map to track the frequency of each color.
        Map<Integer, Integer> colorCount = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int ball = queries[i][0];
            int newColor = queries[i][1];
            
            // If this ball is already colored, check if we need to update its old color frequency.
            if (ballColor.containsKey(ball)) {
                int oldColor = ballColor.get(ball);
                // If the ball already has the new color, no update is needed.
                if (oldColor == newColor) {
                    result[i] = colorCount.size();
                    continue;
                }
                // Otherwise, reduce the frequency of the old color.
                int count = colorCount.get(oldColor);
                if (count == 1) {
                    colorCount.remove(oldColor);
                } else {
                    colorCount.put(oldColor, count - 1);
                }
            }
            
            // Update the ball's color.
            ballColor.put(ball, newColor);
            colorCount.put(newColor, colorCount.getOrDefault(newColor, 0) + 1);
            
            // The number of distinct colors is the number of keys in colorCount.
            result[i] = colorCount.size();
        }
        
        return result;
    }
}
