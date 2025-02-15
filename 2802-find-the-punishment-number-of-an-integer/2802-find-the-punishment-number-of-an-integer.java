class Solution {
    public int punishmentNumber(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (canPartition(i)) {
                result += i * i;
            }
        }
        return result;
    }
    
    private boolean canPartition(int i) {
        long square = (long) i * i;
        String s = Long.toString(square);
        return backtrack(s, 0, 0, i);
    }
    
    private boolean backtrack(String s, int index, int currentSum, int target) {
        if (index == s.length()) {
            return currentSum == target;
        }
        int num = 0;
        for (int j = index; j < s.length(); j++) {
            num = num * 10 + (s.charAt(j) - '0');
            if (currentSum + num > target) {
                break;
            }
            if (backtrack(s, j + 1, currentSum + num, target)) {
                return true;
            }
        }
        return false;
    }
}
