/*
 * 文件名：Date.java
 * 版权：Copyright by www.suyinchina.com
 * 描述：
 * 修改人：gxl
 * 修改时间：2014-1-13
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil
{
    public static boolean isNull(Object obj)
    {
        if (obj != null && !"".equals(obj.toString().replaceAll("(^\\s{1,})|(\\s{1,}$)", ""))) return false;
        return true;
    }
    public static Date stringToDate(String da, String format)
    {
        if (DateUtil.isNull(da))
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try
        {
            date = sdf.parse(da);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToString(Date date, String format)
    {
        if (DateUtil.isNull(date))
        {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getDate(Date date, String format, Integer d)
    {
        if (DateUtil.isNull(date))
        {
            return "";
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, d);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getDate(Date date, String format)
    {
        if (DateUtil.isNull(date))
        {}
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static int daysBetween(Date smdate, Date bdate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        }
        catch (ParseException e)
        {
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }



    public static String longToDate(long time){
        try
        {
            Date date = new Date(time);
            return getDate(date,"yyyy-MM-dd");
        }
        catch (Exception e)
        {

        }
        return null;
    }
}
