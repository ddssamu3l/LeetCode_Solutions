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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> inorderIndexes = new HashMap<>();
        for(int i = 0; i<inorder.length; i++){
            inorderIndexes.put(inorder[i], i);
        }

        return solve(inorderIndexes, postorder, inorder, 0, postorder.length-1, 0, inorder.length-1);
    }

    private TreeNode solve(HashMap<Integer, Integer> inorderIndexes, int[] postorder, int[] inorder, int postorderStart, int postorderEnd, int inorderStart, int inorderEnd){
        if(postorderStart > postorderEnd || inorderStart > inorderEnd)
            return null;

        if(postorderStart == postorderEnd)
            return new TreeNode(postorder[postorderEnd]);
        
        int inorderMid = inorderIndexes.get(postorder[postorderEnd]);
        int leftSubtreeSize = inorderMid - inorderStart;

        TreeNode root = new TreeNode(postorder[postorderEnd]);
        root.left = solve(inorderIndexes, postorder, inorder, postorderStart, postorderStart+leftSubtreeSize-1, inorderStart, inorderMid-1);
        root.right =  solve(inorderIndexes, postorder, inorder, postorderStart+leftSubtreeSize, postorderEnd-1, inorderMid+1, inorderEnd);

        return root;
    }
}