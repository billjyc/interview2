package amazon;

import java.util.ArrayList;

/**
 * Created by billjyc on 2017/1/18.
 */
public class CompanyTree {
    private static double resAve = Double.MIN_VALUE;
    private static Node result;
    public Node getMaxAvgSubTree(Node root) {
        if(root == null) return null;
        helper(root);
        return result;
    }

    private ResultType helper(Node root) {
        if(root == null) {
            return new ResultType(0, 0);
        }
        if(root.children == null || root.children.isEmpty()) {
            return new ResultType(root.val, 1);
        }
        int nn = 1;
        int sum = root.val;
        //ResultType max = null;
        for(Node child : root.children) {
            ResultType type = helper(child);
            /*if(type.node != null && (max == null || type.sum * 1.0 / type.numOfNodes > max.sum * 1.0 / max.numOfNodes)) {
                max = type;
            }*/
            nn += (type.numOfNodes);
            sum += (type.sum);
        }
        double res = sum * 1.0 / nn;
        if(res > resAve) {
            resAve = res;
            result = root;
        }
        return new ResultType(sum, nn);
    }

    public static void main(String[] args) {
        CompanyTree mas = new CompanyTree();

		/*
		 * case 1:
		 *       5
		 *     /   \
		 *   3       3
 		 *  / \     /  \
		 * 1   1   1    1
		 * return is 5
		 */
		result = null;
		resAve = Double.MIN_VALUE;
        Node head1 = new Node(5);
        head1.children.add(new Node(3));
        head1.children.add(new Node(3));
        head1.children.get(0).children.add(new Node(1));
        head1.children.get(0).children.add(new Node(1));
        head1.children.get(1).children.add(new Node(1));
        head1.children.get(1).children.add(new Node(1));
        Node check = mas.getMaxAvgSubTree(head1);
        if(check == head1) {
            System.out.println("test case 1 correct!");
        } else {
            System.out.println("test case 1 fail!");
        }

		/*
		 * case 2:
		 *       3
		 *     /   \
		 *   17      8
		 * return is 3
		 */
        result = null;
        resAve = Double.MIN_VALUE;
        Node head2 = new Node(3);
        head2.children.add(new Node(17));
        head2.children.add(new Node(8));
        check = mas.getMaxAvgSubTree(head2);
        if(check == head2) {
            System.out.println("test case 2 correct!");
        } else {
            System.out.println("test case 2 fail!");
        }

		/*
		 * case 3:
		 * null
		 * return is null
		 */
        result = null;
        resAve = Double.MIN_VALUE;
        Node head3 = null;
        check = mas.getMaxAvgSubTree(head3);
        if(check == null) {
            System.out.println("test case 3 correct!");
        } else {
            System.out.println("test case 3 fail!");
        }

		/*
		 * case 4:
		 * 1
		 * return is null
		 */
        result = null;
        resAve = Double.MIN_VALUE;
        Node head4 = new Node(1);
        check = mas.getMaxAvgSubTree(head4);
        if(check == null) {
            System.out.println("test case 4 correct!");
        } else {
            System.out.println("test case 4 fail!");
        }

		/*
		 * case 5:
		 * 1
		 * | \
		 * 0  null
		 * return is node 1
		 */
        result = null;
        resAve = Double.MIN_VALUE;
        Node head5 = new Node(1);
        head5.children.add(new Node(1));
        head5.children.add(null);
        check = mas.getMaxAvgSubTree(head5);
        if(check == head5) {
            System.out.println("test case 5 correct!");
        } else {
            System.out.println("test case 5 fail!");
        }

		/*
		 * case 6:
		 *   1
		 *  / \
		 * 2   3
		 * |
		 * 4
		 * return is node 2 in 2nd level, not root
		 */
        result = null;
        resAve = Double.MIN_VALUE;
        Node head6 = new Node(1);
        head6.children.add(new Node(2));
        head6.children.add(new Node(3));
        head6.children.get(0).children.add(new Node(4));
        check = mas.getMaxAvgSubTree(head6);
        if(check == head6.children.get(0)) {
            System.out.println("test case 6 correct!");
        } else {
            System.out.println("test case 6 fail!");
        }

		/*
		 * case 7:
		 *   1
		 *  / \
		 * 2   300
		 * |\
		 * 1 1
		 * return is node root
		 */
        result = null;
        resAve = Double.MIN_VALUE;
        Node head7 = new Node(1);
        head7.children.add(new Node(2));
        head7.children.add(new Node(300));
        head7.children.get(0).children.add(new Node(1));
        head7.children.get(0).children.add(new Node(1));
        check = mas.getMaxAvgSubTree(head7);
        if(check == head7) {
            System.out.println("test case 7 correct!");
        } else {
            System.out.println("test case 7 fail!");
        }

		/*
		 * case 8:
		 *   1
		 *  / \
		 * 200   3
		 * |\
		 * 1 1
		 * return is node 200
		 */
        result = null;
        resAve = Double.MIN_VALUE;
        Node head8 = new Node(1);
        head8.children.add(new Node(200));
        head8.children.add(new Node(3));
        head8.children.get(0).children.add(new Node(1));
        head8.children.get(0).children.add(new Node(1));
        check = mas.getMaxAvgSubTree(head8);
        if(check == head8.children.get(0)) {
            System.out.println("test case 8 correct!");
        } else {
            System.out.println("test case 8 fail!");
        }
    }
}

class ResultType {
    int sum;
    int numOfNodes;
    // isLeaf;
    //Node node;

    public ResultType(int sum, int numOfNodes) {
        this.sum = sum;
        this.numOfNodes = numOfNodes;
        //this.isLeaf = isLeaf;
        //this.node = node;
    }
}

class Node {
    int val;
    ArrayList<Node> children;

    public Node(int val) {
        this.val = val;
        children = new ArrayList<Node>();
    }
}