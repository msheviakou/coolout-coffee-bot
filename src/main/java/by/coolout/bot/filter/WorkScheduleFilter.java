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

        LocalTime startWeekday = LocalTime.of(7, 0);
        LocalTime endWeekday = LocalTime.of(21, 0);
        LocalTime startDayOff = LocalTime.of(10, 0);
        LocalTime endDayOff = LocalTime.of(22, 0);

        log.info(now.toString());

        boolean isOpened = true;

        switch (day) {
            case SATURDAY:
            case SUNDAY:
                if (now.isBefore(startDayOff) || now.isAfter(endDayOff)) {
                    isOpened = false;
                }
                break;
            default:
                if (now.isBefore(startWeekday) || now.isAfter(endWeekday)) {
                    isOpened = false;
                }
        }

        return isOpened;
    }
}
