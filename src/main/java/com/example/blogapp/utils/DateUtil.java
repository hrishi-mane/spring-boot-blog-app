package com.example.blogapp.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    /**
     * Converts the dateTime to the given compliant format.
     *
     * @param dateTime            Date in the following string format yyyy/MM/dd HH:mm:ss
     * @param compliantDateFormat Format to which dateTime is to be converted.
     * @return Date in comliant string format.
     * @throws ParseException
     */
    public static String convertToCompliantDateFormat(Timestamp dateTime, String compliantDateFormat)
            throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(compliantDateFormat);
        return simpleDateFormat.format(dateTime);
    }
}
