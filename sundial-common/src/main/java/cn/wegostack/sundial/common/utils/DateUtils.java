package cn.wegostack.sundial.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
