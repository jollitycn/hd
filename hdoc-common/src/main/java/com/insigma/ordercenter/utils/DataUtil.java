package com.insigma.ordercenter.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Data工具类
 * @author zyl
 * @date 2018年11月13日
 */
public class DataUtil {
    public static final String formatter_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String formatter_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String formatter_yyyyMMdd = "yyyy-MM-dd";
    /*将Date类型转化为指定格式字符串*/
    public static String getStringByDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    /*将指定格式的字符串转化为Date*/
    public static Date getDateByString(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /*获取当前Date并转化为指定格式字符串*/
    public static String getCurrentDate(String format) {
        return getStringByDate(new Date(), format);
    }
    
    /*将Date转化为LocalDateTime*/
    public static LocalDateTime getLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    
    /*比较两个Date的时间大小*/
    public static boolean getLocalDateTime(Date date, Date date2) {
        if (date.compareTo(date2) == 1) {
            return true;
        }
        return false;
    }
    
    /*将LocalDateTime转化为Date*/
    public static Date getDataByLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    /*将指定格式String类型日期转化为LocalDateTime*/
    public static LocalDateTime getLocalDateTimeByString(String format,String localDateTime) {
        DateTimeFormatter df= DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(localDateTime, df);
    }
    
    /*获取指定LocalDateTime的秒数*/
    public static Long getSecondByLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }
    
    /*获取指定LocalDateTime的毫秒数*/
    public static Long getMillSecondByLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
    
    /*将LocalDateTime转化为指定格式字符串*/
    public static String getStringByLocalDateTime(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }
    
    /*将LocalDateTime转化为默认yy-mm-dd hh:mm:ss格式字符串*/
    public static String getDefaultStringByLocalDateTime(LocalDateTime localDateTime) {
        return getStringByLocalDateTime(localDateTime, formatter_yyyyMMddHHmmss);
    }
    
    /*获取当前LocalDateTime并转化为指定格式字符串*/
    public static String getCurrentLocalDateTime(String format) {
        return getStringByLocalDateTime(LocalDateTime.now(), format);
    }
    
    /*获取一天的开始时间*/
    public static String getStartTime(LocalDateTime localDateTime) {
        return getDefaultStringByLocalDateTime(localDateTime.with(LocalTime.MIN));
    }
    
    /*获取当天的开始时间*/
    public static String getStartTime() {
        return getDefaultStringByLocalDateTime(LocalDateTime.now());
    }
    
    /*获取一天的结束时间*/
    public static String getEndTime(LocalDateTime localDateTime) {
        return getDefaultStringByLocalDateTime(localDateTime.with(LocalTime.MAX));
    }
    
    /*获取当天的结束时间*/
    public static String getEndTime() {
        return getDefaultStringByLocalDateTime(LocalDateTime.now());
    }
    
    /*比较两个LocalDateTime的时间大小*/
    public static boolean getCompareLocalDateTime(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        if (localDateTime1.isBefore(localDateTime2)) {
            return true;
        }
        return false;
    }
    
    /*计算两个指定格式String日期字符串的时间差(精确到毫秒)*/
    public static Long getCompareSecondLocalDateTime(String format ,String time1, String time2) {
        System.out.println("接收到的参数为:"+format+","+time1+","+time2);
        LocalDateTime localDateTime1=getLocalDateTimeByString(format,time1);
        LocalDateTime localDateTime2=getLocalDateTimeByString(format,time2);
        if(getMillSecondByLocalDateTime(localDateTime1)>getMillSecondByLocalDateTime(localDateTime2)) {
            return getMillSecondByLocalDateTime(localDateTime1)-getMillSecondByLocalDateTime(localDateTime2);
        }
        return getMillSecondByLocalDateTime(localDateTime2)-getMillSecondByLocalDateTime(localDateTime1);
    }
    
    /*获取两个LocalDateTime的天数差*/
    public static Long getCompareDayLocalDateTime(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        if (getCompareLocalDateTime(localDateTime1, localDateTime2)) {
            Duration duration = Duration.between(localDateTime1, localDateTime2);
            return duration.toDays();
        } else {
            Duration duration = Duration.between(localDateTime2, localDateTime1);
            return duration.toDays();
        }
    }
    
    /*获取两个LocalDateTime的小时差*/
    public static Long getCompareYearLocalDateTime(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        if (getCompareLocalDateTime(localDateTime1, localDateTime2)) {
            Duration duration = Duration.between(localDateTime1, localDateTime2);
            return duration.toHours();
        } else {
            Duration duration = Duration.between(localDateTime2, localDateTime1);
            return duration.toHours();
        }
    }
}