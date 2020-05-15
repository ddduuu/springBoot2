package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author dzh
 * @since 2020-05-15
 */
public class UtilDateTime {
    /*
     * 毫秒转当前时间
     * */
    public static String millisecondFomatDate(long time){
        if (time == 0){
            return "";
        }
        Date d = new Date(time);
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        return sm.format(d);
    }

    public static String monthBeginString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return df.format(c.getTime());
    }
    public static String monthEndString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return df.format(c.getTime());
    }

}
