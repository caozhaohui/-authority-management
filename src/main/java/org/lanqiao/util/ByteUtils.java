package org.lanqiao.util;

/**
 * Byte工具类
 */
public class ByteUtils {
    public static byte toByte(String s){
       return s == null ? 0 : Byte.parseByte(s);
    }
}
