package org.lanqiao.util;


/**
 * 字符串工具类
 * @author Louis
 * @date Sep 1, 2018
 */
public class StringUtils {




    public static String toString(Object o){

        return o==null?null:o.toString();
    }


    /**
     * 判空操作
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
    }
}
