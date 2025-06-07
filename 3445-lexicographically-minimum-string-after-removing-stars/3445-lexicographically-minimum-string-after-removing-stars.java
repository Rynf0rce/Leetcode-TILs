class Solution {
    public String clearStars(String s) {
        // ê° ë¬¸ì(a-z)ë³ë¡ ì¸ë±ì¤ë¥¼ ì ì¥íë ì¤íë¤
        Stack<Integer>[] stacks = new Stack[26];
        for (int i = 0; i < 26; i++) {
            stacks[i] = new Stack<>();
        }
        
        // ì ê±°ë ë¬¸ìë¤ì íìíë ë°°ì´
        boolean[] removed = new boolean[s.length()];
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '*') {
                // ê°ì¥ ìì ë¬¸ìë¶í° íì¸íì¬ ì ê±°
                for (int j = 0; j < 26; j++) {
                    if (!stacks[j].isEmpty()) {
                        // ê°ì¥ ìµê·¼ì ëíë í´ë¹ ë¬¸ìì ì¸ë±ì¤ë¥¼ ì ê±°
                        int indexToRemove = stacks[j].pop();
                        removed[indexToRemove] = true;
                        break;
                    }
                }
                // '*' ìì²´ë ì ê±° íì
                removed[i] = true;
            } else {
                // ì¼ë° ë¬¸ìë í´ë¹ ë¬¸ìì ì¤íì ì¸ë±ì¤ ì ì¥
                stacks[c - 'a'].push(i);
            }
        }
        
        // ì ê±°ëì§ ìì ë¬¸ìë¤ë¡ ê²°ê³¼ ë¬¸ìì´ êµ¬ì±
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!removed[i]) {
                result.append(s.charAt(i));
            }
        }
        
        return result.toString();
    }
}