/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan1.Helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author vanvi
 */
public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();
    static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd-MM-yyyy");

    public static Date toDate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);
            formater.setLenient(false);
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Date now() {
        return new Date();
    }

    public static Date add(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            DATE_FORMATER.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = XDate.now();
        }
        return DATE_FORMATER.format(date);
    }

}
