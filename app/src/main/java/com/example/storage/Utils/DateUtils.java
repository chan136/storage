package com.example.storage.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jerry on 2017/11/30.
 */
public class DateUtils {

    public static String getPreMonth (String date,int month,SimpleDateFormat format) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.MONTH, -month);
        Date m = c.getTime();
        String pre = format.format(m);
        return pre;
    }

}
