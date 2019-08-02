package org.lanqiao.interceptor;

import org.lanqiao.pojo.User;
import org.lanqiao.util.JsonWriter;
import org.lanqiao.vo.JsonResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PermsInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String url = request.getRequestURI();
    System.out.println(url);
    HttpSession session = request.getSession();
    System.out.println("url:" + url);
    User user = (User) session.getAttribute("loginUserKey");
    JsonResult jsonResult= null;
    Boolean bool = false;
    if (user != null) {
      String permss[] = user.getPerms().split(",");
      if (permss.length > 0) {
        for (String perms : permss) {//在foreach之前一定要查
          System.out.println("perms:" + perms);
          if (url.equals(perms)) {
            bool = true;
            jsonResult= new JsonResult("200","用户有该权限","");
            break;
          }else {
            jsonResult= new JsonResult("404","用户没有该权限，请先申请权限","");

          }
        }
      }
    }
    JsonWriter.writer(response,jsonResult);
    return bool;
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {}
}
