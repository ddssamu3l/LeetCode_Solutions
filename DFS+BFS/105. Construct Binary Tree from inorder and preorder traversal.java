
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // key is the value inside of inorder[] array, value is that value's index in the array.
        HashMap<Integer, Integer> inInd = new HashMap<>(); 

        for(int i = 0; i<preorder.length; i++){
            inInd.put(inorder[i], i);
        }

        return dfs(inInd, preorder, inorder, 0, preorder.length, 0, inorder.length-1);
    }

    private TreeNode dfs(HashMap<Integer, Integer> inInd, int[] preorder, int[] inorder, int preorderStart, int preorderEnd, int inorderStart, int inorderEnd) {
        if (preorderStart >= preorder.length || preorderStart > preorderEnd || inorderStart > inorderEnd)
            return null;
            
        System.out.println();
        System.out.print("Currently at: " + preorder[preorderStart]);
        
        if (inorderStart == inorderEnd)
            return new TreeNode(preorder[preorderStart]);

        // Find the current root's position in the inorder array
        int inorderMid = inInd.get(preorder[preorderStart]);
        System.out.println(". The number " + preorder[preorderStart] + " is at inorder[" + inorderMid + "].");

        // Calculate sizes of left and right subtrees based on inorder indices.
        int leftSubtreeSize = inorderMid - inorderStart;
        int rightSubtreeSize = inorderEnd - inorderMid;

        // Debug: Print the indices for left and right subtree subarrays.
        System.out.println("Left subtree: preorder indices [" + (preorderStart + 1) + ", " + (preorderStart + leftSubtreeSize) +
                        "], inorder indices [" + inorderStart + ", " + (inorderMid - 1) + "]");
        System.out.println("Right subtree: preorder indices [" + (preorderStart + leftSubtreeSize + 1) + ", " + preorderEnd +
                        "], inorder indices [" + (inorderMid + 1) + ", " + inorderEnd + "]");

        TreeNode currentRoot = new TreeNode(preorder[preorderStart]);
        currentRoot.left = dfs(inInd, preorder, inorder,
                            preorderStart + 1, preorderStart + leftSubtreeSize,
                            inorderStart, inorderMid - 1);
        currentRoot.right = dfs(inInd, preorder, inorder,
                                preorderStart + leftSubtreeSize + 1, preorderEnd,
                                inorderMid + 1, inorderEnd);
        
        return currentRoot;
    }

}