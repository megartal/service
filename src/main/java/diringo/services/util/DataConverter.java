package diringo.services.util;

import ir.huri.jcal.JalaliCalendar;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Akbar
 * @DATE 7/11/2018.
 */
public class DataConverter {
    static Map<String, String> map = new HashMap<>();
    static Map<String, String> map2 = new HashMap<>();

    static {
        map.put("01", "فروردین");
        map.put("02", "اردیبهشت");
        map.put("03", "خرداد");
        map.put("04", "تیر");
        map.put("05", "مرداد");
        map.put("06", "شهریور");
        map.put("07", "مهر");
        map.put("08", "آبان");
        map.put("09", "آذر");
        map.put("10", "دی");
        map.put("11", "بهمن");
        map.put("12", "اسغند");

        map2.put("popularity", "امتیاز");
        map2.put("price-asc", "قیمت (کمترین به بیشترین)");
        map2.put("price-desc", "قیمت (بیشترین به کمترین)");
        map2.put("stars-desc", "ستاره (5 به 1)");
        map2.put("stars-asc", "ستاره (1 به 5)");
    }

    public static String farsiDate(String date) {
        String[] split = date.split("/");
        String s = "%s %s %s";
        String format = String.format(s, " " + split[2] + " ", map.get(split[1]), " " + split[0] + " ");
        return format;
    }

    public static String sortConv(String sort) {
        return map2.get(sort);
    }

    public static Date jalaliToJavaDate(String date) {
        String[] array = date.split("/");
        return new JalaliCalendar(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2])).toGregorian().getTime();
    }
}
