package by.coolout.bot.filter;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.Calendar;

import static java.util.Calendar.*;

@Slf4j
public class WorkScheduleFilter {

    public static boolean isOpened() {
        int day = Calendar.getInstance().get(DAY_OF_WEEK);
        LocalTime now = LocalTime.now();

        LocalTime startSunday = LocalTime.of(6, 0);
        LocalTime endThursday = LocalTime.of(18, 0);
        LocalTime startFriday = LocalTime.of(7, 0);
        LocalTime endSaturday = LocalTime.of(19, 0);

        log.info(now.toString());

        boolean isOpened = true;

        switch (day) {
            case FRIDAY:
            case SATURDAY:
                if (now.isBefore(startFriday) || now.isAfter(endSaturday)) {
                    isOpened = false;
                }
                break;
            default:
                if (now.isBefore(startSunday) || now.isAfter(endThursday)) {
                    isOpened = false;
                }
        }

        return isOpened;
    }
}
