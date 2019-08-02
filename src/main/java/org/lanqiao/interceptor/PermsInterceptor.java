package org.lanqiao.interceptor;

import org.lanqiao.pojo.User;
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
    HttpSession session = request.getSession();
    System.out.println("url:"+url);
    User user = (User) session.getAttribute("loginUserKey");
    String permss[] = user.getPerms().split(",");
    Boolean bool = false;
    if(user!=null){
      for (String perms : permss) {
        System.out.println("perms:" + perms);
        if (url.equals(perms)) {
          bool = true;
        }
      }
    }
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
