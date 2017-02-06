package yahoo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by billjyc on 2016/10/22.
 */
public class CirculateNum {
    public String circulateNum(int a, int b) {
        List<Integer> remainders = new ArrayList<>();
        List<Integer> quotients = new ArrayList<>();

        if(a % b == 0) {
            return String.valueOf(a / b);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a / b).append(".");

        int remainder = a % b;
        remainders.add(remainder);
        int denominator = b;

        while(true) {
            if(remainder < denominator) {
                remainder *= 10;
            }
            quotients.add(remainder / denominator);
            remainders.add(remainder % denominator);
            remainder = remainder % denominator;

            int idx = check(remainder, remainders);
            if(idx != -1) {
                for(int j = 0; j < idx; j++) {
                    sb.append(quotients.get(j));
                }
                sb.append("{");
                for(int j = idx; j < quotients.size(); j++) {
                    sb.append(quotients.get(j));
                }
                sb.append("}");
                break;
            }
        }

        return sb.toString();
    }

    public int check(int remainder, List<Integer> remainders) {
        for(int i = 0; i < remainders.size() - 1; i++) {
            if(remainders.get(i) == remainder) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CirculateNum cn = new CirculateNum();
        System.out.println(cn.circulateNum(10, 3));
        System.out.println(cn.circulateNum(5, 3));
        System.out.println(cn.circulateNum(21, 13));
        System.out.println(cn.circulateNum(9, 3));
    }
}
