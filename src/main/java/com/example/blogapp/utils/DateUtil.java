package com.example.blogapp.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {

    private DateUtil() {
        //sonar compliance
    }

    /**
     * Converts the dateTime to the given compliant format.
     *
     * @param dateTime            Date in the following string format yyyy/MM/dd HH:mm:ss
     * @param compliantDateFormat Format to which dateTime is to be converted.
     * @return Date in comliant string format.
     */
    public static String convertToCompliantDateFormat(Timestamp dateTime, String compliantDateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(compliantDateFormat);
        return simpleDateFormat.format(dateTime);
    }
}
