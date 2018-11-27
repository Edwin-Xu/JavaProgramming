package DATE;

import java.util.Calendar;

public class Calender {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();

        System.out.println(cal.getTimeInMillis());
        System.out.println(cal.getTime());
        System.out.println(cal.getCalendarType());
    }
}
