package google;

import javax.xml.transform.Result;

/**
 * Created by billjyc on 2016/10/21.
 */
public class MaxSubtreeSize {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    class ResultType {
        boolean isValid;
        int subTreeSize;

        public ResultType(boolean isValid, int subTreeSize) {
            this.isValid = isValid;
            this.subTreeSize = subTreeSize;
        }
    }

    /**
     * 求一个二叉查找树中，最大的subtree的size。subtree所有元素都必须在范围[A,B]之间
     * @param root
     * @param min
     * @param max
     * @return
     */
    public int maxSubTreeSize(TreeNode root, int min, int max) {
        if(root == null || min > max) {
            return 0;
        }
        ResultType res = helper(root, min, max);
        return res.subTreeSize;
    }

    public ResultType helper(TreeNode root, int min, int max) {
        if(root == null) {
            return new ResultType(true, 0);
        }
        if(root.left == null && root.right == null) {
            if(root.val >= min && root.val <= max) {
                return new ResultType(true, 1);
            } else {
                return new ResultType(false, 0);
            }
        }
        ResultType left = helper(root.left, min, max);
        ResultType right = helper(root.right, min, max);
        int maxSize = 0;
        if(left.isValid && right.isValid) {
            if(root.val <= max && root.val >= min) {
                return new ResultType(true, left.subTreeSize + right.subTreeSize + 1);
            } else {
                return new ResultType(false, Math.max(left.subTreeSize, right.subTreeSize));
            }

        } else {
            return new ResultType((root.val >= max && root.val <= min) ? true : false, Math.max(left.subTreeSize, right.subTreeSize));
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node3.left = node2;
        node3.right = node5;
        node2.left = node1;
        node5.left = node4;

        MaxSubtreeSize mss = new MaxSubtreeSize();

        System.out.println(mss.maxSubTreeSize(node3, 2, 5));
    }
}
