/**
 * Created by billjyc on 2016/9/25.
 * Given a string, encode it to shorten the length
 * e.g. "aaabb"->"a3b2", "abc"->"a1b1c1"
 */
public class EncodeString {
    //test case 1 : ""
    //test case 2: "abc"
    //test case 3: "aaabbcc"
    //test case 4: "aabccaa"
    public String encodeString(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        int left = 0, right = 0;
        char[] chs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        while(right < chs.length) {
            while(right < chs.length && chs[right] == chs[left]) {
                right++;
            }
            sb.append(chs[left]).append(right - left);
            left = right;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EncodeString es = new EncodeString();
        String s1 = "";
        String s2 = "abc";
        String s3 = "aaabbcc";
        String s4 = "aabccaa";

        System.out.println(es.encodeString(s1));
        System.out.println(es.encodeString(s2));
        System.out.println(es.encodeString(s3));
        System.out.println(es.encodeString(s4));
    }
}
