import java.util.*;

class Solution {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> productCount = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int product = nums[i] * nums[j];
                productCount.put(product, productCount.getOrDefault(product, 0) + 1);
            }
        }
        
        int result = 0;
        for (int count : productCount.values()) {
            if (count > 1) {
                result += 8 * (count * (count - 1) / 2);
            }
        }
        
        return result;
    }
}
