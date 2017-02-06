package huawei;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by billjyc on 2016/10/10.
 */
public class DepthOfBinaryTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String tree = sc.next();
            TreeNode root = helper(tree);
//            String result = helper(tree);
//            System.out.println(result);
        }
    }

    private static TreeNode helper(String tree) {
        Queue<TreeNode> q = new LinkedList<>();
        char[] chs = tree.toCharArray();
        int ptr = 0;
        TreeNode root = new TreeNode(chs[0]);
        q.offer(root);
        root.level = chs[1] - '0';
        //int level = chs[1] - '0';


        for(int i = 2; i < chs.length; i+=2) {
            TreeNode parent = q.poll();
            boolean leftEmpty = true;
            if(i + 1 < chs.length && chs[i + 1] - '0' == parent.level + 1) {
                TreeNode leftNode = new TreeNode(chs[i]);
                leftNode.level = chs[i + 1] - '0';
                q.offer(leftNode);
                parent.left = leftNode;
                leftEmpty = false;
                i += 2;
            }
            if(i + 1 < chs.length && chs[i + 1] - '0' == parent.level + 1) {
                TreeNode rightNode = new TreeNode(chs[i]);
                rightNode.level = chs[i + 1] - '0';
                q.offer(rightNode);
                parent.right = rightNode;
                leftEmpty = true;
            }

        }


        return root;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int level;
    char val;

    public TreeNode(char value) {
        this.val = value;
    }
}
