class Solution {
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> distinctSet = new HashSet<>();
        for (int num : nums) {
            distinctSet.add(num);
        }
        int totalDistinct = distinctSet.size();
        
        int count = 0;
        
        for (int start = 0; start < nums.length; start++) {
            Set<Integer> currentSet = new HashSet<>();
                        for (int end = start; end < nums.length; end++) {
                currentSet.add(nums[end]);
                if (currentSet.size() == totalDistinct) {
                    count += nums.length - end;
                    break;
                }
            }
        }
        
        return count;
    }
}