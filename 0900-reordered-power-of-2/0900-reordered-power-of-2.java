class Solution {
    private int[] countDigits(int num) {
        int[] count = new int[10];
        while (num > 0) {
            count[num % 10]++;
            num /= 10;
        }
        return count;
    }
    
    public boolean reorderedPowerOf2(int n) {
        int[] nCount = countDigits(n);
        int nLength = String.valueOf(n).length();
        
        for (int i = 0; i < 30; i++) {
            int powerOf2 = 1 << i;
            if (String.valueOf(powerOf2).length() == nLength) {
                if (Arrays.equals(nCount, countDigits(powerOf2))) {
                    return true;
                }
            }
        }
        return false;
    }
}