package com.spring.handler.datahandlerspring11.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GuidUtil {

    public static String getGuid() {
        StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        int n = (int) (Math.random() * 90000.0D + 10000.0D);
        return now.append(n).toString();
    }

    public static String getZcbhid() {
        StringBuffer now = new StringBuffer(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        int n = (int) (Math.random() * 9000.0D + 1000.0D);
        return now.append(n).toString();
    }

}
