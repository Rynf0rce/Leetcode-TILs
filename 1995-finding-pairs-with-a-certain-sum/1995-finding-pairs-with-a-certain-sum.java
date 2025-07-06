class FindSumPairs {
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> nums2Freq; // nums2ì ë¹ë ê´ë¦¬
    
    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        
        this.nums2Freq = new HashMap<>();
        for (int num : nums2) {
            nums2Freq.put(num, nums2Freq.getOrDefault(num, 0) + 1);
        }
    }
    
    public void add(int index, int val) {
        int oldValue = nums2[index];
        int newValue = oldValue + val;
        
        nums2Freq.put(oldValue, nums2Freq.get(oldValue) - 1);
        if (nums2Freq.get(oldValue) == 0) {
            nums2Freq.remove(oldValue);
        }
        
        nums2Freq.put(newValue, nums2Freq.getOrDefault(newValue, 0) + 1);
        
        nums2[index] = newValue;
    }
    
    public int count(int tot) {
        int pairs = 0;
        
        for (int num1 : nums1) {
            int target = tot - num1;
            pairs += nums2Freq.getOrDefault(target, 0);
        }
        
        return pairs;
    }
}