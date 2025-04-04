/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private class Result {
        TreeNode node;
        int depth; 
        
        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return helper(root).node;
    }
    
    private Result helper(TreeNode node) {
        if (node == null) {
            return new Result(null, 0);
        }
        
        Result left = helper(node.left);
        Result right = helper(node.right);
        
        if (left.depth == right.depth) {
            return new Result(node, left.depth + 1);
        }
        else if (left.depth > right.depth) {
            return new Result(left.node, left.depth + 1);
        }
        else {
            return new Result(right.node, right.depth + 1);
        }
    }
}