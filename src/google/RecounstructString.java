package google;

/**
 * Created by billjyc on 2016/10/21.
 */
public class RecounstructString {
    /**
     * given a string S and an int K, add a "_" every k characters from the end
     * @param s
     * @return
     */
    public String recounstructString(String s, int k) {
        if(s == null || s.length() == 0) {
            return "";
        }
        if(s.length() < k || k == 0) {
            return s;
        }
        int num = s.length() / k;
        int ptr = 0;
        StringBuilder sb = new StringBuilder(s).reverse();
        StringBuilder res = new StringBuilder();
        while(num > 0) {
            res.append(sb.substring(ptr, ptr + k)).append("_");
            ptr += k;
            num--;
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        RecounstructString rs = new RecounstructString();
        System.out.println(rs.recounstructString("abcdesfdsa", 2));
        System.out.println(rs.recounstructString("abc", 3));
        System.out.println(rs.recounstructString("ba", 5));
    }
}
