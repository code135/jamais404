package com.jamais404.tools;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * 
 */
public class TimeStampTools {

    /**
     * Transform a timestamp in a String with a wanted format in order to display it
     * @param ts the timestamp
     * @return a String with the wanted format.
     * Example : 23.04.2020 at 14:34
     */
    public static String timeStampToString(Timestamp ts){

        long ts_time = ts.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts_time);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        StringBuilder sb = new StringBuilder();
        sb.append(day);
        sb.append(".");
        sb.append(month);
        sb.append(".");
        sb.append(year);
        sb.append(" at ");
        sb.append(hour);
        sb.append(":");
        sb.append(minute);

        return sb.toString();
    }
}