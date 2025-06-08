class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        int cur = 1;
        
        for (int i = 0; i < n; i++) {
            result.add(cur);
            
            if (cur * 10 <= n) {
                cur *= 10;
            } else {
                if (cur >= n) {
                    cur /= 10;
                }
                
                cur++;
                
                while (cur % 10 == 0) {
                    cur /= 10;
                }
            }
        }
        
        return result;
    }
}