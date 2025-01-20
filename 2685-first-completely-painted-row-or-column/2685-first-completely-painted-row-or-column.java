import java.util.*;

class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int total = m * n;  // arr.length == total

        // 1) ìì¹ ë§¤í: pos[val] = (row, col)
        //    val ë²ì: 1..m*n
        //    ì¸ë±ì¤ë¥¼ val-1 ë¡ ëê±°ë, Map ì¬ì©
        int[][] pos = new int[total+1][2]; 
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int val = mat[r][c];
                pos[val][0] = r;
                pos[val][1] = c;
            }
        }

        // 2) ê° í/ì´ì íì¸í¸ ì¹´ì´í¸
        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        // 3) arr ìí
        for (int i = 0; i < total; i++) {
            int val = arr[i];
            int r = pos[val][0];
            int c = pos[val][1];

            rowCount[r]++;
            colCount[c]++;

            if (rowCount[r] == n) {
                // rë² íì´ ëª¨ë íì¸í¸ë¨
                return i;
            }
            if (colCount[c] == m) {
                // cë² ì´ì´ ëª¨ë íì¸í¸ë¨
                return i;
            }
        }

        // ì´ë¡ ì ì¬ê¸° ëë¬íì§ ìì(í­ì row ëë col íëë ìì±ë¨)
        return -1;
    }

    // ê°ë¨ íì¤í¸
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] arr1 = {1,3,4,2};
        int[][] mat1 = {
            {1,4},
            {2,3}
        };
        System.out.println(sol.firstCompleteIndex(arr1, mat1)); // ê¸°ëê°: 2

        int[] arr2 = {2,8,7,4,1,3,5,6,9};
        int[][] mat2 = {
            {3,2,5},
            {1,4,6},
            {8,7,9}
        };
        System.out.println(sol.firstCompleteIndex(arr2, mat2)); // ê¸°ëê°: 3
    }
}
