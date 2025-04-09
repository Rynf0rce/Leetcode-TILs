import java.util.HashSet;

class Solution {
    public int minOperations(int[] nums, int k) {
        for (int num : nums) {
            if (num < k) {
                return -1;
            }
        }
        
        HashSet<Integer> distinctGreaterThanK = new HashSet<>();
        for (int num : nums) {
            if (num > k) {
                distinctGreaterThanK.add(num);
            }
        }
        return distinctGreaterThanK.size();
    }
}