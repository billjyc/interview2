package thoughtworks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by billjyc on 2016/10/15.
 * 收费标准：
 * 1. 周一到周五：
     9:00 ~ 12:00 30元/时
     12:00 ~ 18:00 50元/时
     18:00 ~ 20:00 80元/时
     20:00 ~ 22:00 60元/时
   2. 周六及周日
     9:00 ~ 12:00 40元/时
     12:00 ~ 18:00 50元/时
     18:00 ~ 22:00 60元/时

    人数-订场：
    0-3 - 0
    4-5 - 1
    6-11 - 2
    12-15 - 2
    16-17 - 3
    18-21 - 3
    22-23 - 4
    24-   - x / 6

    constraints: 每次活动时间为2小时或3小时
    参加活动的人每人付给小明30元
 */
public class BadmintonCourtReservation {
    public static void main(String[] args) {
        String input1 = "2016-06-02 20:00~22:00 7\n" +
                "2016-06-03 09:00~12:00 14\n" +
                "2016-06-04 14:00~17:00 22\n" +
                "2016-06-05 19:00~22:00 3\n" +
                "2016-06-06 12:00~15:00 15\n" +
                "2016-06-07 15:00~17:00 12\n" +
                "2016-06-08 10:00~13:00 19\n" +
                "2016-06-09 16:00~18:00 16\n" +
                "2016-06-10 20:00~22:00 5\n" +
                "2016-06-11 13:00~15:00 11";
        //Monday to Friday, not cross the time segment
        String input2 = "2016-06-02 20:00~22:00 7";
        //Saturday and Sunday, not cross the time segment
        String input3 = "2016-10-15 9:00~11:00 7\n" +
                "2016-10-15 12:00~15:00 14";
        //crossing the time segment
        String input4 = "2016-10-14 11:00~14:00 4\n" +
                "2016-10-15 13:00~16:00 16";
        BadmintonCourtReservation bcr = new BadmintonCourtReservation();
        System.out.println(bcr.generateSummary(input1));
        System.out.println(bcr.generateSummary(input3));
    }

    Map<DayOfWeek, Map<Integer, Integer>> fees = new HashMap<>();
    public static final int SINGLE_PAYMENT = 30;

    public BadmintonCourtReservation() {
        Map<Integer, Integer> weekDayFee = new HashMap<>();
        for(int i = 9; i < 12; i++) {
            weekDayFee.put(i, 30);
        }
        for(int i = 12; i < 18; i++) {
            weekDayFee.put(i, 50);
        }
        for(int i = 18; i < 20; i++) {
            weekDayFee.put(i, 80);
        }
        for(int i = 20; i < 22; i++) {
            weekDayFee.put(i, 60);
        }

        Map<Integer, Integer> weekendFee = new HashMap<>();
        for(int i = 9; i < 12; i++) {
            weekendFee.put(i, 40);
        }
        for(int i = 12; i < 18; i++) {
            weekendFee.put(i, 50);
        }
        for(int i = 18; i < 22; i++ ) {
            weekendFee.put(i, 60);
        }

        for(int i = DayOfWeek.MONDAY.ordinal(); i <= DayOfWeek.FRIDAY.ordinal(); i++) {
            fees.put(DayOfWeek.getDay(i), weekDayFee);
        }
        fees.put(DayOfWeek.SATURDAY, weekendFee);
        fees.put(DayOfWeek.SUNDAY, weekendFee);
    }

    public String generateSummary(String input) {
        StringBuilder res = new StringBuilder();
        res.append("[Summary]").append("\n\n");

        String[] lines = input.split("\n");
        int totalPayment = 0;
        int totalIncome = 0;
        for(String line : lines) {
            String[] data = line.split(" ");
            String dateS = data[0];
            String timeS = data[1];
            String peopleS = data[2];

            DayOfWeek dayOfWeek = getDayOfWeek(dateS);
            String[] hours = timeS.split("~");
            int startHour = getHour(hours[0]);
            int endHour = getHour(hours[1]);
            int people = Integer.valueOf(peopleS);

            int numOfCourt = numOfCourt(people);

            int payment = 0;
            int income = 0;
            int profit = 0;
            if(numOfCourt > 0) {
                payment = computePayment(dayOfWeek, startHour, endHour) * numOfCourt;
                income = SINGLE_PAYMENT * people;
                profit = income - payment;

                totalIncome += income;
                totalPayment += payment;

            }
            StringBuilder sb = new StringBuilder();
            sb.append(dateS + " " + timeS).append(" ").append("+").append(income)
                    .append(" -").append(payment).append(" ");
            if(profit > 0) {
                sb.append("+");
            }
            sb.append(profit);
            res.append(sb);
            res.append("\n");
        }
        res.append("\n")
                .append("Total Income: " + totalIncome + "\n")
                .append("Total Payment: " + totalPayment + "\n")
                .append("Profit: " + (totalIncome - totalPayment));
        return res.toString();
    }

    private int getHour(String hourS) {
        int index = hourS.indexOf(":");
        return Integer.parseInt(hourS.substring(0, index));
    }

    /**
     * 计算需要预定的球场数目
     * @param M
     * @return
     */
    private int numOfCourt(int M) {
        int T = M / 6;
        int X = M % 6;
        if(T == 0 && X < 4) {
            return 0;
        } else if(T == 0) {
            return 1;
        } else if(T == 1) {
            return 2;
        } else if((T == 2 || T == 3) && X < 4) {
            return T;
        } else if(T == 2 || T == 3) {
            return T + 1;
        } else {
            return T;
        }
    }

    /**
     * 计算单个球场在该时间段内的花费
     * @param startHour
     * @param endHour
     * @return
     */
    private int computePayment(DayOfWeek dayOfWeek, int startHour, int endHour) {
        int expense = 0;
        for(int i = startHour; i < endHour; i++) {
            expense += fees.get(dayOfWeek).get(i);
        }
        return expense;
    }

    /**
     * 根据所给的日期判断是星期几
     * @param dateS
     * @return
     */
    private DayOfWeek getDayOfWeek(String dateS) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //System.out.println(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return DayOfWeek.getDay(dayOfWeek - 1);
    }
}
