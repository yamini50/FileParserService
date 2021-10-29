package com.eCG.recruitment.creditcheck.utils;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Utility {
    public static String removeExtraSpecialChar(String str) {
        return str.replaceAll("[^a-zA-Z1-90\\,]*", "");
    }

    public static Date processDOBData(String dobData) throws ParseException {
        if (dobData.contains("/")) {
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            return new Date(format1.parse(dobData).getTime());
        }
        return convertDateTimeToDate(dobData);
    }

    private static Date convertDateTimeToDate(String dobData) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        String date = format1.format(new Date(Long.parseLong(dobData)));
        return new Date(date);
    }

    public static boolean isNullorEmpty(String s) {
        if(null!=s && !s.isEmpty())
            return false;
        return true;
    }
}
