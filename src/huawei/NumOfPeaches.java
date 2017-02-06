package huawei;

import java.util.Scanner;

/**
 * Created by billjyc on 2016/10/10.
 */
public class NumOfPeaches {
    public static void main(String[] args) {
        NumOfPeaches np = new NumOfPeaches();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int m = sc.nextInt();
            int result = np.numOfPeaches(m);
            System.out.println(result);
        }
    }

    public int numOfPeaches(int m) {
        return (int)Math.pow(m, m) - (m - 1);
    }
}
