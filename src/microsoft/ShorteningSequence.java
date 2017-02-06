package microsoft;

import java.util.Scanner;

/**
 * Created by billjyc on 2016/10/10.
 */
public class ShorteningSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int N = sc.nextInt();
            //int[] nums = new int[N];
            Node dummy = new Node(0);
            Node curr = dummy;
            for (int i = 0; i < N; i++) {
                curr.next = new Node(sc.nextInt());
                curr = curr.next;
            }


            while (dummy.next != null) {
                curr = dummy.next;
                Node pre = dummy;
                boolean hasPairs = false;
                while (curr != null && curr.next != null) {
                    Node next = curr.next;
                    Node temp = curr.next.next;
                    if (Math.abs(curr.val - next.val) % 2 == 1) {
                        pre.next = temp;
                        curr = temp;
                        hasPairs = true;
                    } else {
                        pre = curr;
                        curr = next;
                    }
                }
                if (!hasPairs) break;
            }

            curr = dummy.next;
            int result = 0;
            while (curr != null) {
                result++;
                curr = curr.next;
            }
            System.out.println(result);
        }
    }
}

class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}