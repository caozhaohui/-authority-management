package org.lanqiao.util;

public class UrlUtil {
  public static String toUrl(Object obj) {
    String perms = obj == null ? null : obj.toString();
    String url  = null;
    if (perms == null) {
        return null;
    }else{
      url = "/api/"+perms.replaceAll(":","/");
    }
    return url;
  }
}
