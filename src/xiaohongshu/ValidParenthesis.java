package xiaohongshu;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by billjyc on 2016/10/30.
 */
public class ValidParenthesis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        boolean rst = isValid(str);
        if(rst) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static boolean isValid(String str) {
        Stack<Character> s = new Stack<>();
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '(') {
                s.push(c);
            } else if(c == ')') {
                if(s.isEmpty() || s.peek() != '(') {
                    return false;
                }
                if(s.peek() == '(') {
                    s.pop();
                }
            }
        }
        return s.isEmpty();
    }
}
