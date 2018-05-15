package cn.jxustsrw.biyeji.util;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class DateUtil {
    /*
    //将节次转月日
    public static int getCourseMouth(String date, String startTime) throws ParseException {
        startTime = "20180305";//开始时间
        int week = Integer.getInteger(date.substring(0, 2));
        int day = Integer.getInteger(date.substring(2, 3));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date start = sdf.parse(startTime);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(start);
        cal1.add(Calendar.WEDNESDAY, week);
        cal1.add(Calendar.DATE, day);
        return cal1.get(Calendar.MONTH);
    }

    public static int getCourseDay(String date, String startTime) throws ParseException {
        startTime = "20180305";//开始时间
        System.out.println(date);
        int week = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(2,3));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date start = sdf.parse(startTime);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(start);
        System.out.println(sdf.format(cal1.getTime()));
        cal1.add(Calendar.WEEK_OF_MONTH, week);
        cal1.add(Calendar.DAY_OF_WEEK, day);
        System.out.println(cal1.getTime());
        return cal1.get(Calendar.DATE);
    }
    public static  void main(String arg[]) throws ParseException {
        System.out.println(getCourseDay("081000000",""));
    }
    */
    public static int calcDayOffset(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {  //同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
                    timeDistance += 366;
                } else {  //不是闰年

                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { //不同年
            return day2 - day1;
        }
    }

    /**
     * date2比date1多的周数
     */
    public static int calcWeekOffset(Date startTime, Date endTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) dayOfWeek = 7;

        int dayOffset = calcDayOffset(startTime, endTime);

        int weekOffset = dayOffset / 7;
        int a;
        if (dayOffset > 0) {
            a = (dayOffset % 7 + dayOfWeek > 7) ? 1 : 0;
        } else {
            a = (dayOfWeek + dayOffset % 7 < 1) ? -1 : 0;
        }
        weekOffset = weekOffset + a;
        return weekOffset + 1;
    }

    public static int getWeek() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date start = sdf.parse("20180305");
        Date now = new Date();
        return calcWeekOffset(start, now);
    }

    public static int getWeek(String oneDay) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");//安卓端的格式
        Date start = sdf.parse("20180305");
        Date now = sdf1.parse(oneDay);
        return calcWeekOffset(start, now);
    }


    public static int getDayOfWeek() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static int getDayOfWeek(String oneDay) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(oneDay);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static int getWeekWithStart(String startTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date start = sdf.parse(startTime);
        Date now = new Date();
        return calcWeekOffset(start, now);
    }

    public static boolean timeInPeriod(String time, String startTime, String endTime) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l_time = df.parse(time).getTime();
        long l_startTime = df.parse(startTime).getTime();
        long l_endTime = df.parse(endTime).getTime();
        return l_time >= l_startTime && l_time <= l_endTime;
    }
}
