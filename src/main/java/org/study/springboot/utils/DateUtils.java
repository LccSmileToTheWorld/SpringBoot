package org.study.springboot.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 */
public final class DateUtils {

    public static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    //~ Methods ================================================================

    /**
     * @param pattern: 指定格式
     * @param date：时间
     * @Author: Lcc
     * @Description: 时间类型转换为字符串类型
     * @Date: 2019/11/19
     * @Return: java.lang.String
     */
    public static final String date2Str(String pattern, Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * @param date
     * @Author: Lcc
     * @Description: 时间类型转换为字符串类型（yyyy-MM-dd）
     * @Date: 2019/11/19
     * @Return: java.lang.String
     */
    public static final String date2Str(Date date) {
       return date2Str(DATE_PATTERN, date);
    }

    /**
     * @param date
     * @Author: Lcc
     * @Description: 时间类型转换为字符串类型（yyyy-MM-dd HH:mm:ss）
     * @Date: 2019/11/19
     * @Return: java.lang.String
     */
    public static final String dateTime2Str(Date date) {
        return date2Str(TIME_PATTERN, date);
    }

    /**
     * @Author: Lcc
     * @Description: 字符串类型转换为时间类型
     * @Date: 2019/11/19
     * @param pattern：指定格式
     * @param strDate：时间
     * @Return: java.utils.Date
     */
    public static final Date str2Date(String pattern, String strDate) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(strDate);
        } catch (ParseException pe) {
            return null;
        }
        return date;
    }

    /**
     * @Author: Lcc
     * @Description: 字符串类型转换为时间类型（yyyy-MM-dd）
     * @Date: 2019/11/19
     * @param strDate
     * @Return: java.utils.Date
     */
    public static final Date str2Date(String strDate){
        return str2Date(DATE_PATTERN, strDate);
    }

    /**
     * @Author: Lcc
     * @Description: 字符串类型转换为时间类型（yyyy-MM-dd HH:mm:ss）
     * @Date: 2019/11/19
     * @param strDate
     * @Return: java.utils.Date
     */
    public static final Date str2DateTime(String strDate){
        return str2Date(TIME_PATTERN, strDate);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(str2Date(todayAsString));

        return cal;
    }


    public static final int getWeekNum(String strWeek) {
        int returnValue = 0;
        if (strWeek.equals("Mon")) {
            returnValue = 1;
        } else if (strWeek.equals("Tue")) {
            returnValue = 2;
        } else if (strWeek.equals("Wed")) {
            returnValue = 3;
        } else if (strWeek.equals("Thu")) {
            returnValue = 4;
        } else if (strWeek.equals("Fri")) {
            returnValue = 5;
        } else if (strWeek.equals("Sat")) {
            returnValue = 6;
        } else if (strWeek.equals("Sun")) {
            returnValue = 0;
        } else if (strWeek == null) {
            returnValue = 0;
        }

        return returnValue;
    }

    /**
     * 获取日期
     *
     * @param timeType 时间类型，譬如：Calendar.DAY_OF_YEAR
     * @param timenum  时间数字，譬如：-1 昨天，0 今天，1 明天
     * @return 日期
     */
    public static final Date getDateFromNow(int timeType, int timenum) {
        Calendar cld = Calendar.getInstance();
        cld.set(timeType, cld.get(timeType) + timenum);
        return cld.getTime();
    }

    /**
     * 获取日期
     *
     * @param timeType      时间类型，譬如：Calendar.DAY_OF_YEAR
     * @param timenum       时间数字，譬如：-1 昨天，0 今天，1 明天
     * @param format_string 时间格式，譬如："yyyy-MM-dd HH:mm:ss"
     * @return 字符串
     */
    public static final String getDateFromNow(int timeType, int timenum, String format_string) {
        if ((format_string == null) || (format_string.equals("")))
            format_string = "yyyy-MM-dd HH:mm:ss";
        Calendar cld = Calendar.getInstance();
        Date date = null;
        DateFormat df = new SimpleDateFormat(format_string);
        cld.set(timeType, cld.get(timeType) + timenum);
        date = cld.getTime();
        return df.format(date);
    }



    /**
     * 返回sDate与eDate之间的毫秒差
     *
     * @param sDate
     * @param eDate
     * @return
     */
