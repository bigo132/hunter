package com.tom.hunter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by txu1 on 9/6/2016.
 */
public class AppUtils {

    public static String convertDateString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(date);
    }
}
