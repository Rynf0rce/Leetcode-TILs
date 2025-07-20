class Solution {
    // Trie ë¸ë í´ëì¤
    class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        boolean isDeleted = false;
        String folderStructure = ""; // íì êµ¬ì¡°ì í´ì ë¬¸ìì´
    }
    
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        // íí¸ 1: Trieë¥¼ ì¬ì©í´ì í´ë êµ¬ì¡° êµ¬ì¶
        TrieNode root = new TrieNode();
        
        // ëª¨ë  ê²½ë¡ë¥¼ Trieì ì½ì
        for (List<String> path : paths) {
            TrieNode current = root;
            for (String folder : path) {
                current.children.putIfAbsent(folder, new TrieNode());
                current = current.children.get(folder);
            }
        }
        
        // íí¸ 2: í´ì±ì ì´ì©í´ì í´ë êµ¬ì¡° í´ìí
        Map<String, List<TrieNode>> structureMap = new HashMap<>();
        computeFolderStructure(root, structureMap);
        
        // ëì¼í êµ¬ì¡°ë¥¼ ê°ì§ í´ëë¤ì ì­ì  íì
        for (List<TrieNode> duplicates : structureMap.values()) {
            if (duplicates.size() > 1) {
                for (TrieNode node : duplicates) {
                    markAsDeleted(node);
                }
            }
        }
        
        // ìë ¥ì¼ë¡ ì£¼ì´ì§ ê²½ë¡ë¤ ì¤ ì­ì ëì§ ìì ê²ë¤ë§ ë°í
        List<List<String>> result = new ArrayList<>();
        for (List<String> path : paths) {
            if (!isPathDeleted(root, path)) {
                result.add(path);
            }
        }
        
        return result;
    }
    
    // DFSë¡ ê° ë¸ëì í´ë êµ¬ì¡°ë¥¼ ê³ì°íê³  í´ì ë§µì ì ì¥
    private String computeFolderStructure(TrieNode node, Map<String, List<TrieNode>> structureMap) {
        if (node.children.isEmpty()) {
            // ë¹ í´ë (ë¦¬í ë¸ë)
            node.folderStructure = "";
            return "";
        }
        
        // íì í´ëë¤ì ìíë²³ ìì¼ë¡ ì ë ¬í´ì êµ¬ì¡° ë¬¸ìì´ ìì±
        List<String> subfolders = new ArrayList<>();
        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            String folderName = entry.getKey();
            TrieNode childNode = entry.getValue();
            
            // ì¬ê·ì ì¼ë¡ íì êµ¬ì¡° ê³ì°
            String childStructure = computeFolderStructure(childNode, structureMap);
            
            // í´ëëªê³¼ íì êµ¬ì¡°ë¥¼ ì¡°í©
            subfolders.add(folderName + "(" + childStructure + ")");
        }
        
        // ì ë ¬í´ì êµ¬ì¡° ë¬¸ìì´ ìì±
        Collections.sort(subfolders);
        String structure = String.join(",", subfolders);
        node.folderStructure = structure;
        
        // non-emptyí êµ¬ì¡°ë§ ë§µì ì¶ê° (ë¹ í´ëë identical íì ìì ì ì¸)
        if (!structure.isEmpty()) {
            structureMap.putIfAbsent(structure, new ArrayList<>());
            structureMap.get(structure).add(node);
        }
        
        return structure;
    }
    
    // ë¸ëì ëª¨ë  íì ë¸ëë¤ì ì­ì  íì
    private void markAsDeleted(TrieNode node) {
        node.isDeleted = true;
        for (TrieNode child : node.children.values()) {
            markAsDeleted(child);
        }
    }
    
    // ì£¼ì´ì§ ê²½ë¡ê° ì­ì ëìëì§ íì¸
    private boolean isPathDeleted(TrieNode root, List<String> path) {
        TrieNode current = root;
        for (String folder : path) {
            current = current.children.get(folder);
            if (current == null || current.isDeleted) {
                return true;
            }
        }
        return false;
    }
}