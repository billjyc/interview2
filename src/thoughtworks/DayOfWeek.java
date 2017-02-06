package thoughtworks;

/**
 * Created by billjyc on 2016/10/15.
 */
public enum DayOfWeek {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    public static DayOfWeek getDay(int index) {
        DayOfWeek result;
        switch (index) {
            case 0:
                result = SUNDAY;
                break;
            case 1:
                result = MONDAY;
                break;
            case 2:
                result = TUESDAY;
                break;
            case 3:
                result = WEDNESDAY;
                break;
            case 4:
                result = TUESDAY;
                break;
            case 5:
                result = FRIDAY;
                break;
            case 6:
                result = SATURDAY;
                break;
            default:
                result = SUNDAY;
                break;
        }
        return result;

    }
}
