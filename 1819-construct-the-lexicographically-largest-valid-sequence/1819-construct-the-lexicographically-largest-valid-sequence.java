class Solution {
    int n, L;
    int[] res;
    boolean[] used; 

    public int[] constructDistancedSequence(int n) {
        this.n = n;
        L = 2 * n - 1;
        res = new int[L];
        used = new boolean[n + 1];
        backtrack(0);
        return res;
    }
    
    private boolean backtrack(int pos) {
        while (pos < L && res[pos] != 0) {
            pos++;
        }
        if (pos == L) {
            return true;
        }
        
        for (int num = n; num >= 1; num--) {
            if (used[num]) continue;
            if (num == 1) {
                res[pos] = 1;
                used[1] = true;
                if (backtrack(pos + 1)) return true;
                res[pos] = 0;
                used[1] = false;
            } else {
                int pos2 = pos + num;
                if (pos2 < L && res[pos] == 0 && res[pos2] == 0) {
                    res[pos] = num;
                    res[pos2] = num;
                    used[num] = true;
                    if (backtrack(pos + 1)) return true;
                    res[pos] = 0;
                    res[pos2] = 0;
                    used[num] = false;
                }
            }
        }
        return false;
    }
}
