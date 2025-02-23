class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return buildTree(preorder, 0, preorder.length - 1, 
                         postorder, 0, postorder.length - 1);
    }
    
    private TreeNode buildTree(int[] pre, int preStart, int preEnd,
                               int[] post, int postStart, int postEnd) {
        if (preStart > preEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == preEnd) return root;
        
        int leftVal = pre[preStart + 1];
        int index = postStart;
        while (post[index] != leftVal) {
            index++;
        }
        int leftSize = index - postStart + 1;
        
        root.left = buildTree(pre, preStart + 1, preStart + leftSize, 
                              post, postStart, index);
        root.right = buildTree(pre, preStart + leftSize + 1, preEnd, 
                               post, index + 1, postEnd - 1);
        return root;
    }
}
