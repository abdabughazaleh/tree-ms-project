package com.treeproject.statement.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DDateUtils {

    public static String getNowAsString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getPlushMonthsAsString(Integer months) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = DateUtils.addMonths(new Date(), months);
        return dateFormat.format(newDate);
    }
}
