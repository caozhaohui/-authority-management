package org.lanqiao.util;

public class IntegerUtils {
  public static int toInt(Object obj) {
    return obj == null ? 0 : Integer.valueOf(obj.toString());
  }
}
