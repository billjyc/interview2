package qunar;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by billjyc on 2016/10/14.
 */
public class ContainSameChars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String s1 = sc.next();
            String s2 = sc.next();
            boolean res = containSameChars(s1, s2);
            if(res) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }

    private static boolean containSameChars(String s1, String s2) {
        if(s1.length() == 0 && s2.length() == 0) return true;
        if(s1.length() == 0 || s2.length() == 0) return false;
        if(s1.equals(s2)) {
            return true;
        }
        Set<Character> set = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for(char c : s1.toCharArray()) {
            set.add(c);
        }
        for(char c : s2.toCharArray()) {
            if(!set.contains(c)) {
                return false;
            } else {
                set2.add(c);
            }
        }
        return set.size() == set2.size();
    }
}
