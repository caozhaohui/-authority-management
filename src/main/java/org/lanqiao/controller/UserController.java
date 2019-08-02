package org.lanqiao.controller;

import org.lanqiao.pojo.User;
import org.lanqiao.service.UserService;
import org.lanqiao.util.JsonWriter;
import org.lanqiao.vo.JsonResult;
import org.lanqiao.vo.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

  @RequestMapping("/login")
  @ResponseBody
  public JsonResult login(
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      HttpSession session) {
    JsonResult jsonResult = null;
    try {
      UserService service = new UserService();
      User user = service.queryByName(username);
      if (user.getName().equals(username) && user.getPassword().equals(password)) {
        session.setAttribute("loginUserKey", user);
        jsonResult = new JsonResult("200", "登陆成功", user);
      } else {
        jsonResult = new JsonResult("404", "登陆失败", "");
      }
    } catch (Exception e) {
      jsonResult = new JsonResult("500", "数据错误", "");
    }
    return jsonResult;
  }

  // 退出请求
  @RequestMapping("/logout")
  public JsonResult logout(HttpServletResponse response, HttpSession httpSession) throws Exception {
    JsonResult jsonResult = new JsonResult("200", "退出登录成功", "");
    // 清空session
    httpSession.invalidate();
    JsonWriter.writer(response, jsonResult);
    return jsonResult;
  }

  @RequestMapping("/menu")
  @ResponseBody
  public JsonResult menu(HttpSession session) {
    JsonResult jsonResult = null;
    User user = (User) session.getAttribute("loginUserKey");
    System.out.println("userName：" + user.getName());
    try {
      UserService userService = new UserService();
      List<Menu> menus = userService.menuList(user.getId().toString());
      if (menus.size() > 0) {
        jsonResult = new JsonResult("200", "查询成功", menus);
      } else {
        jsonResult = new JsonResult("404", "查询失败", "");
      }
    } catch (Exception e) {
      e.getStackTrace();
      jsonResult = new JsonResult("500", "数据错误", "");
    }
    return jsonResult;
  }

  // 查询用户列表
  @RequestMapping("/sys/user/view")
  @ResponseBody
  public JsonResult userList(HttpSession session) {
    JsonResult jsonResult = null;
    try {
      UserService userService = new UserService();
      List<User> users = userService.userList();
      if (users.size() > 0) {
        jsonResult = new JsonResult("200", "查询成功", users);
      } else {
        jsonResult = new JsonResult("404", "查询失败", "");
      }
    } catch (Exception e) {
      e.getStackTrace();
      jsonResult = new JsonResult("500", "数据错误", "");
    }
    return jsonResult;
  }

  // 添加用户
  @RequestMapping("/sys/user/add")
  @ResponseBody
  public JsonResult addUser(
      @RequestParam("name") String name,
      @RequestParam("password") String password,
      @RequestParam("email") String email,
      @RequestParam("mobile") String mobile,
      HttpSession session,
      @RequestParam("role_id") String role_id) {
    User user1 = (User) session.getAttribute("loginUserKey");
    String create_by = user1.getName();
    JsonResult jsonResult = null;
    User user = new User();
    user.setName(name);
    user.setPassword(password);
    user.setEmail(email);
    user.setMobile(mobile);
    user.setCreateBy(create_by);
    // 缺个时间
    try {
      UserService userService = new UserService();
      //      需要给用户添加角色
      int i = userService.addUser(user);
      if (i > 0) {
        jsonResult = new JsonResult("200", "添加成功", "");
      } else {
        jsonResult = new JsonResult("404", "添加失败", "");
      }
    } catch (Exception e) {
      e.getStackTrace();
      jsonResult = new JsonResult("500", "数据错误", "");
    }
    return jsonResult;
  }

  // 更新用户
  @RequestMapping("/sys/user/update")
  @ResponseBody
  public JsonResult updateUser(
      @RequestParam("name") String name,
      @RequestParam("password") String password,
      @RequestParam("email") String email,
      @RequestParam("mobile") String mobile,
      HttpSession session,
      @RequestParam("role_id") String role_id) {
    User user1 = (User) session.getAttribute("loginUserKey");
    String update_by = user1.getName();
    JsonResult jsonResult = null;
    User user = new User();
    user.setName(name);
    user.setPassword(password);
    user.setEmail(email);
    user.setMobile(mobile);
    user.setLastUpdateBy(update_by);
    // 缺个时间
    try {
      UserService userService = new UserService();
      //      需要给用户更新角色
      int i = userService.updateUser(user);
      if (i > 0) {
        jsonResult = new JsonResult("200", "更改成功", "");
      } else {
        jsonResult = new JsonResult("404", "更改失败", "");
      }
    } catch (Exception e) {
      e.getStackTrace();
      jsonResult = new JsonResult("500", "数据错误", "");
    }
    return jsonResult;
  }
}
