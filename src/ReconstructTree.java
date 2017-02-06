/**
 * Created by billjyc on 2016/9/25.
 */
public class ReconstructTree {
    public TreeNode constrcuctTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 ||
                preorder.length != inorder.length) {
            return null;
        }
        TreeNode root = helper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return root;
    }

    private TreeNode helper(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = search(root.val, inorder, inStart, inEnd);
        int leftSize = rootIndex - inStart;

        root.left = helper(preorder, inorder, preStart + 1, preStart + leftSize, inStart, rootIndex - 1);
        root.right = helper(preorder, inorder, preStart + leftSize + 1, preEnd, rootIndex + 1, inEnd);
        return root;
    }

    private int search(int rootVal, int[] inorder, int start, int end) {
        for(int i = start; i <= end; i++) {
            if(inorder[i] == rootVal) {
                return i;
            }
        }
        return -1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
