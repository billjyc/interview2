package xiaohongshu;

import java.util.*;

/**
 * Created by billjyc on 2016/10/30.
 */
public class RotateLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        Node dummy = new Node(0);
        Node cur = dummy;
        for(int i = 0; i < m; i++) {
            cur.next = new Node(sc.nextInt());
            cur = cur.next;
        }
        RotateLinkedList rotateLinkedList = new RotateLinkedList();
        Node head = rotateLinkedList.rotateList(dummy.next, n);
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }


    public Node rotateList(Node head, int n) {
        if(head == null) {
            return head;
        }
        //get length of list
        Node cur = head;
        int len = 0;
        while(cur != null) {
            cur = cur.next;
            len++;
        }

        if(n == 0 || n >= len) return head;
        int midIdx = len / 2;
        int leftIdx = (len - n) / 2;
        int rightIdx = leftIdx + n - 1;
        //find tail node
        cur = head;
        while(cur.next != null) {
            cur = cur.next;
        }
        Node tail = cur;
        //find node before left Node
        Node left = head;
        for(int i = 0; i < leftIdx - 1; i++) {
            left = left.next;
        }
        Node leftNode = left.next;
        //find  right node
        Node right = head;
        for(int i = 0; i < rightIdx; i++) {
            right = right.next;
        }

        System.out.println("before left node: " + left.val);
        System.out.println("right node: " + right.val);

        Node dummy = new Node(0);
        dummy.next = head;

        Node rightHalf = right.next;
        if(rightHalf != null) {
            tail.next = left.next;
            left.next = null;
            right.next = dummy.next;
            dummy.next = rightHalf;
        } else {
            right.next = dummy.next;
            dummy.next = left.next;
            left.next = null;

        }

        return dummy.next;
    }
}

class Node {
    int val;
    Node next;

    Node(int x) {
        val = x;
    }
}
