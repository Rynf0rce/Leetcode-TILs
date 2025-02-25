class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1_000_000_007;
        long ans = 0;
        long evenCount = 1;
        long oddCount = 0;
        int prefix = 0;
        
        for (int num : arr) {
            prefix += num;
            if ((prefix & 1) == 0) {
                ans = (ans + oddCount) % mod;
                evenCount++;
            } else {
                ans = (ans + evenCount) % mod;
                oddCount++;
            }
        }
        return (int) ans;
    }
}
