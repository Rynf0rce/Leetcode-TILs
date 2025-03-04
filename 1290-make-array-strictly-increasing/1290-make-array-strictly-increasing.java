import java.util.*;

class Solution {
    int[] arr1, sortedArr2;
    int n;
    Map<String, Integer> memo;
    final int INF = 2000;
    
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        n = arr1.length;
        Arrays.sort(arr2);
        int m = arr2.length;
        List<Integer> list = new ArrayList<>();
        list.add(arr2[0]);
        for (int i = 1; i < m; i++) {
            if (arr2[i] != arr2[i - 1]) {
                list.add(arr2[i]);
            }
        }
        sortedArr2 = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sortedArr2[i] = list.get(i);
        }
        
        memo = new HashMap<>();
        int res = helper(0, -1);
        return res >= INF ? -1 : res;
    }
    
    private int helper(int i, int prev) {
        if (i == n) return 0;
        String key = i + "," + prev;
        if (memo.containsKey(key)) return memo.get(key);
        
        int ans = INF;
        if (arr1[i] > prev) {
            ans = helper(i + 1, arr1[i]);
        }
        
        int idx = Arrays.binarySearch(sortedArr2, prev + 1);
        if (idx < 0) {
            idx = -idx - 1;
        }
        if (idx < sortedArr2.length) {
            ans = Math.min(ans, 1 + helper(i + 1, sortedArr2[idx]));
        }
        
        memo.put(key, ans);
        return ans;
    }
}
