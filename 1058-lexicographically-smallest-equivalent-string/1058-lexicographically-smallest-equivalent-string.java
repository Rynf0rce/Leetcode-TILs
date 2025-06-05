class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // Union-Find ìë£êµ¬ì¡°ë¥¼ ì¬ì©íì¬ ë¬¸ì ê°ì ëë±ì± ê´ê³ë¥¼ ê´ë¦¬
        UnionFind uf = new UnionFind();
        
        // s1ê³¼ s2ì ëìëë ë¬¸ìë¤ì Union ì°ì°ì¼ë¡ ì°ê²°
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            uf.union(c1, c2);
        }
        
        // baseStrì ê° ë¬¸ìë¥¼ í´ë¹ ê·¸ë£¹ì ëí ë¬¸ìë¡ ë³í
        StringBuilder result = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            result.append(uf.find(c));
        }
        
        return result.toString();
    }
    
    // Union-Find ìë£êµ¬ì¡° êµ¬í
    class UnionFind {
        private char[] parent;
        
        public UnionFind() {
            // ê° ë¬¸ìì ì´ê¸° ë¶ëª¨ë¥¼ ìê¸° ìì ì¼ë¡ ì¤ì 
            parent = new char[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = (char)('a' + i);
            }
        }
        
        // Find ì°ì°: í´ë¹ ë¬¸ìê° ìí ê·¸ë£¹ì ëí ë¬¸ìë¥¼ ì°¾ì
        // ê²½ë¡ ìì¶(Path Compression)ì íµí´ í¨ì¨ì± í¥ì
        public char find(char c) {
            int index = c - 'a';
            if (parent[index] != c) {
                // ì¬ê·ì ì¼ë¡ ë£¨í¸ë¥¼ ì°¾ê³ , ê²½ë¡ìì ëª¨ë  ë¸ëë¥¼ ë£¨í¸ì ì§ì  ì°ê²°
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
        
        // Union ì°ì°: ë ë¬¸ìê° ìí ê·¸ë£¹ì í©ì¹¨
        // ì¬ì ìì¼ë¡ ë ìì ë¬¸ìë¥¼ ìë¡ì´ ëíë¡ ì í
        public void union(char c1, char c2) {
            char root1 = find(c1);
            char root2 = find(c2);
            
            if (root1 != root2) {
                // ì¬ì ìì¼ë¡ ë ìì ë¬¸ìë¥¼ ë¶ëª¨ë¡ ì¤ì 
                // ì´ë¥¼ íµí´ ê° ê·¸ë£¹ì ëíê° í­ì ì¬ì ìì¼ë¡ ê°ì¥ ìì ë¬¸ìê° ë¨
                if (root1 < root2) {
                    parent[root2 - 'a'] = root1;
                } else {
                    parent[root1 - 'a'] = root2;
                }
            }
        }
    }
}