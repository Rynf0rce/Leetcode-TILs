import java.util.*;

class Solution {
    // gcd ê³ì°
    private long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    /**
     * fill(req, length):
     *  - req(ëëì´ì¼ í  ì ì)ë¥¼ 9..2 ììë¡ ëë ì ìë ë§í¼ ëëì´ digitì ansì ì¶ê°
     *  - ìë¦¿ì ë¶ì¡±ë¶ì '1'ë¡ ì±ìë£ì
     *  - ansë¥¼ ë¤ì§ì´(reverse) ë¬¸ìì´ë¡ ë§ë¤ì´ ë°í (ê°ì¥ ìì ìë¥¼ ë§ë¤ê¸° ìí ì²ë¦¬)
     */
    private String fill(long req, int length) {
        List<Integer> ans = new ArrayList<>();
        
        // 9ë¶í° 2ê¹ì§ í° ì«ìë¶í° ëëì´ë³¸ë¤
        for (int d = 9; d >= 2; d--) {
            while (req % d == 0) {
                ans.add(d);
                req /= d;
            }
        }
        // lengthë³´ë¤ ìì¼ë©´ '1'ì ì±ìë£ì
        int need = length - ans.size();
        for (int i = 0; i < need; i++) {
            ans.add(1);
        }
        
        // ë¤ì§ì´ì(ìì ìë¦¬ë¶í°) ë¬¸ìì´ë¡
        Collections.reverse(ans);  // ansë¥¼ reverse
        StringBuilder sb = new StringBuilder();
        for (int x : ans) {
            sb.append(x);
        }
        return sb.toString();
    }

    /**
     * smallestNumber(S, T):
     *   - ë¬¸ìì´ S (num), ì ì Të¥¼ ë°ì,
     *   - "S ì´ì"ì´ë©´ì "ìë¦¿ì ê³±ì´ Tì ë°°ì(= Të¡ ëëì´ë¨ì´ì§)"ì¸ zero-free ì ì¤ ê°ì¥ ìì ê²ì ì°¾ëë¤.
     *   - ì ê·¼ ë°©ìì Python ì½ëì ëì¼:
     *     1) Tê° 2,3,5,7 ì´ì¸ ììë¡ ëëì´ë¨ì´ì§ì§ ìì¼ë©´ -1
     *     2) P[i]: ììì iìë¦¬ë¡ Të¥¼ ì´ë ì ë ìì¸ì ìì§íëì§ ì¶ì 
     *     3) ë§¨ ëê¹ì§ ìì§íë©´(= P[-1]==1) => S ê·¸ëë¡ ë°í
     *     4) ìëë©´ ë¤ììë¶í° digitë¥¼ +1..9ê¹ì§ ì¬ë ¤ë³´ë©° ë¨ì ìì¸ì(fill) ìë
     *     5) ì ë¶ ì ëë©´ ê¸¸ì´ë¥¼ (S.length+1)ë¡ fill
     */
    public String smallestNumber(String S, long T) {
        // 1) Të¥¼ 2,3,5,7ë¡ë§ ë¶í´ ê°ë¥íì§ ì²´í¬
        long tmp = T;
        int[] primes = {2,3,5,7};
        for (int p : primes) {
            while (tmp % p == 0) {
                tmp /= p;
            }
        }
        if (tmp != 1) {
            // Tê° 2,3,5,7 ì´ì¸ ìì í¬í¨ => ê³±ì¼ë¡ ë§ë¤ ì ìì
            return "-1";
        }

        int N = S.length();
        
        // 2) P[i]: Sì ì iìë¦¬ë¡ Tìì ì´ë ì ë ìì¸ìë¥¼ ìì§íëì§ ì¶ì 
        //    ì¬ê¸°ìë gcd(P[i], digit)ë§í¼ ìì¸ìë¥¼ ìì§íë¤ê³  ë³´ê³ , P[i+1] = P[i] / gcd(P[i], digit)
        long[] P = new long[N+1];
        Arrays.fill(P, T);  // ì ë¶ Të¡ ì´ê¸°í
        
        // ìììë¶í° digitë¥¼ ì½ì¼ë©° gcd ìì¸ì ìì§
        for (int i = 0; i < N; i++) {
            int x = S.charAt(i) - '0';
            if (x == 0) {
                break; // zero-free ì¡°ê±´ ê¹¨ì§ -> ì¤ë¨
            }
            long g = gcd(P[i], x);
            P[i+1] = P[i] / g;
        }

        // ë§ì½ P[N] == 1ì´ë©´ => ììë¦¬ë§ì¼ë¡ ì´ë¯¸ Tì ìì¸ì ë¤ ìì§ => S ê·¸ëë¡ ëµ
        if (P[N] == 1) {
            return S;
        }

        // 3) zero ì¸ë±ì¤ ì°¾ê¸° (Sì '0'ì´ ìì¼ë©´ ë©ì¶ë ìì¹)
        //    (python ì½ë: zero = S.find('0') % N)
        //    ìë°ìì indexOf('0') => -1ì´ë©´ ìë¤. => ê·¸ë¼ zero=Nì¼ë¡ ì¡ì¼ë©´ ë¨
        int zero = S.indexOf('0');
        if (zero < 0) zero = N; // '0'ì´ ìë ê²½ì°

        // 4) ë¤ììë¶í° digit+1..9 ìë
        //    python: for i in range(zero, -1, -1):
        for (int i = zero; i >= 0; i--) {
            long req = P[i];
            int digits = (N-1) - i; 
            // digitì (S[i]+1 .. 9) ìë
            if (i < N) {
                int original = S.charAt(i) - '0';
                for (int d = original+1; d <= 9; d++) {
                    long g = gcd(req, d);
                    long newReq = req / g;
                    // fill(newReq, digits)
                    String ending = fill(newReq, digits);
                    // ending ê¸¸ì´ê° digits ì´íë¼ë©´ => ì±ê³µ
                    if (ending.length() <= digits) {
                        // ìë¶ë¶ = S[:i], ì¤ê° digit=d, ë¤=ending
                        return S.substring(0, i) + d + ending;
                    }
                }
            }
        }

        // 5) ëª¨ë ì ëë©´ ìë¦¿ì íë ëë ¤ì fill(T, N+1)
        return fill(T, N+1);
    }

    // -- ê°ë¨ íì¤í¸ --
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // ìì: num="12", t=22020096000
        // ìì¸ìë¶í´ => 2^23 * 3^1 * 5^3 * 7^1 => ëëµ 12ìë¦¬ íì
        // ì´ ë¡ì§ì length = N+1=3ê¹ì§ë§ ìëíë©´ ì¤í¨í´ì fill(...) => 
        // fill(...) 3ìë¦¬ë ë¹ì°í 2^23ë±ì ëª» ì»¤ë² => "111"? => ê³±ì´ 1 => T ëª»ë§ë¤ì´ì... 
        // -> ì¤ì ììë ë í° ê¸¸ì´ë¡ íì¥ íì
        // ê·¸ëë ì½ë ìì°
        System.out.println(sol.smallestNumber("12", 22020096000L));
    }
}
