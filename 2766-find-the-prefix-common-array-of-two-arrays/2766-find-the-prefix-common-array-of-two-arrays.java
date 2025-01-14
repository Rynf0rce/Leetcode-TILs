class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];

        // ê° ì¸ë±ì¤ iê¹ì§ A, Bì ë±ì¥í ììë¤ì ë´ì Set
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        for(int i = 0; i < n; i++){
            // ië²ì§¸ ìì ì¶ê°
            setA.add(A[i]);
            setB.add(B[i]);

            // êµì§í© ê°ì êµ¬íê¸°
            // (setAë¥¼ ê¸°ì¤ì¼ë¡ ëë©´ì setBì í¬í¨ë¼ ìë ì§ íì¸í´ë ëê³ ,
            //  êµì§í©ì ì§ì  ë§ë¤ì´ë ë¨)
            int commonCount = 0;
            for(int num : setA) {
                if(setB.contains(num)) {
                    commonCount++;
                }
            }

            result[i] = commonCount;
        }

        return result;
    }
}
