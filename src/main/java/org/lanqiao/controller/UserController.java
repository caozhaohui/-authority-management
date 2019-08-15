package org.lanqiao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.lanqiao.pojo.User;
import org.lanqiao.service.impl.UserServiceImpl;
import org.lanqiao.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

// @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600,allowedHeaders=
// "http://localhost:8080",allowCredentials = "true")

@CrossOrigin
@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @RequestMapping(value = "/login")
    public JsonResult login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        JsonResult jsonResult = null;
        System.out.println(username + password);
        try {
            User user = userServiceImpl.queryByName(username);
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                session.setAttribute("loginUserKey", user);
                User a = (User) session.getAttribute("loginUserKey");
                jsonResult = new JsonResult("200", "登陆成功", user);
            } else {
                jsonResult = new JsonResult("404", "登陆失败", "");
            }
        } catch (Exception e) {
            jsonResult = new JsonResult("500", "数据错误", "");
        }
        return jsonResult;
    }

    // 模糊查询用户
    @RequestMapping(value = "/sys/user/view/ByName")
    public JsonResult userByName(
            @RequestParam("username") String username,
            @RequestParam("role") String role,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {
        JsonResult jsonResult = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            System.out.println("username" + username);
            List<User> user = userServiceImpl.ByName(username, role);
            PageInfo pageInfo = new PageInfo(user);
            if (user != null) {
                jsonResult = new JsonResult("200", "查询成功", pageInfo);
            } else {
                jsonResult = new JsonResult("404", "查询失败", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult("500", "数据错误", "");
        }
        return jsonResult;
    }

    //   通过用户id查询
    @ResponseBody
    @RequestMapping(value = "/sys/user/view/ById")
    public JsonResult userByUserId(@RequestParam("userId") int userId) {
        JsonResult jsonResult = null;

        try {
            User user = userServiceImpl.queryById(userId);
            if (user != null) {
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
    public JsonResult logout(HttpSession httpSession) throws Exception {
        JsonResult jsonResult = new JsonResult("200", "退出登录成功", "");
        // 清空session
        httpSession.invalidate();
        return jsonResult;
    }

    // 查询用户列表
    @RequestMapping("/sys/user/view")
    public JsonResult userList(
            @RequestParam(value = "pageNum") int pageNum,
            @RequestParam(value = "pageSize") int pageSize) {
        JsonResult jsonResult = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<User> user = userServiceImpl.userList();
            PageInfo pageInfo = new PageInfo(user);
            if (user != null) {
                jsonResult = new JsonResult("200", "查询成功", pageInfo);
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
    public JsonResult addUser(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("mobile") String mobile,
            @RequestParam("status") Byte status,
            HttpSession session,
            @RequestParam("role") String role) {
//      User user1 = (User) session.getAttribute("loginUserKey");
//      String create_by = user1.getName();
//
        JsonResult jsonResult = null;
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setMobile(mobile);
//      user.setCreateBy(create_by);
        user.setStatus(status);
        user.setRole(role);
        System.out.println("得到的用户"+user.toString());
        // 缺个时间
        try {

            //      需要给用户添加角色
            Long i = userServiceImpl.addUser(user);
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
    @RequestMapping("/sys/user/delete")
    public JsonResult deleteUser(@RequestParam("id") int[] id) {

        JsonResult jsonResult = null;
        for (int i = 0; i < id.length; i++) {
            System.out.println("数组的长度" + id[i]);
        }

        try {
            int i = userServiceImpl.deleteUser(id);
            if (i>0) {
                jsonResult = new JsonResult("200", "删除用户成功", "");
            } else {
                jsonResult = new JsonResult("400", "删除用户失败", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult("500", "删除用户异常", "");
        }
        return jsonResult;
    }


}

