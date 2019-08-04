package org.lanqiao.interceptor;

import org.lanqiao.pojo.User;
import org.lanqiao.util.JsonWriter;
import org.lanqiao.vo.JsonResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class interceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(
      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object o)
      throws Exception {
    Boolean bools = false;
//    if (httpServletRequest.getHeader("x-requested-with") != null
//        && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
//      // 如果是ajax请求响应头会有x-requested-with
//      System.out.println("ceshixiang");
//    } else {
//      System.out.println("测试点1");
      HttpSession session = httpServletRequest.getSession();
      User user = (User)session.getAttribute("loginUserKey");
      String url = httpServletRequest.getRequestURI();
      System.out.println("url:" + url);
      if (user != null) {
        String permss[] = user.getPerms().split(",");
        if (permss.length > 0) {
          for (String perms : permss) { // 在foreach之前一定要查
            System.out.println("perms:" + perms);
            if (url.equals(perms)) {
              System.out.println("访问的权限：" + perms);
              bools = true;
              break;
            }
          }
        }
      } else {
        JsonResult jsonResult = new JsonResult("404", "用户没有登陆，请先登陆哦", "");
        JsonWriter.writer(httpServletResponse, jsonResult);
        bools = false;
      }

    return bools;
  }

  @Override
  public void postHandle(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      Object o,
      ModelAndView modelAndView)
      throws Exception {}

  @Override
  public void afterCompletion(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      Object o,
      Exception e)
      throws Exception {}
}
