package google;

/**
 * Created by billjyc on 2016/10/20.
 */
public class ReplaceTwoAdjacentDigits {
    /**
     * replace two adjacent digits with the larger one, return min
     * e.g. 233614->23364, 21324 ->2134, 232432
     * @param num
     * @return
     */
    public int replaceTwoAdjacentDigitsWithTheLargerOne(int num) {
        if(num < 10) {
            return num;
        }
        String s = String.valueOf(num);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < s.length() - 1; i++) {
            String tmp;
            if(s.charAt(i) < s.charAt(i + 1)) {
                tmp = s.substring(0, i) + s.substring(i + 1);
            } else {
                tmp = s.substring(0, i + 1);
                if(i < s.length() - 2)
                    tmp += s.substring(i + 2);
            }
            int m = Integer.parseInt(tmp);
            min = Math.min(m, min);
        }
        return min;
    }

    /**
     * replace two adjacent digits with the round up average
     * return max
     * e.g. 623315 -> 63315
     * @param num
     * @return
     */
    public int roundupAverage(int num) {
        if(num < 10) {
            return num;
        }
        String s = String.valueOf(num);
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < s.length() - 1; i++) {
            int d1 = s.charAt(i) - '0';
            int d2 = s.charAt(i + 1) - '0';
            int d = (int) Math.ceil((d1 + d2) * 1.0 / 2);

            String tmp = s.substring(0, i) + d;
            if(i < s.length() - 2) {
                tmp += s.substring(i + 2);
            }
            int m = Integer.parseInt(tmp);
            max = Math.max(m, max);
        }

        return max;
    }

    public int identicalAdjacent(int num) {
        if(num < 10) {
            return num;
        }
        String s = String.valueOf(num);
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < s.length() - 1; i++) {
            String tmp = "";
            if(s.charAt(i) == s.charAt(i + 1)) {
                tmp = s.substring(0, i) + s.substring(i + 1);
            }
            int m = Integer.parseInt(tmp);
            max = Math.max(m, max);
        }

        return max;
    }

    public static void main(String[] args) {
        ReplaceTwoAdjacentDigits rtd = new ReplaceTwoAdjacentDigits();
        System.out.println(rtd.replaceTwoAdjacentDigitsWithTheLargerOne(232432));
        System.out.println(rtd.roundupAverage(623315));
        System.out.println(rtd.roundupAverage(223336226));
    }
}
