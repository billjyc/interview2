package thoughtworks.test;

import org.junit.*;
import static org.junit.Assert.*;
import thoughtworks.BadmintonCourtReservation;

/**
 * Created by billjyc on 2016/10/17.
 */
public class BadmintonCourtReservationTest {
    BadmintonCourtReservation bcr;
    @Before
    public void setUp() {
        bcr = new BadmintonCourtReservation();
    }

    @Test
    public void generateSummary1() throws Exception {
        //Monday to Friday, not cross the time segment
        String input1 = "2016-06-02 20:00~22:00 7";
        String result = bcr.generateSummary(input1);

        String expected = "[Summary]\n" +
                "\n" +
                "2016-06-02 20:00~22:00 +210 -240 -30\n" +
                "\n" +
                "Total Income: 210\n" +
                "Total Payment: 240\n" +
                "Profit: -30";
        assertEquals(expected, result);
    }

    @Test
    public void generateSummary2() throws Exception {
        //Saturday and Sunday, not cross the time segment
        String input2 = "2016-10-15 9:00~11:00 7\n" +
                "2016-10-15 12:00~15:00 14";
        String result = bcr.generateSummary(input2);

        String expected = "[Summary]\n" +
                "\n" +
                "2016-10-15 9:00~11:00 +210 -160 +50\n" +
                "2016-10-15 12:00~15:00 +420 -300 +120\n" +
                "\n" +
                "Total Income: 630\n" +
                "Total Payment: 460\n" +
                "Profit: 170";
        assertEquals(expected, result);
    }

    @Test
    public void generateSummary3() throws Exception {
        ///crossing the time segment
        String input3 = "2016-10-14 11:00~14:00 4\n" +
                "2016-10-15 13:00~16:00 16";
        String result = bcr.generateSummary(input3);

        String expected = "[Summary]\n" +
                "\n" +
                "2016-10-14 11:00~14:00 +120 -130 -10\n" +
                "2016-10-15 13:00~16:00 +480 -450 +30\n" +
                "\n" +
                "Total Income: 600\n" +
                "Total Payment: 580\n" +
                "Profit: 20";
        assertEquals(expected, result);
    }

    @Test
    public void generateSummary4() throws Exception {
        String input4 = "2016-06-02 20:00~22:00 7\n" +
                "2016-06-03 09:00~12:00 14\n" +
                "2016-06-04 14:00~17:00 22\n" +
                "2016-06-05 19:00~22:00 3\n" +
                "2016-06-06 12:00~15:00 15\n" +
                "2016-06-07 15:00~17:00 12\n" +
                "2016-06-08 10:00~13:00 19\n" +
                "2016-06-09 16:00~18:00 16\n" +
                "2016-06-10 20:00~22:00 5\n" +
                "2016-06-11 13:00~15:00 11";
        String result = bcr.generateSummary(input4);

        String expected = "[Summary]\n" +
                "\n" +
                "2016-06-02 20:00~22:00 +210 -240 -30\n" +
                "2016-06-03 09:00~12:00 +420 -180 +240\n" +
                "2016-06-04 14:00~17:00 +660 -600 +60\n" +
                "2016-06-05 19:00~22:00 +0 -0 0\n" +
                "2016-06-06 12:00~15:00 +450 -300 +150\n" +
                "2016-06-07 15:00~17:00 +360 -200 +160\n" +
                "2016-06-08 10:00~13:00 +570 -330 +240\n" +
                "2016-06-09 16:00~18:00 +480 -300 +180\n" +
                "2016-06-10 20:00~22:00 +150 -120 +30\n" +
                "2016-06-11 13:00~15:00 +330 -200 +130\n" +
                "\n" +
                "Total Income: 3630\n" +
                "Total Payment: 2470\n" +
                "Profit: 1160";
        assertEquals(expected, result);
    }
}