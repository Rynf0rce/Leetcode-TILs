class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        char[] result = new char[n];
        for (int i = 0; i < n; i++) {
            // nums[i].charAt(i)ì ë°ëì ë¬¸ìë¥¼ result[i]ì ì ì¥
            result[i] = (nums[i].charAt(i) == '0') ? '1' : '0';
        }
        return new String(result);
    }
}
