import java.util.HashSet;

class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int operations = 0;
        int start = 0;
        
        while (start < n) {
            HashSet<Integer> set = new HashSet<>();
            boolean isDistinct = true;
            for (int i = start; i < n; i++) {
                if (!set.add(nums[i])) {
                    isDistinct = false;
                    break;
                }
            }
            
            if (isDistinct) return operations;
            
            int elementsToRemove = Math.min(3, n - start);
            start += elementsToRemove;
            operations++;
        }
        
        return operations;
    }
}