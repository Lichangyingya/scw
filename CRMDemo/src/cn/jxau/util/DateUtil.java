package cn.jxau.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class  DateUtil {


    /**
     *
     * @param date 日期类型
     * @param pattern 需要转换的日期格式
     * @return format 转换后的日期格式
     */

    public  static String Date2String(Date date, String pattern){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     *
     * @param str
     * @param pattern
     * @return
     * @throws ParseException
     */
    public  static Date String2Date(String str,String pattern) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(str);
        return date;
    }
}
