public class Codec {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    // ì ì ìíë¡ ë¸ë ê°ì StringBuilderì ì¶ê°
    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("X,");
            return;
        }
        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }
    
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(tokens));
        return deserializeHelper(queue);
    }
    
    // íë¥¼ ì´ì©íì¬ ì ì ììëë¡ í¸ë¦¬ë¥¼ ì¬êµ¬ì±
    private TreeNode deserializeHelper(Queue<String> queue) {
        String token = queue.poll();
        if (token.equals("X")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        return node;
    }
}