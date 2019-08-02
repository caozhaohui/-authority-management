package org.lanqiao.controller;

import org.lanqiao.pojo.User;
import org.lanqiao.service.UserService;
import org.lanqiao.vo.JsonResult;
import org.lanqiao.vo.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/menu")
    @ResponseBody
    public JsonResult menu(HttpSession session) {
        JsonResult jsonResult = null;
        User user = (User)session.getAttribute("loginUserKey");
        System.out.println("userName："+user.getName());
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
}
