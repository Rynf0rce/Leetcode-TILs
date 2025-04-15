class Solution {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums1[i]] = i;
        }
        
        BIT bit = new BIT(n);
        long result = 0;
        
        for (int i = 0; i < n; i++) {
            int idx = pos[nums2[i]]; 
            long leftSmaller = bit.query(idx - 1);
            long rightLarger = (n - 1 - idx) - (i - bit.query(idx));
            result += leftSmaller * rightLarger;
            bit.update(idx, 1);
        }
        return result;
    }
    
    class BIT {
        int[] tree;
        
        BIT(int n) {
            tree = new int[n + 1];
        }
        
        void update(int idx, int val) {
            idx++;
            while (idx < tree.length) {
                tree[idx] += val;
                idx += idx & -idx;
            }
        }
        
        int query(int idx) {
            if (idx < 0) return 0; 
            idx++;
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }
}