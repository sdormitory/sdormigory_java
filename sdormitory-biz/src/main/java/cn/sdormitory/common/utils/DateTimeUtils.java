package cn.sdormitory.common.utils;

import cn.sdormitory.common.constant.CommonConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created By ruanteng
 * DateTime：2020/12/3
 * 时间转换工具类
 */

public class DateTimeUtils {

    /**
     * 格式化时间
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date dateTimeFormat(double time)  {
        long times = new Double(time).longValue();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(times * 1000);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= null;
        try {
            date= format.parse(format.format(gc.getTime()));
        }catch (ParseException exception){
            exception.printStackTrace();
        }
        return date;
    }

    /**
     * 获取两个时间之间的间隔
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int compare(Date date1,Date date2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String str = simpleDateFormat.format(date2)+" "+ CommonConstant.ATTENDANCE_TIME;
        return  date1.compareTo(simpleDateFormat2.parse(str));
    }

}
