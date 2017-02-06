package qunar;

import java.util.Scanner;

/**
 * Created by billjyc on 2016/10/14.
 */
public class NextSymmetricNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int n = sc.nextInt();
            long result;
            if(isAll9s(n)) {
                result = n + 2;
            } else {
                result = nextSymmetricNumber(n);
            }
            System.out.println(result);
        }
    }

    private static long nextSymmetricNumber(int m) {
        //we take the mirror of the left side
        String s = String.valueOf(m);
        char[] c = s.toCharArray();
        int[] num = new int[c.length];
        for(int i = 0; i < num.length; i++) {
            num[i] = c[i] - '0';
        }
        int left, right;

        int n = num.length;
        int mid = n / 2;
        boolean leftSmaller = false;

        left = mid - 1;
        right = (n % 2 == 1) ? mid + 1 : mid;

        while(left >= 0 && num[left] == num[right]) {
            left--;
            right++;
        }
        if(left < 0 || num[left] < num[right]) {
            leftSmaller = true;
        }
        //copy mirror of left to right
        while(left >= 0) {
            num[right] = num[left];
            left--;
            right++;
        }
        //Handle the case where middle digits must be increased
        if(leftSmaller) {
            int carry = 1;
            left = mid - 1;
            // If there are odd digits, then increment
            // the middle digit and store the carry
            if(n% 2 == 1) {
                num[mid] += carry;
                carry = num[mid] / 10;
                num[mid] = num[mid] % 10;
                right = mid + 1;
            } else {
                right = mid;
            }

            while(left >= 0) {
                num[left] += carry;
                carry = num[left] / 10;
                num[left] %= 10;
                num[right++] = num[left--];
            }
        }
        long res = 0;
        for(int i = 0; i < num.length; i++) {
            res = res * 10 + num[i];
        }
        return res;
    }

    private static boolean isAll9s(int m) {
        boolean res = true;
        while(m > 0) {
            if(m % 10 != 9) {
                return false;
            }
            m /= 10;
        }
        return res;
    }
}
