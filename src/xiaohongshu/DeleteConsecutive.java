package xiaohongshu;

import java.util.Scanner;

/**
 * Created by billjyc on 2016/10/30.
 */
public class DeleteConsecutive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String result = deleteConsecutive(str, "abc");

        System.out.println(result);
    }

    public static String deleteConsecutive(String str, String sign) {
        if(str == null || str.length() < sign.length()) {
            return str == null ? "" : str;
        }
        StringBuilder rst;
        int start;
        while(true) {
            boolean changed = false;
            start = 0;
            rst = new StringBuilder();
            while(start < str.length()) {
                int idx = str.indexOf(sign, start);
                if(idx != -1) {
                    rst.append(str.substring(start, idx));
                    start = idx + sign.length();
                    changed = true;
                } else {
                    break;
                }
            }
            if(start < str.length()) {
                rst.append(str.substring(start));
            }
            if(!changed) break;
            str = rst.toString();
        }


        return rst.toString();
    }
}