/*    public static String getTime(Date sDate, Date eDate) {
        return String.valueOf(ArithTool.sub(Double.valueOf(eDate.getTime()), Double.valueOf(sDate.getTime())));
    }*/

    /**
     * 几天后
     *
     * @param date
     * @param days
     * @return
     */
    public static Date afterDay(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date); // 设置当前日期
        c.add(Calendar.DATE, days);
        return c.getTime(); // 结果
    }

    /**
     * 几天前
     *
     * @param date
     * @param days
     * @return
     */
    public static Date beforeDay(Date date, int days) {
        return afterDay(date, -days);
    }


    /**
     * 比较两个日期的大小
     * <ul>
     * <li>date1小于date2 : true</li>
     * <li>date1大于等于date2 : false</li>
     * </ul>
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compare(Date date1, Date date2) {
        return date1.before(date2);
    }

    /**
     * 判断日期是否是双休日
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        if (date == null) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1 || dayOfWeek == 7) {
            return true;
        }
        return false;
    }

    /**
     * 日期时间带时分秒的Timestamp表示
     */
    public static Timestamp stringToDateHMS(String str) {

        Timestamp time = null;
        try {
            time = Timestamp.valueOf(str);
        } catch (Exception ex) {

        }
        return time;

    }


    /**
     * 取得一年中的最早一天。
     */
    public static Date getMinDateOfYear(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 取得一年中的最后一天
     */
    public static Date getMaxDateOfYear(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }


    /**
     * 取得一月中的最早一天。
     */
    public static Date getMinDateOfMonth(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 取得一月中的最后一天
     */
    public static Date getMaxDateOfMonth(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }


    /**
     * 取得一季度最早一天。
     */
    public static Date getMinDateOfQuarter(Date date) {
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);// 一月为0

        if (month >= 0 && month <= 2) {// 第一季度
            calendar.set(Calendar.MONTH, 0);
        } else if (month >= 3 && month <= 5) {// 第二季度
            calendar.set(Calendar.MONTH, 3);
        } else if (month >= 6 && month <= 8) {// 第三季度
            calendar.set(Calendar.MONTH, 6);
        } else {// 第四季度
            calendar.set(Calendar.MONTH, 9);
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        dateList.add(0, calendar.getTime());//本季度最早一天

        calendar.add(Calendar.MONTH, 3);// 加3个月，到下个季度的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);// 退一天，得到上季度的最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        dateList.add(1, calendar.getTime());//本季度最后一天

        return dateList.get(0);
    }

    /**
     * 取得一季度最后一天。
     */
    public static Date getMaxDateOfQuarter(Date date) {
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);// 一月为0

        if (month >= 0 && month <= 2) {// 第一季度
            calendar.set(Calendar.MONTH, 0);
        } else if (month >= 3 && month <= 5) {// 第二季度
            calendar.set(Calendar.MONTH, 3);
        } else if (month >= 6 && month <= 8) {// 第三季度
            calendar.set(Calendar.MONTH, 6);
        } else {// 第四季度
            calendar.set(Calendar.MONTH, 9);
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        dateList.add(0, calendar.getTime());//本季度最早一天

        calendar.add(Calendar.MONTH, 3);// 加3个月，到下个季度的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);// 退一天，得到上季度的最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        dateList.add(1, calendar.getTime());//本季度最后一天

        return dateList.get(1);
    }

    /**
     * 取得一个date对象对应的日期的23点59分59秒时刻的Date对象。
     */
    public static Date getMaxDateOfDay(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 取得一个date对象对应的小时的59分59秒时刻的Date对象。
     */
    public static Date getMaxDateOfHour(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 获取2个时间相隔几秒
     */
    public static int getBetweenSecondNumber(Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            return -1;

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        long timeNumber = -1L;
        long TIME = 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {

        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几分钟
     */
    public static int getBetweenMinuteNumber(Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            return -1;

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        long timeNumber = -1l;
        long TIME = 60L * 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几小时
     */
    public static int getBetweenHourNumber(Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            return -1;

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        long timeNumber = -1l;
        long TIME = 60L * 60L * 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几天
     */
    public static int getBetweenDayNumber(Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            return -1;

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        long dayNumber = -1L;
        long DAY = 24L * 60L * 60L * 1000L;
        try {
            // "2010-08-01 00:00:00 --- 2010-08-03 23:59:59"算三天
            dayNumber = (endDate.getTime() + 1000 - startDate.getTime()) / DAY;
            // System.out.println(endDate.getTime()+" "+startDate.getTime());
        } catch (Exception e) {
            log.error(e.toString());
        }
        return (int) dayNumber;
    }

    /**
     * 获取2个时间相隔几天
     */
    public static int getBetweenDayNumberIgnoreTime(Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            return -1;

        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = dateFormat.parse(dateFormat.format(startDate));
            endDate = dateFormat.parse(dateFormat.format(endDate));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        long dayNumber = -1L;
        long DAY = 24L * 60L * 60L * 1000L;

        try {
            // "2010-08-01 00:00:00 --- 2010-08-03 23:59:59"算两天
            dayNumber = (endDate.getTime() + 1000 - startDate.getTime()) / DAY;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) dayNumber;
    }

    /**
     * 获取2个时间相隔几月
     */
    public static int getBetweenMonthNumber(Date startDate, Date endDate) {

        int result = 0;
        try {
            if (startDate == null || endDate == null)
                return -1;

            // swap start and end date
            if (startDate.after(endDate)) {
                Date tmp = endDate;
                endDate = startDate;
                startDate = tmp;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            int monthS = calendar.get(Calendar.MONTH);
            int yearS = calendar.get(Calendar.YEAR);

            calendar.setTime(endDate);
            int monthE = calendar.get(Calendar.MONTH);
            int yearE = calendar.get(Calendar.YEAR);

            if (yearE - yearS == 0) {
                result = monthE - monthS;
            } else {
                result = (yearE - yearS - 1) * 12 + (12 - monthS) + monthE;
            }

        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

    /**
     * 获取2个时间相隔几年
     */
    public static int getBetweenYearNumber(Date startDate, Date endDate) {

        int result = 0;
        try {
            if (startDate == null || endDate == null)
                return -1;

            // swap start and end date
            if (startDate.after(endDate)) {
                Date tmp = endDate;
                endDate = startDate;
                startDate = tmp;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int yearS = calendar.get(Calendar.YEAR);

            calendar.setTime(endDate);
            int yearE = calendar.get(Calendar.YEAR);

            result = yearE - yearS;

        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }


    /**
     * 本季度
     */
    public static List<Date> getCurrentQuarter() {

        List<Date> dateList = new ArrayList<Date>();
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);// 一月为0
        //dateList.add(0, calendar.getTime());
        //dateList.add(1, calendar.getTime());// 结束时间设置为当前时间

        if (month >= 0 && month <= 2) {// 第一季度
            calendar.set(Calendar.MONTH, 0);
        } else if (month >= 3 && month <= 5) {// 第二季度
            calendar.set(Calendar.MONTH, 3);
        } else if (month >= 6 && month <= 8) {// 第三季度
            calendar.set(Calendar.MONTH, 6);
        } else {// 第四季度
            calendar.set(Calendar.MONTH, 9);
        }

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        dateList.add(0, calendar.getTime());//本季度最早一天

        calendar.add(Calendar.MONTH, 3);// 加3个月，到下个季度的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);// 退一天，得到上季度的最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        dateList.add(1, calendar.getTime());//本季度最后一天

        return dateList;
    }

    /**
     * 上季度
     */
    public static List<Date> getLastQuarter() {

        List<Date> dateList = new ArrayList<Date>();
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);// 一月为0

        // 如果是第一季度则返回去年的第四季度
        if (month >= 0 && month <= 2) {// 当前第一季度
            calendar.add(Calendar.YEAR, -1);// 退到去年
            calendar.set(Calendar.MONTH, 9);// 去年十月
        } else if (month >= 3 && month <= 5) {// 当前第二季度
            calendar.set(Calendar.MONTH, 0);
        } else if (month >= 6 && month <= 8) {// 当前第三季度
            calendar.set(Calendar.MONTH, 3);
        } else {// 当前第四季度
            calendar.set(Calendar.MONTH, 6);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        dateList.add(0, calendar.getTime());

        calendar.add(Calendar.MONTH, 3);// 加3个月，到下个季度的第一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);// 退一天，得到上季度的最后一天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        dateList.add(1, calendar.getTime());

        return dateList;
    }

    /**
     * 返回2个日期中的大者
     */
    public static Date max(Date date1, Date date2) {

        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (date1.after(date2)) {
            return date1;
        } else {
            return date2;
        }
    }

    /**
     * 返回不大于date2的日期 如果 date1 >= date2 返回date2 如果 date1 < date2 返回date1
     */
    public static Date ceil(Date date1, Date date2) {

        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (date1.after(date2)) {
            return date2;
        } else {
            return date1;
        }
    }

    /**
     * 返回不小于date2的日期 如果 date1 >= date2 返回date1 如果 date1 < date2 返回date2
     */
    public static Date floor(Date date1, Date date2) {

        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (date1.after(date2)) {
            return date1;
        } else {
            return date2;
        }
    }

    /**
     * 返回2个日期中的小者
     */
    public static Date min(Date date1, Date date2) {

        if (date1 == null && date2 == null) {
            return null;
        }
        if (date1 == null) {
            return date2;
        }
        if (date2 == null) {
            return date1;
        }
        if (date1.after(date2)) {
            return date2;
        } else {
            return date1;
        }
    }

    /**
     * 判断输入日期是否是一天中的最大时刻
     */
    public static boolean isMaxDayOfDay(Date date1, String precision) {

        if (date1 == null)
            return false;
        Date date2 = getMaxDateOfDay(date1);
        int diffNum = 0;
        if ("HH".equals(precision)) {
            diffNum = getBetweenHourNumber(date1, date2);
        } else if ("mm".equals(precision)) {
            diffNum = getBetweenMinuteNumber(date1, date2);
        } else {
            diffNum = getBetweenSecondNumber(date1, date2);
        }
        return diffNum == 0;
    }

    /**
     * 判断输入日期是否是一天中的最小时刻
     */
    public static boolean isMinDayOfDay(Date date1, String precision) {

        if (date1 == null)
            return false;
        Date date2 = getMinDateOfDay(date1);
        int diffNum = 0;
        if ("HH".equals(precision)) {
            diffNum = getBetweenHourNumber(date1, date2);
        } else if ("mm".equals(precision)) {
            diffNum = getBetweenMinuteNumber(date1, date2);
        } else {
            diffNum = getBetweenSecondNumber(date1, date2);
        }
        return diffNum == 0;
    }

    /**
     * 取得一个date对象对应的日期的0点0分0秒时刻的Date对象。
     */
    public static Date getMinDateOfDay(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getMinDateOfHour(date));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        return calendar.getTime();
    }

    /**
     * 取得一个date对象对应的日期的0分0秒时刻的Date对象。
     */
    public static Date getMinDateOfHour(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 判断输入日期是否是一天中的最大时刻
     */
    public static boolean isMaxDayOfDay(Date date1) {

        if (date1 == null)
            return false;
        Date date2 = getMaxDateOfDay(date1);
        int secondNum = getBetweenSecondNumber(date1, date2);
        return secondNum == 0;
    }

    /**
     * 判断输入日期是否是一天中的最小时刻
     */
    public static boolean isMinDayOfDay(Date date1) {

        if (date1 == null)
            return false;
        Date date2 = getMinDateOfDay(date1);
        int secondNum = getBetweenSecondNumber(date1, date2);
        return secondNum == 0;
    }

    /**
     * 判断输入日期是否是一月中的最大时刻
     */
    public static boolean isMaxDayOfMonth(Date date1) {

        if (date1 == null)
            return false;
        Date date2 = getMaxDateOfMonth(date1);
        int secondNum = getBetweenSecondNumber(date1, date2);
        return secondNum == 0;
    }

    /**
     * 判断输入日期是否是一月中的最小时刻
     */
    public static boolean isMinDayOfMonth(Date date1) {

        if (date1 == null)
            return false;
        Date date2 = getMinDateOfMonth(date1);
        int secondNum = getBetweenSecondNumber(date1, date2);
        return secondNum == 0;
    }


    /**
     * startStr 或者 startStr-endStr
     */
    public static String getDifferentStr(String startStr, String endStr) {

        String dateRangeStr = "";
        if (startStr.equals(endStr)) {
            dateRangeStr = startStr;
        } else {
            dateRangeStr = startStr + "-" + endStr;
        }
        return dateRangeStr;
    }

    /**
     * 将字符串转化为日期。 字符串格式("YYYY-MM-DD")。
     * 例如："2012-07-01"或者"2012-7-1"或者"2012-7-01"或者"2012-07-1"是等价的。
     */
    public static Date stringToDate(String str, String pattern) {

        Date dateTime = null;
        try {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            dateTime = formater.parse(str);
        } catch (Exception e) {
            log.error(e.toString());
        }
        return dateTime;
    }

    /**
     * 给定一个日期和天数，得到这个日期+天数的日期
     *
     * @param date 指定日期
     * @param num  天数
     * @return
     * @author tangzhi, 2012-11-28
     */
    public static String getNextDay(String date, int num, String format) {

        Date d = stringToDate(date, format);
        Calendar ca = Calendar.getInstance();
        ca.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        ca.setTime(d);

        int day = ca.get(Calendar.DATE);
        day = day + num;
        ca.set(Calendar.DATE, day);
        return getFormatDateTime(ca.getTime(), format);

    }

    /**
     * 给定一个日期和天数，得到这个日期+天数的日期
     *
     * @param date 指定日期
     * @param num  天数
     * @return
     * @author liwenliang, 2014-09-05
     */
    public static Date getNextDay(Date date, int num) {

        Calendar ca = Calendar.getInstance();
        ca.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        ca.setTime(date);

        int day = ca.get(Calendar.DATE);
        day = day + num;
        ca.set(Calendar.DATE, day);
        return ca.getTime();
    }

    /**
     * 根据指定格式获取日期数据
     *
     * @param date    ：指定日期
     * @param pattern ：日期格式
     * @return
     * @author tangzhi, 2012-11-28
     */
    public static String getFormatDateTime(Date date, String pattern) {

        if (null == date) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return format.format(date);
    }


    /**
     * 获取2个时间相隔几秒,非绝对值
     */
    public static int getBetweenSecondNumberNotAbsolute(Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            return -1;
        //
        // if (startDate.after(endDate)) {
        // Date tmp = endDate;
        // endDate = startDate;
        // startDate = tmp;
        // }

        long timeNumber = -1L;
        long TIME = 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            log.error(e.toString());
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几分钟,非绝对值
     */
    public static int getBetweenMinuteNumberNotAbsolute(Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            return -1;

        // if (startDate.after(endDate)) {
        // Date tmp = endDate;
        // endDate = startDate;
        // startDate = tmp;
        // }

        long timeNumber = -1l;
        long TIME = 60L * 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几小时，非绝对值
     */
    public static int getBetweenHourNumberNotAbsolute(Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            return -1;

        // if (startDate.after(endDate)) {
        // Date tmp = endDate;
        // endDate = startDate;
        // startDate = tmp;
        // }

        long timeNumber = -1l;
        long TIME = 60L * 60L * 1000L;
        try {
            timeNumber = (endDate.getTime() - startDate.getTime()) / TIME;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) timeNumber;
    }

    /**
     * 获取2个时间相隔几天，非绝对值
     */
    public static int getBetweenDayNumberNotAbsolute(Date startDate, Date endDate) {

        if (startDate == null || endDate == null)
            return -1;
        //
        // if (startDate.after(endDate)) {
        // Date tmp = endDate;
        // endDate = startDate;
        // startDate = tmp;
        // }

        long dayNumber = -1L;
        long DAY = 24L * 60L * 60L * 1000L;
        try {
            // System.out.println((endDate.getTime() + 1000 -
            // startDate.getTime()));
            dayNumber = (endDate.getTime() - startDate.getTime()) / DAY;
            // System.out.println(endDate.getTime()+" "+startDate.getTime());
        } catch (Exception e) {
            log.error(e.toString());
        }
        return (int) dayNumber;
    }

    /**
     * 获取2个时间相隔几月，非绝对值
     */
    public static int getBetweenMonthNumberNotAbsolute(Date startDate, Date endDate) {

        int result = 0;
        try {
            if (startDate == null || endDate == null)
                return -1;

            // swap start and end date
            // if (startDate.after(endDate)) {
            // Date tmp = endDate;
            // endDate = startDate;
            // startDate = tmp;
            // }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            int monthS = calendar.get(Calendar.MONTH);
            int yearS = calendar.get(Calendar.YEAR);

            calendar.setTime(endDate);
            int monthE = calendar.get(Calendar.MONTH);
            int yearE = calendar.get(Calendar.YEAR);

            if (yearE - yearS == 0) {
                result = monthE - monthS;
            } else {
                result = (yearE - yearS - 1) * 12 + (12 - monthS) + monthE;
            }

        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

    /**
     * 获取2个时间相隔几年，非绝对值
     */
    public static int getBetweenYearNumberNotAbsolute(Date startDate, Date endDate) {

        int result = 0;
        try {
            if (startDate == null || endDate == null)
                return -1;

            // swap start and end date
            // if (startDate.after(endDate)) {
            // Date tmp = endDate;
            // endDate = startDate;
            // startDate = tmp;
            // }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int yearS = calendar.get(Calendar.YEAR);

            calendar.setTime(endDate);
            int yearE = calendar.get(Calendar.YEAR);

            result = yearE - yearS;

        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

    /**
     * 得到给定时间的年份
     *
     * @return
     */
    public static int getYear(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }


    // 获取给定时间月份，cal.get(Calendar.MONTH)是从零开始。
    public static int getMonth(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 得到给定时间的日
     *
     * @return
     */
    public static int getDay(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }


    /**
     * 得到给定时间的日
     *
     * @return
     */
    public static int getHour(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.HOUR_OF_DAY);
        return day;
    }


    /**
     * 得到时间段中每一天(包含起止日期)
     *
     * @param dateFirst 起始日期
     * @param dateLast  终止日期
     * @return
     */
    public static LinkedList<String> displayIntervalDate(String dateFirst, String dateLast) {

        LinkedList<String> dateList = new LinkedList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateOne = dateFormat.parse(dateFirst);
            Date dateTwo = dateFormat.parse(dateLast);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOne);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            while (calendar.getTime().before(dateTwo)) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                dateList.add(dateFormat.format(calendar.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return dateList;
        }
        return dateList;
    }


    /**
     * 得到时间段中每一月(包含起止日期)
     *
     * @param dateFirst 起始日期
     * @param dateLast  终止日期
     * @return
     */
    public static LinkedList<String> displayIntervalMonth(String dateFirst, String dateLast) {

        LinkedList<String> dateList = new LinkedList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();

            min.setTime(dateFormat.parse(dateFirst));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(dateFormat.parse(dateLast));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                dateList.add(dateFormat.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return dateList;
        }
        return dateList;
    }

    public static void main(String[] args) throws Exception {
        //LinkedList<String> list = displayIntervalDate("2017-1-1","2017-1-12");;
        //System.out.print(addMinute(new Date(),-3));

        System.out.print(weekForStr(String.valueOf(dayForWeek(new Date()))));
    }

    /**
     * 得到时间段中每一年(包含起止日期)
     *
     * @param dateFirst 起始日期
     * @param dateLast  终止日期
     * @return
     */
    public static LinkedList<String> displayIntervalYear(String dateFirst, String dateLast) {

        LinkedList<String> dateList = new LinkedList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        try {
            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();

            min.setTime(dateFormat.parse(dateFirst));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(dateFormat.parse(dateLast));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                dateList.add(dateFormat.format(curr.getTime()));
                curr.add(Calendar.YEAR, 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return dateList;
        }
        return dateList;
    }

    /**
     * 得到时间段中每一小时(包含起止时间)
     *
     * @param dateFirst 起始时间
     * @param dateLast  终止时间
     * @return
     */
    public static LinkedList<String> displayIntervalHour(String dateFirst, String dateLast) {

        LinkedList<String> dateList = new LinkedList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateOne = dateFormat.parse(dateFirst);
            Date dateTwo = dateFormat.parse(dateLast);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOne);
            calendar.add(Calendar.HOUR, -1);
            while (calendar.getTime().before(dateTwo)) {
                calendar.add(Calendar.HOUR, 1);
                dateList.add(dateFormat.format(calendar.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return dateList;
        }
        return dateList;
    }

    /**
     * 得到时间段中每15分钟(包含起止时间)
     *
     * @param dateFirst 起始时间
     * @param dateLast  终止时间
     * @return
     */
    public static LinkedList<String> displayIntervalMinute(String dateFirst, String dateLast) {

        LinkedList<String> dateList = new LinkedList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateOne = dateFormat.parse(dateFirst);
            Date dateTwo = dateFormat.parse(dateLast);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.getMinDateOfDay(dateOne));
            while (calendar.getTime().before(dateTwo)) {
                dateList.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.MINUTE, 15);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return dateList;
        }
        return dateList;
    }

    /**
     * 得到时间段中每15分钟(包含起止时间)
     *
     * @param dateFirst 起始时间
     * @param dateLast  终止时间
     * @return
     */
    public static LinkedList<String> displayIntervalMinute(Date dateFirst, Date dateLast) {

        LinkedList<String> dateList = new LinkedList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DateUtils.getMinDateOfDay(dateFirst));
            while (calendar.getTime().before(dateLast)) {
                dateList.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.MINUTE, 15);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return dateList;
        }
        return dateList;
    }

    /**
     * 判断date是否在两个日期之间
     *
     * @param date
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean dateIsBetween(Date date, Date startDate, Date endDate) {

        if (date == null && startDate == null && endDate == null) {
            return false;
        }
        if (date.after(startDate) && date.before(endDate)) {
            return true;
        }
        return false;
    }


    /**
     * 上上月字符串
     *
     * @return
     */
    public static String getLastMonthStr() {

        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        int delYear = 0;
        int delMonth = cal.get(Calendar.MONTH) - 1;
        String delYearMonth = "";

        if (delMonth == 0) {
            delYear = cal.get(Calendar.YEAR) - 1;
            delMonth = 12;
        } else if (delMonth == -1) {
            delYear = cal.get(Calendar.YEAR) - 1;
            delMonth = 11;
        } else if (delMonth == -2) {
            delYear = cal.get(Calendar.YEAR) - 1;
            delMonth = 10;
        } else {
            delYear = cal.get(Calendar.YEAR);
        }

        if (delMonth < 10) {
            delYearMonth = delYear + "-0" + delMonth + "";
        } else {
            delYearMonth = delYear + "-" + delMonth + "";
        }

        return delYearMonth;
    }

    /**
     * 上个月字符串
     *
     * @return
     */
    public static String getThisMonthStr(Date date) {

        Calendar cal = Calendar.getInstance();
        //Date date = new Date();
        cal.setTime(date);
        int year = 0;
        int month = cal.get(Calendar.MONTH); // 上个月月份
        String yearMonthly = "";

        if (month == 0) {
            year = cal.get(Calendar.YEAR) - 1;
            month = 12;
        } else {
            year = cal.get(Calendar.YEAR);
        }

        if (month < 10) {
            yearMonthly = year + "-0" + month + "";
        } else {
            yearMonthly = year + "-" + month + "";
        }

        return yearMonthly;
    }

    public static Date getNextTime(Date date, int num) {

        Calendar ca = Calendar.getInstance();
        ca.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        ca.setTime(date);

        int minute = ca.get(Calendar.MINUTE);
        minute = minute + num;
        ca.set(Calendar.MINUTE, minute);
        return ca.getTime();
    }

    /**
     * 调整当前时间
     *
     * @param modifyTime ( Y年 M月 d日 H时 m分 s秒 ) 例：修改1年5天 ： 1Y5d ； 十分钟 10m 设置提前10分钟 -10m
     * @return
     */
    public static Date adjustNowTime(String modifyTime) {

        return adjustTime(new Date(), modifyTime);
    }

    /**
     * 调整时间
     *
     * @param nowDate    yyyy-MM-dd HH:mm:ss
     * @param modifyTime ( Y年 M月 d日 H时 m分 s秒 ) 例：修改1年5天 ： 1Y5d ； 十分钟 10m 设置提前10分钟 -10m
     * @return
     */
    public static Date adjustTime(Date nowDate, String modifyTime) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        String[] typeStr = modifyTime.replaceAll("\\d+", "#").split("#");
        String[] valuesStr = modifyTime.replaceAll("[a-zA-Z]+", "#").split("#");

        int adjustTime = 0;
        for (int i = 1; i < typeStr.length; i++) {
            adjustTime += Integer.valueOf(valuesStr[i - 1]) * PeriodEnum.vf(typeStr[i]).getTime();
        }

        calendar.add(Calendar.MILLISECOND, adjustTime);

        return calendar.getTime();
    }

    enum PeriodEnum {
        SECOND("sS", 1000L), MINUTE("m", 1000L * 60), HOUR("hH", 1000L * 60 * 60), DAY("dD", 1000L * 60 * 60 * 24), WEEK("week", 1000L * 60 * 60 * 24 * 7), MONTH("M",
                1000L * 60 * 60 * 24 * 30), YEAR("yY", 1000L * 60 * 60 * 24 * 365);

        private String simpleName;

        private long time;

        private PeriodEnum(String simpleName, long time) {

            this.simpleName = simpleName;
            this.time = time;
        }

        public long getTime() {

            return this.time;
        }

        public static PeriodEnum vf(String simpleName) {

            for (PeriodEnum e : PeriodEnum.values()) {
                if (e.simpleName.indexOf(simpleName) >= 0)
                    return e;
            }
            return null;
        }

    }

    /**
     * @param str
     * @param format
     * @return
     */
    public static Date createDate(String str, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = sdf.parse(str);
        } catch (ParseException e) {
            log.error("日期转换失败" + str);
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 字符串转日期 接收格式yyyy-MM-dd
     *
     * @param str
     * @return
     */
    public static Date getDate(String str) {

        return createDate(str, "yyyy-MM-dd");
    }

    /**
     * 字符串转日期 接收格式yyyy-MM-dd
     *
     * @param str
     * @return
     */
    public static Date getDateYmd(String str) {

        return createDate(str, "yyyyMMdd");
    }


    /**
     * 格式化当前日期
     *
     * @param format
     * @return
     */
    public static String dateToStr(String format) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 加一天
     *
     * @param date
     * @return
     */
    public static Date addDay(Date date, int dayNum) {

        Calendar c = Calendar.getInstance();
        c.setTime(date); // 设置当前日期
        c.add(Calendar.DATE, dayNum); // 日期加1
        return c.getTime(); // 结果

    }

    /**
     * 加小时
     *
     * @param date
     * @return
     */
    public static Date addHours(Date date, int dayNum) {

        Calendar c = Calendar.getInstance();
        c.setTime(date); // 设置当前日期
        c.add(Calendar.HOUR, dayNum); // 日期加1
        return c.getTime(); // 结果

    }

    /**
     * 加分钟
     *
     * @param date
     * @return
     */
    public static Date addMinute(Date date, int minuteNum) {

        Calendar c = Calendar.getInstance();
        c.setTime(date); // 设置当前日期
        c.add(Calendar.MINUTE, minuteNum); // 日期加1
        return c.getTime(); // 结果

    }

    /**
     * 将日期对象转化为指定format格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDateTime(Date date, String format) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }


    /**
     * 将日期对象格式化为日期yyyy-MM-dd时间标准格式的字符串
     *
     * @param date 待格式化的日期
     * @return 格式化为日期、时间格式的字符串
     */
    public static String formatDateTime(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * sDate大于eDate返回true
     *
     * @param sDate
     * @param eDate
     * @return
     */
    public static boolean compareDate(String sDate, String eDate) {

        Date sd = createDate(sDate, "yyyy-MM-dd");
        Date ed = createDate(eDate, "yyyy-MM-dd");
        if (sd.after(ed)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 将日期对象格式化为日期时间标准格式的字符串
     *
     * @param date 待格式化的日期对豄
     * @return 格式化为日期、时间格式的字符串 默认 yyyy-MM-dd
     */
    public static String formatDate(Date date) {

        String format = "yyyy-MM-dd";
        return formatDateTime(date, format);
    }

    /**
     * 当前月份加上month月
     *
     * @param month
     * @return
     */
    public static Date addMonth(int month) {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, month);
        return getDate(formatDateTime(c.getTime()));
    }

    /**
     * 给 date 减去 year年
     *
     * @param date
     * @param year
     * @return
     */
    public static Date subYear(Date date, int year) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);
        return c.getTime();
    }

    /**
     * 在传入的时间上加上month月
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        return getDate(formatDateTime(c.getTime()));
    }

    /**
     * 判断日期是否是双休日
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static boolean isOverDay(Date date) {

        if (date == null) {
            return false;
        }
        if (date.getDay() == 0 || date.getDay() == 6) {
            return true;
        }
        return false;
    }

    private static final Map<Integer, Integer> seasons = new HashMap<Integer, Integer>();

    static {
        seasons.put(0, 0);
        seasons.put(1, 0);
        seasons.put(2, 0);
        seasons.put(3, 1);
        seasons.put(4, 1);
        seasons.put(5, 1);
        seasons.put(6, 2);
        seasons.put(7, 2);
        seasons.put(8, 2);
        seasons.put(9, 3);
        seasons.put(10, 3);
        seasons.put(11, 3);
    }

    /**
     * 获取日期对应的季节(0:spring 1:summer 2:autumn 3:winter)
     *
     * @param date
     * @return
     * @author wangzheng
     * @date 2014年7月14日-上午10:23:11
     */
    @SuppressWarnings({"deprecation"})
    public static int getDateSeason(Date date) {

        if (null == date)
            return 0;
        return seasons.get(date.getMonth());
    }

    /**
     * 判断日期是否为当前季度的最后一月
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static boolean isLastMonthInSeason(Date date) {

        if (3 * getDateSeason(date) + 2 == date.getMonth()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 将String类型的日期按指定格式转换
     *
     * @param date
     * @param pattern
     * @return
     * @author cnpayb
     */
    public static String formatDate(String date, String pattern) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String getYmd(Date date) {

        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    public static String getHms(Date date) {

        return new SimpleDateFormat("HHmmss").format(date);
    }

    /**
     * 时间格式yyyyMMdd
     *
     * @param date
     * @return
     */
    public static Date getYmd(String date) {

        try {
            return new SimpleDateFormat("yyyyMMdd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取给定日期延后 多少天/月的日期
     *
     * @param date   给定的日期
     * @param amount 日/天
     * @param type   1：按日 2：按月 3：按年
     * @return
     */
    public static String getYmdAfter(String date, int amount, int type) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(getYmd(date));
        if (type == 1) { // 按日
            cal.add(Calendar.DATE, amount);
        } else if (type == 2) { // 按月
            cal.add(Calendar.MONTH, amount);
        } else if (type == 3) {
            cal.add(Calendar.YEAR, amount);
        }
        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    /**
     * 日期和当前日期比较
     *
     * @param date
     * @return true：日期大于等于当前日期  flest：日期小于当前日期
     */
    public static Boolean compareDateToBoolean(Date date) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String srcDate = format.format(date);
        String currDate = format.format(new Date());
        Boolean bl = false;
        try {
            Date sDate = format.parse(srcDate);
            Date cDate = format.parse(currDate);
            if (sDate.equals(cDate)) {
                bl = false;
            } else if (sDate.after(cDate)) {
                bl = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bl;
    }

    public static boolean compareDayToBoolean(Date maxDate, Date minDate) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String srcDate = format.format(maxDate);
        String currDate = format.format(minDate);
        Boolean bl = false;
        try {
            Date sDate = format.parse(srcDate);
            Date cDate = format.parse(currDate);
            if (sDate.equals(cDate)) {
                bl = true;
            } else if (sDate.after(cDate)) {
                bl = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 日期和日期比较
     *
     * @param maxDate
     * @return true：max日期大于等于min日期  flest：max日期小于min日期
     */
    public static Boolean compareDateToBooleand(Date maxDate, Date minDate) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String srcDate = format.format(maxDate);
        String currDate = format.format(minDate);
        Boolean bl = false;
        try {
            Date sDate = format.parse(srcDate);
            Date cDate = format.parse(currDate);
            if (sDate.equals(cDate)) {
                bl = true;
            } else if (sDate.after(cDate)) {
                bl = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 日期和日期比较
     *
     * @param maxDate
     * @return true：max日期大于等于min日期  flest：max日期小于min日期
     */
    public static Boolean compareDateToBoolean(Date maxDate, Date minDate) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String srcDate = format.format(maxDate);
        String currDate = format.format(minDate);
        Boolean bl = false;
        try {
            Date sDate = format.parse(srcDate);
            Date cDate = format.parse(currDate);
            if (sDate.equals(cDate)) {
                bl = false;
            } else if (sDate.after(cDate)) {
                bl = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 日期和当前日期比较
     *
     * @param date
     * @return 0：日期相等 1：日期大于当前日期 2：日期小于当前日期
     */
    public static Integer compareDate(Date date) {

        DateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String srcDate = format.format(date);
        String currDate = format.format(new Date());
        Integer retDate = null;
        try {
            Date sDate = format.parse(srcDate);
            Date cDate = format.parse(currDate);
            if (sDate.equals(cDate)) {
                retDate = 0;
            } else if (sDate.after(cDate)) {
                retDate = 1;
            } else {
                retDate = 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retDate;
    }

    /**
     * 指定日期和指定日期比较
     *
     * @param date
     * @return 0：日期相等 1：日期大于当前日期 2：日期小于当前日期
     */
    public static Integer comparePlanDate(Date date, Date starttime) {

        DateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String srcDate = format.format(date);
        String currDate = format.format(starttime);
        Integer retDate = null;
        try {
            Date sDate = format.parse(srcDate);
            Date cDate = format.parse(currDate);
            if (sDate.equals(cDate)) {
                retDate = 1;
            } else if (sDate.after(cDate)) {
                retDate = 1;
            } else {
                retDate = 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retDate;
    }

    /**
     * 获取data最后一天时间
     *
     * @param data
     * @return yyyy-MM-dd
     * @author ningzhirong
     */
    public static String getLastDay(Date data) {

        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return formatDateTime(c.getTime());
    }

    /**
     * 设置时间
     *
     * @param date   给定的日期
     * @param amount 日/天/年
     * @param type   Calendar.DAY_OF_MONTH
     * @return
     */
    public static Date setDate(Date date, int amount, int type) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(type, amount);
        return cal.getTime();
    }

    /**
     * 获取给定日期延后 多少天/月的日期
     *
     * @param date   给定的日期
     * @param amount 日/天
     * @param type   0:按小时（24H） 1：按日 2：按月 3：按年
     * @return
     */
    public static Date getYmdAfter(Date date, int amount, int type) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (type == 0) {
            cal.add(Calendar.HOUR_OF_DAY, amount);
        } else if (type == 1) { // 按日
            cal.add(Calendar.DAY_OF_MONTH, amount);
        } else if (type == 2) { // 按月
            cal.add(Calendar.MONTH, amount);
        } else if (type == 3) {
            cal.add(Calendar.YEAR, amount);
        }
        return cal.getTime();
    }

    public static String currentDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static String currentTime() {
        return new SimpleDateFormat("HHmmss").format(new Date());
    }

    /**
     * 时间戳转指定格式的日期字符串
     *
     * @param time   1537425194268
     * @param format HH:mm
     * @return
     */
    public static String timeStampToDateStr(Long time, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(cal.getTime());
    }

    /**
     * 获取指定时间的最开始时间戳，比如传入 2015-08-08, 返回 2015-08-08 00:00:00的时间戳
     */
    public static long getMinDateOfDay(String strDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getMinDateOfHour(str2Date(strDate)));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        return calendar.getTimeInMillis();
    }

    /**
     * 获取指定时间的最后时间戳，比如传入 2015-08-08, 返回 2015-08-08 23:59:59的时间戳
     */
    public static final long getMaxDateOfDay(String strDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(str2Date(strDate));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTimeInMillis();
    }

    /**
     * 获取某一时间段特定星期几的日期
     *
     * @param dateFrom 开始时间
     * @param dateEnd  结束时间
     * @param weekDays 星期
     * @return 返回时间数组
     */
    public static List<String> findDates(String dateFrom, String dateEnd, String weekDays) {
        long time = 1l;
        long perDayMilSec = 24 * 60 * 60 * 1000;
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //需要查询的星期系数
        String strWeekNumber = weekForNum(weekDays);
        try {
            dateFrom = sdf.format(sdf.parse(dateFrom).getTime() - perDayMilSec);
            while (true) {
                time = sdf.parse(dateFrom).getTime();
                time = time + perDayMilSec;
                Date date = new Date(time);
                dateFrom = sdf.format(date);
                if (dateFrom.compareTo(dateEnd) <= 0) {
                    //查询的某一时间的星期系数
                    Integer weekDay = dayForWeek(date);
                    //判断当期日期的星期系数是否是需要查询的
                    if (strWeekNumber.indexOf(weekDay.toString()) != -1) {
                        dateList.add(dateFrom);
                    }
                } else {
                    break;
                }
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return dateList;
    }

    /**
     * 得到对应星期的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
     *
     * @param weekDays 星期格式  星期一|星期二
     */
    public static String weekForNum(String weekDays) {
        //返回结果为组合的星期系数
        String weekNumber = "";
        //解析传入的星期
        if (weekDays.indexOf("|") != -1) {//多个星期数
            String[] strWeeks = weekDays.split("\\|");
            for (int i = 0; i < strWeeks.length; i++) {
                weekNumber = weekNumber + "" + getWeekNumByCode(strWeeks[i]).toString();
            }
        } else {//一个星期数
            weekNumber = getWeekNumByCode(weekDays).toString();
        }

        return weekNumber;
    }

    //得到当期时间的周系数。星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
    public static Integer dayForWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //将星期转换为对应的系数  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
    public static Integer getWeekNumByCode(String strWeek) {
        Integer number = 1;//默认为星期日
        if ("7".equals(strWeek)) {//星期日
            number = 1;
        } else if ("1".equals(strWeek)) {//星期一
            number = 2;
        } else if ("2".equals(strWeek)) {//星期二
            number = 3;
        } else if ("3".equals(strWeek)) {//星期三
            number = 4;
        } else if ("4".equals(strWeek)) {//星期四
            number = 5;
        } else if ("5".equals(strWeek)) {//星期五
            number = 6;
        } else if ("6".equals(strWeek)) {//星期六
            number = 7;
        }
        return number;
    }


    /**
     * 得到对应星期的系数的中文字符  星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
     *
     * @param weekCode 星期的系数
     */
    public static String weekForStr(String weekCode) {
        String str = "";
        if ("1".equals(weekCode)) {
            str = "星期日";
        } else if ("2".equals(weekCode)) {
            str = "星期一";
        } else if ("3".equals(weekCode)) {
            str = "星期二";
        } else if ("4".equals(weekCode)) {
            str = "星期三";
        } else if ("5".equals(weekCode)) {
            str = "星期四";
        } else if ("6".equals(weekCode)) {
            str = "星期五";
        } else if ("7".equals(weekCode)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * 获取当前年份之前若干年的年份数组,不包括当前年份
     *
     * @param years 查询前多少年 years <= 1 只返回上一年
     * @param order 输出顺序 true小-大: 2016,2017,2018  false大-小: 2018,2017,2016
     * @return 年份数组
     */
    public static String[] getLastSomeYearArray(int years, boolean order) {
        Year now = Year.now();
        if (years <= 1) {
            return new String[]{String.valueOf(now.getValue() - 1)};
        }
        String[] arrayYears = new String[years];
        for (int x = 0; x < years; x++) {
            if (order) {
                arrayYears[x] = String.valueOf(now.getValue() + x - years);
                continue;
            }
            arrayYears[x] = String.valueOf(now.getValue() - x - 1);
        }
        return arrayYears;
    }

    /**
     * 获取当前年份之前若干年的年份数组,不包括当前年份
     *
     * @param years 查询前多少年 years <= 1 只返回上一年
     * @return 年份数组 倒序排列:2016,2017,2018
     */
    public static String[] getLastSomeYearArray(int years) {
        return getLastSomeYearArray(years, true);
    }

    /**
     * 获取当前月份之前若干月的年份+月份数组,不包括当前月
     *
     * @param months 查询前多少月 months <= 1 只返回上个月
     * @param order  输出顺序 true小-大: 2017-12,2018-01,2018-02  false大-小: 2018-02,2018-01,2017-12
     * @return 年份数组 yyyy-MM
     */
    public static String[] getLastSomeMonthArray(int months, boolean order) {
        YearMonth now = YearMonth.now();
        if (months <= 1) {
            return new String[]{String.valueOf(now.minusMonths(1))};
        }
        String[] arrayMonth = new String[months];
        for (int x = 0; x < months; x++) {
            if (order) {
                arrayMonth[x] = String.valueOf(now.minusMonths(months - x));
                continue;
            }
            arrayMonth[x] = String.valueOf(now.minusMonths(1 + x));
        }
        return arrayMonth;
    }

    /**
     * 获取当前月份之前若干月的年份+月份数组,不包括当前月
     *
     * @param months 查询前多少月 months <= 1 只返回上个月
     * @return 年份数组 yyyy-MM 倒序排列:2017-12,2018-01,2018-02
     */
    public static String[] getLastSomeMonthArray(int months) {
        return getLastSomeMonthArray(months, true);
    }

}
