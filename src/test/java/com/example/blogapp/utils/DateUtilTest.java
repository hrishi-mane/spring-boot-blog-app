package com.example.blogapp.utils;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DateUtilTest {

    /**
     * Method under test: {@link DateUtil#convertToCompliantDateFormat(Timestamp, String)}
     */
    @Test
    public void testConvertToCompliantDateFormat2() throws ParseException {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);
        assertEquals("2020-03-01", DateUtil.convertToCompliantDateFormat(timestamp, "2020-03-01"));
        verify(timestamp).getTime();
    }
}

