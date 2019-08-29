package org.lanqiao.interceptor;

import com.google.gson.Gson;
import org.lanqiao.pojo.User;
import org.lanqiao.util.JsonWriter;
import org.lanqiao.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class interceptor implements HandlerInterceptor {

  @Autowired
  RedisTemplate redisTemplate;

  @Override
  public boolean preHandle(
      HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object o)
      throws Exception {
    //刘粤写的  奚好雷注释了
    //Boolean bools = false;


//    if (httpServletRequest.getHeader("x-requested-with") != null
//        && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
//      // 如果是ajax请求响应头会有x-requested-with
//      System.out.println("ceshixiang");
//    } else {
//      System.out.println("测试点1");

    //从这开始注释的
//      HttpSession session = httpServletRequest.getSession();
//      User user = (User)session.getAttribute("loginUserKey");
//      String url = httpServletRequest.getRequestURI();
//      System.out.println("url:" + url);
//      if (user != null) {
//        String permss[] = user.getPerms().split(",");
//        if (permss.length > 0) {
//          for (String perms : permss) { // 在foreach之前一定要查
//            System.out.println("perms:" + perms);
//            if (url.equals(perms)) {
//              System.out.println("访问的权限：" + perms);
//              bools = true;
//              break;
//            }
//          }
//        }
//      } else {
//        JsonResult jsonResult = new JsonResult("404", "用户没有登陆，请先登陆哦", "");
//        JsonWriter.writer(httpServletResponse, jsonResult);
//        bools = false;
//      }
//
//    return bools;



    //奚好雷写的
    Cookie [] cookies = httpServletRequest.getCookies();
    if(cookies != null && cookies.length>0){
        for(Cookie cookie : cookies){
          if("token".equals(cookie.getName())){
            String token_jSession = cookie.getValue();
            Object object = redisTemplate.opsForValue().get(token_jSession);
            if(object != null){
              return true;
            }
          }
        }
    }
    JsonResult jsonResult = new JsonResult("250","没有令牌，不能登陆",null);
    httpServletResponse.setContentType("application/json;charset=UTF-8");
    httpServletResponse.getWriter().write(new Gson().toJson(jsonResult));
    httpServletResponse.getWriter().flush();
    return false;
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
