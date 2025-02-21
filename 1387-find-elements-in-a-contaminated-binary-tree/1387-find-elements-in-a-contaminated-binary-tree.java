import java.util.HashSet;
import java.util.Set;

class FindElements {
    private Set<Integer> recoveredValues;
    
    public FindElements(TreeNode root) {
        recoveredValues = new HashSet<>();
        if (root != null) {
            root.val = 0; // ë£¨í¸ ë³µêµ¬
            dfs(root);
        }
    }
    
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        recoveredValues.add(node.val);
        if (node.left != null) {
            node.left.val = 2 * node.val + 1;
            dfs(node.left);
        }
        if (node.right != null) {
            node.right.val = 2 * node.val + 2;
            dfs(node.right);
        }
    }
    
    public boolean find(int target) {
        return recoveredValues.contains(target);
    }
}
