package com.test.automation.framework.core;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtilities {
    private static final SimpleDateFormat cdf = new SimpleDateFormat("MMddyyyy");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

    public static String getCurrentDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return cdf.format(timestamp).toString();
    }

    public static String getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp).toString();
    }
}
