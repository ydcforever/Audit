package com.btw.parser.utils;

/**
 * Created by ydc on 2020/10/15.
 */
public final class ParserUtil {

    public static String dateFormat(String date){
        if (date.contains("-")) {
            return date;
        } else {
            return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
        }
    }
}
