package huawei;

import java.util.Scanner;

/**
 * Created by billjyc on 2016/10/10.
 */
public class NumOfDuplicateCharacters {
    //tc1: ""
    //tc2: "a"
    //tc3" "aaaa"
    //tc4: "abbbccaa"
    //tc5: "2323$$$"
    //tc6: "ABBC67%%%CCCAA99"
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String str = sc.next();

            String result = numOfDuplicateChars(str);
            System.out.println(result);
        }
    }

    private static boolean isCharacter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static String numOfDuplicateChars(String str) {
        if(str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int left = 0, right = 0;
        char[] chs = str.toCharArray();
        while(right < chs.length) {
            while(left < chs.length && !isCharacter(chs[left])) {
                left++;
            }
            right = left;
            if(left >= chs.length) {
                break;
            }
            int dup = 0;
            while(right < chs.length && (chs[right] == chs[left] || !isCharacter(chs[right]))) {

                if(right < chs.length && chs[right] == chs[left]) {
                    dup++;
                }
                right++;
            }

            sb.append(chs[left]).append(dup);
            left = right;
        }
        return sb.toString();
    }
}
