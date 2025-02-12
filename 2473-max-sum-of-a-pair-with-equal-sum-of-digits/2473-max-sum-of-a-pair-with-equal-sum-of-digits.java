import java.util.*;

class Solution {
    public int maximumSum(int[] nums) {
        Map<Integer, int[]> groups = new HashMap<>();
        int ans = -1;
        
        for (int num : nums) {
            int ds = sumDigits(num);
            if (!groups.containsKey(ds)) {
                groups.put(ds, new int[]{num, -1});
            } else {
                int[] pair = groups.get(ds);
                if (num > pair[0]) {
                    pair[1] = pair[0];
                    pair[0] = num;
                } else if (num > pair[1]) {
                    pair[1] = num;
                }
            }
        }
        
        for (int[] pair : groups.values()) {
            if (pair[1] != -1) {
                ans = Math.max(ans, pair[0] + pair[1]);
            }
        }
        
        return ans;
    }
    
    private int sumDigits(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}
