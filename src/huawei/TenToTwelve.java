package huawei;

import java.util.Scanner;

/**
 * Created by billjyc on 2016/10/10.
 */
public class TenToTwelve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int n = sc.nextInt();
            String result = tenToTwelve(n);
            System.out.println(result);
        }
    }

    public static String tenToTwelve(int n) {
        if(n == 0) {
            return "0";
        }
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B"};
        StringBuilder sb = new StringBuilder();

        while(n != 0) {
            sb.append(nums[n % 12]);
            n /= 12;
        }
        return sb.reverse().toString();
    }
}
