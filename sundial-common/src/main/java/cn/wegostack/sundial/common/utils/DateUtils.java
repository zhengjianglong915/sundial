package cn.wegostack.sundial.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-06-24
 */
public class DateUtils {

    public static Date parse(String dateStr) {
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            Date date = format.parse(dateStr);
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    public static String format(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            return format.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static long subtract(Date big, Date low) {
        return big.getTime() - low.getTime();
    }

    public static Date subtract(Date current, int val, TimeUnit timeUnit) {
        int field;
        switch (timeUnit) {
            case MINUTES:
                field = Calendar.MINUTE;
                break;
            case HOURS:
                field = Calendar.HOUR;
                break;
            default:
                field = Calendar.SECOND;
                break;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(field, -val);
        return calendar.getTime();
    }
}
