import java.util.*;

class Solution {
    // A simple Union-Find (Disjoint Set) implementation
    static class UnionFind {
        int[] parent, rank;
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx != ry) {
                if (rank[rx] < rank[ry]) {
                    parent[rx] = ry;
                } else if (rank[rx] > rank[ry]) {
                    parent[ry] = rx;
                } else {
                    parent[ry] = rx;
                    rank[rx]++;
                }
            }
        }
    }

    // We'll name our main method exactly as requested:
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        if (n <= 1) return nums;  // trivial case

        // 1) Pair each element as (value, original_index) and sort by value
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        Arrays.sort(arr, (a,b) -> Integer.compare(a.val, b.val));

        // 2) Use a Union-Find to group indices that can be swapped (directly or indirectly)
        UnionFind uf = new UnionFind(n);

        // Only adjacent elements in the sorted array can differ by <= limit
        for (int i = 0; i < n - 1; i++) {
            // If consecutive sorted elements differ by <= limit, union them
            if (arr[i+1].val - arr[i].val <= limit) {
                uf.union(arr[i].idx, arr[i+1].idx);
            }
        }

        // 3) Gather each Union-Find component -> we can reorder those elements freely
        Map<Integer, List<Integer>> components = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            components.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        // 4) For each component, sort its indices, collect/ sort their values, then place them back
        for (List<Integer> compIndices : components.values()) {
            if (compIndices.size() > 1) {
                // gather values from these indices
                List<Integer> compVals = new ArrayList<>();
                for (int idx : compIndices) {
                    compVals.add(nums[idx]);
                }
                // sort indices ascending
                Collections.sort(compIndices);
                // sort values ascending
                Collections.sort(compVals);

                // place sorted values back into sorted indices
                for (int i = 0; i < compIndices.size(); i++) {
                    nums[compIndices.get(i)] = compVals.get(i);
                }
            }
        }

        return nums;
    }

    // Helper class to store (value, originalIndex)
    static class Pair {
        int val;
        int idx;
        Pair(int v, int i) {
            val = v;
            idx = i;
        }
    }
}
