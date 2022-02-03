package com.zied.nasri.www_sms.Tools;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateTools {

    public static Date fromLongString(String d){

        Long ld = Long.parseLong(d);
        return new Date(ld);
    }

    public static String fromLongStringLongFormat(String longStr){

        Long ld = Long.parseLong(longStr);
        return new SimpleDateFormat("E, MMM dd, yyyy").format(new Date(ld));
    }

    public static String timeFromLongStringLongFormat(String longStr){

        Long ld = Long.parseLong(longStr);
        return new SimpleDateFormat("h:mm a").format(new Date(ld));
    }
}
