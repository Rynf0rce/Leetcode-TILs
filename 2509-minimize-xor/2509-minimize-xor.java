class Solution {
    public int minimizeXor(int num1, int num2) {
        int count1 = Integer.bitCount(num1);  // num1ì 1ë¹í¸ ê°ì
        int k = Integer.bitCount(num2);       // num2ì 1ë¹í¸ ê°ì
        
        // 1ë¹í¸ ê°ìê° ê°ì¼ë©´ ê·¸ë¥ num1 ë¦¬í´
        if (count1 == k) {
            return num1;
        }

        // num1ì ì´ì§ ííì ì´í´ë³´ë©°
        // - setBits   : num1ìì 1ì¸ ë¹í¸ë¤ì ìì¹(LSB=0, ì¤ë¦ì°¨ì)
        // - unsetBits : num1ìì 0ì¸ ë¹í¸ë¤ì ìì¹(LSB=0, ì¤ë¦ì°¨ì)
        // ìµë 31~32ë¹í¸ê¹ì§ë§ ì²´í¬ (ë¬¸ì ìì 10^9ê¹ì§ ê°ë¥)
        List<Integer> setBits = new ArrayList<>();
        List<Integer> unsetBits = new ArrayList<>();
        for (int pos = 0; pos < 31; pos++) {
            if (((num1 >> pos) & 1) == 1) {
                setBits.add(pos);
            } else {
                unsetBits.add(pos);
            }
        }

        // xë¥¼ ë§ë¤ê¸° ìí´, ììì num1 ê·¸ëë¡
        int x = num1;

        if (count1 < k) {
            // 1ë¹í¸ê° ë¶ì¡± -> unsetBitsìì ë®ì ìì¹ë¶í° 1ë¡ ì¼¬
            int diff = k - count1;
            // íìí ë§í¼ë§ ì¼ì¤ë¤
            for (int pos : unsetBits) {
                if (diff == 0) break;
                // í´ë¹ posê° ì´ë¯¸ 1ì´ë©´ ëì´ê°ì§ë§, unsetBitsë¼ ì´ì°¨í¼ 0
                x |= (1 << pos);
                diff--;
            }
        } else {
            // count1 > k
            // 1ë¹í¸ê° ì´ê³¼ -> setBitsìì ë®ì ìì¹ë¶í° 1ì ë
            int diff = count1 - k;
            for (int pos : setBits) {
                if (diff == 0) break;
                // í´ë¹ posê° 1ì´ë©´ ë
                x &= ~(1 << pos);
                diff--;
            }
        }

        return x;
    }
}
